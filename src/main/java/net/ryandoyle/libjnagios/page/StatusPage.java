package net.ryandoyle.libjnagios.page;

import net.ryandoyle.libjnagios.domain.QueryFilter;
import net.ryandoyle.libjnagios.http.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class StatusPage {

    public static final int SERVICE_INFORMATION_COLUMNS = 6;
    public static final int SERVICE_NAME = 0;
    public static final int SERVICE_STATUS = 1;
    public static final int SERVICE_LAST_CHECK = 2;
    public static final int SERVICE_DURATION = 3;
    public static final int SERVICE_ATTEMPT = 4;
    public static final int SERVICE_STATUS_INFORMATION = 5;

    private HttpClient httpClient;

    Document document;

    public StatusPage(HttpClient httpClient, QueryFilter queryFilter) throws IOException {
        this.httpClient = httpClient.navigateTo("/status.cgi?embedded=1&noheader=1&limit=0&" + queryFilter.toString());
        this.document = Jsoup.parse(this.httpClient.get());
    }

    public StatusPage(HttpClient httpClient) throws IOException {
        this.httpClient = httpClient.navigateTo("/status.cgi?embedded=1&noheader=1&limit=0");
        this.document = Jsoup.parse(this.httpClient.get());
    }

    public List<String> getHostnames(){
        List<String> hosts = new ArrayList<String>();
        Elements elements = document.select("a[href~=extinfo\\.cgi\\?type\\=1]").select("a[title]");
        for (Element element : elements) {
            hosts.add(element.text());
        }
        return hosts;
    }

    public List<List<String>> getServicesForHost(String hostname){
        List servicesList = new ArrayList();
        for(Element serviceLink : linksForHostServices(hostname)){
            servicesList.add(getServiceRow(serviceLink));
        }
        return servicesList;
    }

    private List<String> getServiceRow(Element serviceLink) {
        Elements serviceTableRow = getServiceRowColumnsFromServiceLink(serviceLink);
        List row = new ArrayList(SERVICE_INFORMATION_COLUMNS);
        row.add(serviceTableRow.get(SERVICE_NAME).text());
        row.add(serviceTableRow.get(SERVICE_STATUS).text());
        row.add(serviceTableRow.get(SERVICE_LAST_CHECK).text());
        row.add(serviceTableRow.get(SERVICE_DURATION).text());
        row.add(serviceTableRow.get(SERVICE_ATTEMPT).text());
        row.add(serviceTableRow.get(SERVICE_STATUS_INFORMATION).text().replace("\u00a0", ""));
        return row;
    }



    private Elements linksForHostServices(String hostname) {
        return document.select("a[href~=^extinfo.cgi\\?type=2&host=" + hostname + "]");
    }

    private Elements getServiceRowColumnsFromServiceLink(Element serviceLink) {
        /* FIXME: find a better way to get the row that the service is in */
        Elements columnElements = serviceLink.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().children();
        removeHostColumnElement(columnElements);
        return columnElements;
    }

    private void removeHostColumnElement(Elements rowElement) {
        rowElement.remove(0);
    }


}
