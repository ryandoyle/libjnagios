package net.ryandoyle.libjnagios.domain;

import net.ryandoyle.libjnagios.page.StatusPage;

import java.util.ArrayList;
import java.util.List;

public class HostsBuilder {

    private final StatusPage page;

    public HostsBuilder(StatusPage page){
        this.page = page;
    }

    public List<Host> buildHosts() throws NoHostsFoundException {
        if(thePageContainsNoHosts()){
            throw new NoHostsFoundException();
        }
        List<Host> hosts = new ArrayList<Host>();
        for (String hostname : this.page.getHostnames()){
            hosts.add(buildHostWithHostname(hostname));
        }
        return hosts;
    }

    private boolean thePageContainsNoHosts() {
        return this.page.getHostnames().isEmpty();
    }

    private Host buildHostWithHostname(String hostname){
        Host host = new Host(hostname);
        addServicesForHost(host);
        return host;
    }

    private void addServicesForHost(Host host){
        for(List<String> service : page.getServicesForHost(host.getName())){
            host.addService(new ServiceBuilder(service).buildService());
        }
    }

}
