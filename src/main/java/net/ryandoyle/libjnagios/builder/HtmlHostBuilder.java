package net.ryandoyle.libjnagios.builder;


import net.ryandoyle.libjnagios.domain.Host;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlHostBuilder {

    private final Document document;
    private Host host;

    public HtmlHostBuilder(Document document) {
        this.document = document;
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
            host.addService(ServiceBuilder.build(element));
        }
    }

    private Elements serviceLinkElements() {
        return document.select("a[href~=^extinfo.cgi\\?type=2&host=" + host.getName() + "]");
    }

}
