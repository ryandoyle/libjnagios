package net.ryandoyle.libjnagios.builder;

import net.ryandoyle.libjnagios.domain.Service;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ServiceBuilder {

    private final Element serviceLinkElement;
    private final Service service;

    public ServiceBuilder(Element serviceLinkElement){
        this.serviceLinkElement = serviceLinkElement;
        this.service = new Service();
    }

    public static Service build(Element serviceLinkElement){
        return new ServiceBuilder(serviceLinkElement).buildService();
    }

    private Service buildService(){
        Elements serviceRow = getServiceRowElements();
        service.setName(serviceRow.get(1).text());
        service.setStatus(serviceRow.get(2).text());
        service.setLastCheck(serviceRow.get(3).text());
        service.setDuration(serviceRow.get(4).text());
        service.setAttempt(serviceRow.get(5).text());
        service.setStatusInformation(serviceRow.get(6).text());
        return service;
    }

    private Elements getServiceRowElements() {
        /* FIXME: find a better way to get the row that the service is in */
        return serviceLinkElement.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().children();
    }

}
