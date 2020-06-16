package edu.takvim.client;

import com.google.gwt.i18n.client.LocalizableResource.Generate;

@Generate(format = "com.google.gwt.i18n.server.PropertyCatalogFactory")
public interface Messages extends com.google.gwt.i18n.client.Messages {

    @DefaultMessage("Enter your name")
    String nameField();

    @DefaultMessage("Send")
    String sendButton();

    @DefaultMessage("Proje Adı")
    String projectName();

    @DefaultMessage("TAKVİM")
    String CALENDAR();

    @DefaultMessage("Hoşgeldiniz")
    String welcome();

    @DefaultMessage("Kullanıcı Adı")
    String userName();

    @DefaultMessage("Password")
    String password();

    @DefaultMessage("Takvim Hakkında")
    String about();

    @DefaultMessage("Giriş")
    String signIn();

    @DefaultMessage("Ara")
    String search();

    @DefaultMessage("Bildirimler")
    String notifications();

    @DefaultMessage("Ayarlar")
    String settings();

    @DefaultMessage("Yeni")
    String _new();
}
