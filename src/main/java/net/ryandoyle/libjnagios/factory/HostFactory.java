package net.ryandoyle.libjnagios.factory;


import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.domain.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HostFactory {

    private final Document document;
    private Host host;

    public HostFactory(String html) {
        this.document = Jsoup.parse(html);
    }

    public Host build() {
        host = new Host(getHostname());
        addServicesToHost();
        return host;
    }

    private String getHostname() {
        return document.select("a[href~=extinfo\\.cgi\\?type\\=1]").first().html();
    }

    private void addServicesToHost() {
        for(Element element : serviceLinkElements()){
            host.addService(ServiceFactory.build(element));
        }
    }

    private Elements serviceLinkElements() {
        return document.select("a[href~=^extinfo.cgi\\?type=2&host=" + host.getName() + "]");
    }

}
