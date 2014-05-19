package net.ryandoyle.libjnagios.repository;

import net.ryandoyle.libjnagios.page.CgiConnection;
import net.ryandoyle.libjnagios.domain.Host;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HttpRepository implements NagiosRepository {

    private final CgiConnection cgiConnection;

    public HttpRepository(CgiConnection connection) {
        this.cgiConnection = connection;
    }

    @Override
    public Host getHost(String hostName) {
        return null;
    }
}