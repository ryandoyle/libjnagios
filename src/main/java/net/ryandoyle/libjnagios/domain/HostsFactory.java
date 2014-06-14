package net.ryandoyle.libjnagios.domain;

import net.ryandoyle.libjnagios.page.AllHostsStatusPage;

import java.util.ArrayList;
import java.util.List;

public class HostsFactory {

    private final AllHostsStatusPage page;

    public HostsFactory(AllHostsStatusPage page){
        this.page = page;
    }

    public List<Host> buildHosts() {
        List<Host> hosts = new ArrayList<Host>();
        for (String host : this.page.getHosts()){
            hosts.add(buildHost(host));
        }
        return hosts;
    }

    private Host buildHost(String hostname){
        Host host = new Host(hostname);
        addServicesForHost(host);
        return host;
    }

    private void addServicesForHost(Host host){
        for(List<String> service : page.getHostServices(host.getName())){
            host.addService(new ServiceFactory(service).buildService());
        }
    }

}
