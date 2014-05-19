package net.ryandoyle.libjnagios.page;


import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class CgiConnection {

    private final String STATUS = "/status.cgi?embedded=1&noheader=1&limit=0";

    private final Connection connection;
    private final String url;

    public CgiConnection(String url, String userName, String password) {
        String userNameAndPasswordInBasicAuthFormat = new String(userName + ":" + password);
        String encodedBasicAuth = new String(Base64.encodeBase64(userNameAndPasswordInBasicAuthFormat.getBytes()));
        this.url = url;
        this.connection = Jsoup.connect(url).header("Authorization", "Basic " + encodedBasicAuth);
    }

    public CgiConnection(String url){
        this.url = url;
        this.connection = Jsoup.connect(url);
    }

    public String hostStatus(String hostName) throws IOException {
            return null;
            // return connection.url(url + STATUS + "&host=" + hostName).get();
    }


}
