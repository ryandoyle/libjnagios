package net.ryandoyle.libjnagios;


import net.ryandoyle.libjnagios.page.CgiConnection;
import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.repository.HttpRepository;
import net.ryandoyle.libjnagios.repository.NagiosRepository;

public class NagiosClient {


    private final NagiosRepository repository;
    private final CgiConnection connection;

    public NagiosClient(String url, String userName, String password) {

        this.connection = new CgiConnection(url, userName, password);
        this.repository = new HttpRepository(connection);
    }

    public NagiosClient(String url) {
        this.connection = new CgiConnection(url);
        this.repository = new HttpRepository(connection);
    }

    public Host getHost(String hostName) {
        return repository.getHost(hostName);
    }



    //  new NagiosClient('http://foops.com/nagios', 'u', 'p').host('app1.example.com')
    //  new NagiosClient('http://foops.com/nagios', 'u', 'p').getHosts(new HostFilter().byName('app1.syd.acx'))

}
