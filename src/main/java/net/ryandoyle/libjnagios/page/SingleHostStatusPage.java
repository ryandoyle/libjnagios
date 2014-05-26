package net.ryandoyle.libjnagios.page;

import net.ryandoyle.libjnagios.http.HttpClient;

import java.io.IOException;

public class SingleHostStatusPage extends Page {

    private final HttpClient httpClient;

    public SingleHostStatusPage(HttpClient httpClient, String host) throws IOException {
        this.httpClient = httpClient.navigateTo("/status.cgi?embedded=1&noheader=1&limit=0&host=" + host);
    }

    @Override
    public String toString() {
        try {
            return httpClient.getBody();
        } catch (IOException e) {
            return null;
        }
    }

}
