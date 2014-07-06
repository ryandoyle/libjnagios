package net.ryandoyle.libjnagios.repository;

import net.ryandoyle.libjnagios.domain.*;
import net.ryandoyle.libjnagios.http.HttpClient;
import net.ryandoyle.libjnagios.page.StatusPage;

import java.io.IOException;
import java.util.List;

public class HttpRepository implements NagiosRepository {

    private final HttpClient httpClient;

    public HttpRepository(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public List<Host> getHosts() throws IOException, NoHostsFoundException {
        StatusPage page = new StatusPage(httpClient);
        return new HostsFactory(page).buildHosts();
    }

    @Override
    public List<Host> getHosts(QueryFilter queryFilter) throws IOException, NoHostsFoundException {
        StatusPage page = new StatusPage(httpClient, queryFilter);
        return new HostsFactory(page).buildHosts();
    }

}