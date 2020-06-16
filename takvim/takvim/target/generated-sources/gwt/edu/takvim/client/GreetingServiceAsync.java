package edu.takvim.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.takvim.shared.Kullanici;
import edu.takvim.shared.Randevu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface GreetingServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see edu.takvim.client.GreetingService
     */
    void greetServer( java.lang.String name, AsyncCallback<java.lang.String> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see edu.takvim.client.GreetingService
     */
    void getWeather( java.lang.String tarih, AsyncCallback<java.lang.String> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see edu.takvim.client.GreetingService
     */
    void getKullanici( java.lang.String adi, AsyncCallback<edu.takvim.shared.Kullanici> callback );

    void randevuKaydet(String aciklama, Date basTar, Date bitTar, Kullanici kullanici, AsyncCallback<Void> async);

    void getRandevular(AsyncCallback<ArrayList<Randevu>> async);


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static GreetingServiceAsync instance;

        public static final GreetingServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (GreetingServiceAsync) GWT.create( GreetingService.class );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instantiated
        }
    }
}
