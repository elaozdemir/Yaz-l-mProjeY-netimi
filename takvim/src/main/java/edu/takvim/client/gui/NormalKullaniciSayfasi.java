package edu.takvim.client.gui;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import edu.takvim.client.IconUtils;
import edu.takvim.client.Messages;

import java.util.ArrayList;
import java.util.Date;

public class NormalKullaniciSayfasi extends VerticalPanel {
    private DatePicker dp;
    private Calendar calendar;

    private final Messages messages = GWT.create(Messages.class);

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

        Appointment appointment = new Appointment();
        appointment.setStart(TarihIslemleriUtil.stringToDate("11/03/2020 04:00:00"));
        appointment.setEnd(TarihIslemleriUtil.stringToDate("11/03/2020 07:00:00"));
        appointment.setTitle("tittttleeeeeeeeeeeeee");
        appointment.setCreatedBy("created byyyy");
        appointment.setDescription("bu da desc");
        appointment.setReadOnly(true);


        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);

        calendar.addAppointments(appointments);

//        GWT.log(TarihIslemleriUtil.stringToDate("11/03/2020 16:50:00").toString());

        dockPanel.add(calendar, DockPanel.CENTER);


        add(dockPanel);
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
        hpSol.add(new Label(messages.projectName()));
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
        hpSag.add(new Button(messages.search()));
        hpSag.add(new ListBox());
        hpSag.add(new Button(messages.notifications()));
        hpSag.add(new Button(messages.settings()));
        hpSag.add(new Button(messages._new()));
        dockPanel.add(hpSag, DockPanel.LINE_END);

        return dockPanel;
    }

    private IsWidget menuyuOlustur() {
        MenuBar menu = new MenuBar(true);

        menu.setAutoOpen(true);
//        menu.setWidth("25px");
        menu.setAnimationEnabled(true);

        MenuBar fileMenu = new MenuBar(true);
        fileMenu.setAnimationEnabled(true);
        fileMenu.getElement().getStyle().setPosition(Style.Position.RELATIVE);
        fileMenu.getElement().getStyle().setZIndex(999999);

        MenuItem menuItem = new MenuItem(IconUtils.getIcon("User-Interface-Menu-icon.png"), true, fileMenu);
        menu.addItem(menuItem);

        fileMenu.addItem(" menu 1", (Command) () -> {

        });
        fileMenu.addItem("menu 2", (Command) () -> {
        });
        fileMenu.addItem("menu 3", (Command) () -> {
        });
        fileMenu.addItem("menu 4", (Command) () -> {
        });

        return menu;
    }

    private VerticalPanel yanPaneliOlustur() {
        VerticalPanel vp = new VerticalPanel();
        vp.setSpacing(20);

        vp.add(new Label(TarihIslemleriUtil.getStringFormat(new Date())));
        dp = new DatePicker();
        vp.add(dp);

        dp.addValueChangeHandler(e -> {
            calendar.setDate(dp.getValue());
        });

        return vp;
    }
}