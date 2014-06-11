package net.ryandoyle.libjnagios.http;


import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient {


    private static final String QUERY_CHARACTER = "?";
    private static final String QUERY_SEPARATOR = "&";

    private final String url;
    private URLConnection connection;

    public HttpClient(String url, final String userName, final String password) throws IOException {
        this(url);
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password.toCharArray());
            }
        });
    }

    public HttpClient(String url) throws IOException {
        this.url = url;
        this.connection = new URL(url).openConnection();
    }

    public String getUrl(){
        return url;
    }

    public String getBody() throws IOException {
        InputStream response = connection.getInputStream();
        return new StreamReader(response).read();
    }

    public HttpClient navigateTo(String url) throws IOException {
        return new HttpClient(this.url + url);
    }


}
