package edu.takvim.client.gui;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import edu.takvim.client.GreetingService;
import edu.takvim.client.GreetingServiceAsync;
import edu.takvim.shared.GecerliKullanici;

class TakvimEkleme extends DialogBox {
    private final GreetingServiceAsync servis = GWT.create(GreetingService.class);
    public TakvimEkleme(Calendar calendar) {
        setAutoHideEnabled(true);


        setText("Takvim Ekleme");

        HTML msg = new HTML("<center>Randevu Ekleme</center>", true);

        DockPanel dock = new DockPanel();
        dock.setSpacing(4);

        FlexTable ft = new FlexTable();
        ft.setCellSpacing(10);
        ft.setWidget(0, 0, new Label("Başlangıç Tarihi"));
        TarihSaat dpBaslangic = new TarihSaat();
        ft.setWidget(0, 1, dpBaslangic);

        ft.setWidget(1, 0, new Label("Bitiş Tarihi"));
        TarihSaat dpBitis = new TarihSaat();
        ft.setWidget(1, 1, dpBitis);

        ft.setWidget(2, 0, new Label("Başlık"));
        TextBox baslik = new TextBox();
        ft.setWidget(2, 1, baslik);

        ft.setWidget(2, 0, new Label("Açıklama"));
        TextBox aciklama = new TextBox();
        ft.setWidget(2, 1, aciklama);

        Button btnKaydet = new Button("Kaydet");
        btnKaydet.addClickHandler(event -> {

            servis.randevuKaydet(aciklama.getValue(), dpBaslangic.getValue(), dpBitis.getValue(),GecerliKullanici.kullanici, new AsyncCallback<Void>() {
                @Override
                public void onFailure(Throwable caught) {

                }

                @Override
                public void onSuccess(Void result) {
                    Appointment appointment = new Appointment();
                    appointment.setStart(dpBaslangic.getValue());
                    appointment.setEnd(dpBitis.getValue());
                    appointment.setTitle(baslik.getValue());
                    appointment.setCreatedBy("created byyyy");
                    appointment.setDescription(aciklama.getValue());
                    appointment.setTitle("Title: "+ GecerliKullanici.kullanici.getAdi());
                    appointment.setCreatedBy("Created by: "+GecerliKullanici.kullanici);

                    calendar.addAppointment(appointment);
                }
            });


            hide();
        });


        dock.add(btnKaydet, DockPanel.SOUTH);
        dock.add(msg, DockPanel.NORTH);
        setAutoHideEnabled(true);
        dock.add(ft, DockPanel.CENTER);

        dock.setCellHorizontalAlignment(btnKaydet, DockPanel.ALIGN_RIGHT);
        dock.setWidth("100%");
        setWidget(dock);

        getElement().getStyle().setZIndex(1);
    }

    public void onClick(Widget sender) {
        hide();
    }
}