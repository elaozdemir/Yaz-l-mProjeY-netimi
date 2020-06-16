package edu.takvim.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "randevu")
public class Randevu implements Serializable, IsSerializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "baslik")
    private String baslik;
    @Column(name = "aciklama")
    private String aciklama;
    @Column(name = "baslama_tarihi")
    private Date baslama_tarihi;
    @Column(name = "bitis_tarihi")
    private Date bitis_tarihi;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "olusturan_kullanici")
    private Kullanici olusturanKullanici;


    public Randevu() {
    }

    public Randevu(String aciklama, Date basTar, Date bitTar, Kullanici kullanici) {
        this.aciklama = aciklama;
        this.baslama_tarihi = basTar;
        this.bitis_tarihi = bitTar;
        this.olusturanKullanici = kullanici;
        System.out.println(kullanici);
        System.out.println("--------------------");
        System.out.println(GecerliKullanici.kullanici);
        baslik = kullanici.getAdi();


    }


    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Date getBaslama_tarihi() {
        return baslama_tarihi;
    }

    public void setBaslama_tarihi(Date baslama_tarihi) {
        this.baslama_tarihi = baslama_tarihi;
    }

    public Date getBitis_tarihi() {
        return bitis_tarihi;
    }

    public void setBitis_tarihi(Date bitis_tarihi) {
        this.bitis_tarihi = bitis_tarihi;
    }

    public Kullanici getOlusturanKullanici() {
        return olusturanKullanici;
    }

    public void setOlusturanKullanici(Kullanici olusturanKullanici) {
        this.olusturanKullanici = olusturanKullanici;
    }
}