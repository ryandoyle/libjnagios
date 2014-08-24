package net.ryandoyle.libjnagios.http.request;

import net.ryandoyle.libjnagios.http.StreamReader;

import java.io.IOException;
import java.net.HttpURLConnection;

public abstract class Request {

    protected final HttpURLConnection connection;

    public Request(HttpURLConnection connection){
        this.connection = connection;
    }

    public String getResponseBody() throws IOException {
        String body = new StreamReader(connection.getInputStream()).read();
        connection.disconnect();
        return body;
    }

    public int getResponseCode() throws IOException {
        return connection.getResponseCode();
    }





}
