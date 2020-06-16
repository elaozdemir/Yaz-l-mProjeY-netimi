package edu.takvim.server;


import edu.takvim.client.GreetingService;
import edu.takvim.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.takvim.shared.GecerliKullanici;
import edu.takvim.shared.Kullanici;
import edu.takvim.shared.Randevu;
import edu.takvim.veritabani.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {


  public String greetServer(String input) throws IllegalArgumentException {
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("Kullanici-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);

    return "Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
  }

  @Override
  public String getWeather(String tarih) {
    Random r = new Random();
    int cel = r.nextInt(30)+5;
    System.out.println(cel+"C");
    return cel+"°C";
  }

  @Override
  public Kullanici getKullanici(String adi) {
    Session session = HibernateUtil.getSessionFactory().openSession();

    session.beginTransaction();

    List<Kullanici> kullanicis = new ArrayList<Kullanici>();

    kullanicis = session
            .createQuery("from Kullanici where adi=?")
            .setParameter(0, adi).list();

    return kullanicis.get(0);
  }

  @Override
  public void randevuKaydet(String aciklama, Date basTar, Date bitTar,Kullanici kullanici) {
    Session session = HibernateUtil.getSessionFactory().openSession();

    session.beginTransaction();


    Randevu randevu = new Randevu(aciklama,basTar,bitTar, kullanici);
    session.save(randevu);

    session.flush();
    session.getTransaction().commit();
  }

  @Override
  public ArrayList<Randevu> getRandevular() {
    Session session = HibernateUtil.getSessionFactory().openSession();

    session.beginTransaction();

    List<Randevu> randevular;

    randevular = session
            .createQuery("from Randevu").list();

    return new ArrayList<>(randevular);
  }


  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   *
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }
}
