package edu.takvim.client;

import com.google.gwt.core.client.GWT;

public class IconUtils {

    public static String getIcon(String icon) {
        final String image = "<img src='" + GWT.getHostPageBaseURL() + "img/icons/" + icon + "' height='25px' width='25px'/>";
        return image;
    }


}
