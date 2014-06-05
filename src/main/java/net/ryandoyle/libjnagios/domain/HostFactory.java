package net.ryandoyle.libjnagios.domain;


import net.ryandoyle.libjnagios.page.SingleHostStatusPage;

import java.util.List;

public class HostFactory {

    private Host host;
    private SingleHostStatusPage page;

    public HostFactory(SingleHostStatusPage page) {
        this.page = page;
    }

    public Host buildHost() {
        host = new Host(page.getHostname());
        addServicesToHost();
        return host;
    }

    private void addServicesToHost() {
       for (List<String> hostService : page.getHostServices()){
        host.addService(new ServiceFactory(hostService).buildService());
       }
    }

}
