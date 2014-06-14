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

    public SingleHostStatusPage(HttpClient httpClient, String host) throws IOException {
        super(httpClient, "&host=" + host);
    }

}
