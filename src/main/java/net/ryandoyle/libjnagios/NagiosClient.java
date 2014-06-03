package net.ryandoyle.libjnagios;


import net.ryandoyle.libjnagios.http.HttpClient;
import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.repository.HttpRepository;
import net.ryandoyle.libjnagios.repository.NagiosRepository;

import java.io.IOException;

public class NagiosClient {

    private final NagiosRepository repository;
    private final HttpClient httpClient;

    public NagiosClient(String url, String userName, String password) throws IOException {
        this.httpClient = new HttpClient(url, userName, password);
        this.repository = new HttpRepository(httpClient);
    }

    public NagiosClient(String url) throws IOException {
        this.httpClient = new HttpClient(url);
        this.repository = new HttpRepository(httpClient);
    }

    public Host getHost(String hostName) throws IOException {
        return repository.getHost(hostName);
    }

}
