package net.ryandoyle.libjnagios.domain;

import net.ryandoyle.libjnagios.page.StatusPage;

import java.util.ArrayList;
import java.util.List;

public class HostsFactory {

    private final StatusPage page;

    public HostsFactory(StatusPage page){
        this.page = page;
    }

    public List<Host> buildHosts() throws NoHostsFoundException {
        if(thePageContainsNoHosts()){
            throw new NoHostsFoundException();
        }
        List<Host> hosts = new ArrayList<Host>();
        for (String host : this.page.getHosts()){
            hosts.add(buildHost(host));
        }
        return hosts;
    }

    private boolean thePageContainsNoHosts() {
        return this.page.getHosts().isEmpty();
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
