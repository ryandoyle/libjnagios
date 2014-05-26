package net.ryandoyle.libjnagios.page;

import net.ryandoyle.libjnagios.http.HttpClient;

import java.io.IOException;

public class PageFactory {

    private final HttpClient httpClient;

    public PageFactory(HttpClient httpClient){
        this.httpClient = httpClient;
    }

    public Page getPageforHost(String hostName) throws IOException{
        return new SingleHostStatusPage(httpClient, hostName);
    }
}
