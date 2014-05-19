package net.ryandoyle.libjnagios;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


/*

http://localhost:8088/cgi-bin/nagios3/status.cgi?embedded=1&noheader=1&limit=0&host=localhost



 */

public class TestSoup {
    public static void main(String[] args) throws IOException {

        String login = "nagiosadmin:nagiosadmin";
        String basicAuthLogin = new String(Base64.encodeBase64(login.getBytes()));


        Document document = Jsoup.connect("http://localhost:8088/cgi-bin/nagios3/status.cgi?embedded=1&noheader=1&limit=0&host=localhost").header("Authorization", "Basic " + basicAuthLogin).get();
        Elements elements = document.select("");
        System.out.println(document.toString());

    }
}
