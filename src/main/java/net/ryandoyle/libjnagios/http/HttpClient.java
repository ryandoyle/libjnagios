package net.ryandoyle.libjnagios.http;




import net.ryandoyle.libjnagios.http.domain.Form;
import net.ryandoyle.libjnagios.http.request.GetRequest;
import net.ryandoyle.libjnagios.http.request.PostRequest;

import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public class HttpClient {


    private static final String QUERY_CHARACTER = "?";
    private static final String QUERY_SEPARATOR = "&";

    private final String url;
    private HttpURLConnection connection;

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
        this.connection = (HttpURLConnection) new URL(url).openConnection();
    }

    public String getUrl(){
        return url;
    }

    public String get() throws IOException {
        return new GetRequest(connection).getResponseBody();
    }

    public String post(Form form) throws IOException {
        return new PostRequest(connection, form).postForm().getResponseBody();
    }

    public HttpClient navigateTo(String url) throws IOException {
        return new HttpClient(this.url + url);
    }


}
