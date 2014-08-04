package net.ryandoyle.libjnagios.domain;


import java.util.List;

public class ServiceBuilder {

    private final List<String> serviceAttributes;
    private Service service;

    public ServiceBuilder(List<String> serviceAttributes){
        this.serviceAttributes = serviceAttributes;
    }

    public Service buildService() {
        // FIXME: Share the service indexes or move creation somewhere else
        service = new Service(serviceAttributes.get(0));
        service.setStatus(serviceAttributes.get(1));
        service.setLastCheck(serviceAttributes.get(2));
        service.setDuration(serviceAttributes.get(3));
        service.setAttempt(serviceAttributes.get(4));
        service.setStatusInformation(serviceAttributes.get(5));
        return service;
    }
}
