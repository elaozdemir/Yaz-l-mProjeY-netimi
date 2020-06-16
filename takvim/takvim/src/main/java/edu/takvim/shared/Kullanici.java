package edu.takvim.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "kullanici")
public class Kullanici implements Serializable , IsSerializable {
 
    @Id
    @Column(name = "id")
    private int id;
 
    @Column(name = "adi")
    private String adi;
 
    @Column(name = "sifre")
    private String sifre;
 
    @Column(name = "admin")
    private boolean admin;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Kullanici{" +
                "id=" + id +
                ", adi='" + adi + '\'' +
                ", sifre='" + sifre + '\'' +
                ", admin=" + admin +
                '}';
    }
}