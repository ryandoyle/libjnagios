package net.ryandoyle.libjnagios.page;

import net.ryandoyle.libjnagios.builder.HtmlHostBuilder;
import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.http.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class SingleHostStatusPage {

    private final HttpClient httpClient;
    private final Document document;

    public SingleHostStatusPage(HttpClient httpClient, String host) throws IOException {
        this.httpClient = httpClient.navigateTo("/status.cgi?embedded=1&noheader=1&limit=0&host=" + host);
        this.document = Jsoup.parse(this.httpClient.getBody());
    }

    public Host getHost() throws IOException {
        return new HtmlHostBuilder(document).build();
    }

}
