package net.ryandoyle.libjnagios.page;

import net.ryandoyle.libjnagios.builder.HostBuilder;
import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.http.HttpClient;

import java.io.IOException;

public class SingleHostStatusPage {

    private final HttpClient httpClient;

    public SingleHostStatusPage(HttpClient httpClient, String host) throws IOException {
        this.httpClient = httpClient.navigateTo("/status.cgi?embedded=1&noheader=1&limit=0&host=" + host);
    }

    public Host getHost() throws IOException {
        String page = httpClient.getBody();
        return new HostBuilder(page).build();
    }
}
