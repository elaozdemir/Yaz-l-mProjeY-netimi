package edu.takvim.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;

import java.util.Date;

public class TarihSaat extends Composite {
    private DatePicker dp = new DatePicker();
    private TextBox tbSaat = new TextBox();
    private TextBox tbDakika = new TextBox();
    private TextBox tbSaniye = new TextBox();

    public TarihSaat() {
        FlexTable ft = new FlexTable();
        ft.setCellSpacing(5);

        ft.setWidget(0, 0, dp);

        HorizontalPanel hp = new HorizontalPanel();
        hp.add(tbSaat);
        hp.add(new Label(":"));
        hp.add(tbDakika);
        hp.add(new Label(":"));
        hp.add(tbSaniye);

        ft.setWidget(1, 0, hp);

        initWidget(ft);
    }

    public Date getValue() {
        Date value = dp.getValue();

        String date = TarihIslemleriUtil.getStringFormat(value);
        GWT.log("tarih: "+date);
        String saat = tbSaat.getValue()+":"+tbDakika.getValue()+":"+tbSaniye.getValue();
        GWT.log("saat: "+saat);
        String yeniTarih = date.substring(0,10)+" "+saat;

        return TarihIslemleriUtil.stringToDate(yeniTarih);
    }
}
