package edu.takvim.client.gui;

import com.google.gwt.user.client.ui.*;

class KullaniciGuncelleme extends DialogBox  {
  public KullaniciGuncelleme() {
    setText("Güncelleme");

    HTML msg = new HTML("<center>Kullanıcı Güncelleme</center>",true);

    DockPanel dock = new DockPanel();
    dock.setSpacing(4);

    FlexTable ft = new FlexTable();
    ft.setCellSpacing(10);
    ft.setWidget(0, 0, new Label("Şifre"));
    TextBox tbSifre = new TextBox();
    ft.setWidget(0, 1, tbSifre);

    ft.setWidget(1, 0, new Label("Telefon"));
    TextBox tbTelefon = new TextBox();
    ft.setWidget(1, 1, tbTelefon);

    ft.setWidget(2, 0, new Label("Eposta"));
    TextBox tbEposta = new TextBox();
    ft.setWidget(2, 1, tbEposta);

    Button btnKaydet = new Button("Kaydet");


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