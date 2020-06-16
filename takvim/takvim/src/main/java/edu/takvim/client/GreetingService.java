package edu.takvim.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import edu.takvim.shared.Kullanici;
import edu.takvim.shared.Randevu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
  String greetServer(String name) throws IllegalArgumentException;

  String getWeather(String tarih);

  Kullanici getKullanici(String adi);


  void randevuKaydet(String aciklama, Date basTar,Date bitTar,Kullanici kullanici);

  ArrayList<Randevu> getRandevular();
}
