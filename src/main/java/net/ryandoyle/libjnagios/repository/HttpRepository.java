package net.ryandoyle.libjnagios.repository;

import net.ryandoyle.libjnagios.domain.*;
import net.ryandoyle.libjnagios.http.HttpClient;
import net.ryandoyle.libjnagios.page.StatusPage;
import net.ryandoyle.libjnagios.page.SingleHostStatusPage;

import java.io.IOException;
import java.util.List;

public class HttpRepository implements NagiosRepository {

    private final HttpClient httpClient;

    public HttpRepository(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Host getHost(String hostName) throws IOException, UnknownHostException {
        SingleHostStatusPage page = new SingleHostStatusPage(httpClient, hostName);
        Host host = new HostFactory(page).buildHost();
        return host;
    }

    @Override
    public List<Host> getAllHosts() throws IOException, NoHostsFoundException {
        StatusPage page = new StatusPage(httpClient);
        return new HostsFactory(page).buildHosts();
    }
}