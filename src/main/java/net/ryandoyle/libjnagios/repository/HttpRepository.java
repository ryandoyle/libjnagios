package net.ryandoyle.libjnagios.repository;

import net.ryandoyle.libjnagios.builder.HostBuilder;
import net.ryandoyle.libjnagios.page.Page;
import net.ryandoyle.libjnagios.page.PageFactory;
import net.ryandoyle.libjnagios.domain.Host;

import java.io.IOException;

public class HttpRepository implements NagiosRepository {

    private final PageFactory pageFactory;

    public HttpRepository(PageFactory connection) {
        this.pageFactory = connection;
    }

    @Override
    public Host getHost(String hostName) {
        // TODO: Throw an "InvalidHostException" if it is not found
        Page page = null;
        try {
            page = pageFactory.getPageforHost(hostName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HostBuilder(page).build();
    }
}