package net.ryandoyle.libjnagios.repository;

import net.ryandoyle.libjnagios.domain.HostFactory;
import net.ryandoyle.libjnagios.http.HttpClient;
import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.page.SingleHostStatusPage;

import java.io.IOException;

public class HttpRepository implements NagiosRepository {

    private final HttpClient httpClient;

    public HttpRepository(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Host getHost(String hostName) throws IOException {
        SingleHostStatusPage page = new SingleHostStatusPage(httpClient, hostName);
        Host host = new HostFactory(page).buildHost();
        return host;
    }
}