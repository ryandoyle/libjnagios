package net.ryandoyle.libjnagios.http;

import net.ryandoyle.libjnagios.http.domain.Form;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HttpClientTest {

    HttpClient client;

    @Before
    public void setup() throws IOException {
        client = new HttpClient("http://ahost");
    }

    @Test
    public void itNavigatesToThePageSpecified() throws IOException {
        assertThat(client.navigateTo("/a").getUrl(), is("http://ahost/a"));
    }

    @Test
    public void itSupportsConnectingUsingHttps() throws IOException {
        HttpClient secureClient = new HttpClient("https://test");
        assertThat(secureClient.navigateTo("/a").getUrl(), is("https://test/a"));
    }

    @Test
    public void itPostsAForm() throws IOException {
        Form form = new Form();
        form.add("test", "derp");
        HttpClient c = new HttpClient("http://localhost:8088");
        c.navigateTo("/command.cgi").post(form);
    }

}