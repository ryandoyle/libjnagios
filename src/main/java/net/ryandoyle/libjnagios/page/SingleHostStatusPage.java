package net.ryandoyle.libjnagios.page;

import net.ryandoyle.libjnagios.http.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingleHostStatusPage extends StatusPage {

    private final String host;

    public SingleHostStatusPage(HttpClient httpClient, String host) throws IOException {
        super(httpClient, "&host=" + host);
        this.host = host;
    }

    public String getHostname() {
        // FIXME: refactor to throw an "InvalidHostElement" exception or similar
        Elements elements = document.select("a[href~=extinfo\\.cgi\\?type\\=1]");
        return elements.isEmpty() ? "" : elements.first().text();
    }

    public List<List<String>> getHostServices(){
        return super.getHostServices(host);
    }
}
