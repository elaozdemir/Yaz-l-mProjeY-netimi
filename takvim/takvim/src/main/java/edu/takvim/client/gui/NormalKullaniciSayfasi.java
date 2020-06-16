package edu.takvim.client.gui;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import edu.takvim.client.*;
import edu.takvim.shared.GecerliKullanici;
import edu.takvim.shared.Randevu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NormalKullaniciSayfasi extends VerticalPanel  {
    private DatePicker dp;
    private Calendar calendar;
    private Label lblHavaDurumu = new Label("");
    private final Messages messages = GWT.create(Messages.class);
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    public NormalKullaniciSayfasi() {
        DockPanel dockPanel = new DockPanel();

        dockPanel.setSpacing(4);
        dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);

        // Add text all around
        dockPanel.add(ustPaneliOlustur(),
                DockPanel.NORTH);
        dockPanel.add(yanPaneliOlustur(),
                DockPanel.WEST);

        calendar = new Calendar();
        calendar.setDate(new Date());
        calendar.setDays(5);

        greetingService.getRandevular(new AsyncCallback<ArrayList<Randevu>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(ArrayList<Randevu> result) {
                ArrayList<Appointment> appointments = new ArrayList<>();

                for (Randevu randevu : result) {
                    Appointment appointment =new Appointment();
                    appointment.setStart(randevu.getBaslama_tarihi());
                    appointment.setEnd(randevu.getBitis_tarihi());
                    appointment.setTitle("Created by : "+randevu.getOlusturanKullanici().getAdi());
                    appointment.setCreatedBy(randevu.getOlusturanKullanici().getAdi());
                    appointment.setDescription(randevu.getAciklama());

                    if(GecerliKullanici.kullanici.isAdmin()){
                        appointment.setReadOnly(false);
                    }else{
                        appointment.setReadOnly(true);
                    }

                    appointments.add(appointment);
                }

                calendar.addAppointments(appointments);
            }
        });





//        GWT.log(TarihIslemleriUtil.stringToDate("11/03/2020 16:50:00").toString());

        dockPanel.add(calendar, DockPanel.CENTER);


        add(dockPanel);
        refreshWhether();
    }

    private DockPanel ustPaneliOlustur() {
        DockPanel dockPanel = new DockPanel();
        dockPanel.getElement().getStyle().setWidth(100, Style.Unit.PCT);
        dockPanel.getElement().getStyle().setMargin(5, Style.Unit.PX);
        dockPanel.setSpacing(4);
        dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);

        /**
         * SOL TARAF
         */
        HorizontalPanel hpSol = new HorizontalPanel();
        hpSol.setSpacing(10);
        hpSol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        hpSol.getElement().getStyle().setFloat(Style.Float.LEFT);
        hpSol.getElement().getStyle().setMargin(5, Style.Unit.PX);
        hpSol.add(menuyuOlustur());
        hpSol.add(new Label(messages.CALENDAR()));
        dockPanel.add(hpSol, DockPanel.LINE_START);

        /**
         * ORTA
         */
        dockPanel.add(new Label(TarihIslemleriUtil.getStringFormat(new Date())), DockPanel.CENTER);

        /**
         * SAG TARAF
         */
        HorizontalPanel hpSag = new HorizontalPanel();
        hpSag.setSpacing(10);
        hpSag.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        hpSag.getElement().getStyle().setFloat(Style.Float.RIGHT);
        hpSag.getElement().getStyle().setMargin(5, Style.Unit.PX);
        //hpSag.add(new Button(messages.search()));
        hpSag.add(new Label(GecerliKullanici.kullanici.getAdi()));
        hpSag.add(new Button(messages.notifications()));
        hpSag.add(new Button(messages.settings()));

        Button btnNew = new Button(messages._new());
        btnNew.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                new TakvimEkleme(calendar).center();
            }
        });
        hpSag.add(btnNew);

        dockPanel.add(hpSag, DockPanel.LINE_END);



        return dockPanel;
    }

    private IsWidget menuyuOlustur() {
        MenuBar menu = new MenuBar(true);
        menu.getElement().getStyle().setPadding(10, Style.Unit.PX);

        menu.setAutoOpen(true);
//        menu.setWidth("25px");
        menu.setAnimationEnabled(true);

        MenuBar fileMenu = new MenuBar(true);
        fileMenu.setAnimationEnabled(true);
        fileMenu.getElement().getStyle().setPosition(Style.Position.RELATIVE);
        fileMenu.getElement().getStyle().setZIndex(999999);

        MenuItem menuItem = new MenuItem(IconUtils.getIcon("Kullanici-Interface-Menu-icon.png"), true, fileMenu);
        menu.addItem(menuItem);

        fileMenu.addItem("Bilgilerimi Güncelle", new Command() {
            @Override
            public void execute() {
                DialogBox dlg = new KullaniciGuncelleme();
                dlg.center();
            }
        });
        fileMenu.addItem("Çıkış", new Command() {
            @Override
            public void execute() {
                Window.Location.replace(GWT.getHostPageBaseURL());
            }
        });

        return menu;
    }

    private VerticalPanel yanPaneliOlustur() {
        VerticalPanel vp = new VerticalPanel();
        vp.setSpacing(20);

        vp.add(new Label(TarihIslemleriUtil.getStringFormat(new Date())));
        dp = new DatePicker();
        vp.add(dp);

        dp.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> valueChangeEvent) {
                calendar.setDate(dp.getValue());
                refreshWhether();
            }
        });
        vp.add(lblHavaDurumu);

        return vp;
    }

    private void refreshWhether() {
        greetingService.getWeather("", new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(String result) {
                lblHavaDurumu.setText(result);
            }
        });

    }
}