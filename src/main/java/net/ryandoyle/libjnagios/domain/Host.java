package net.ryandoyle.libjnagios.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Host {

    private final String name;
    private final List<Service> services;

    public Host(String name){
        this.name = name;
        this.services = new ArrayList<Service>();
    }

    public String getName() {
        return name;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public List<Service> getServices() {
        return services;
    }

}
