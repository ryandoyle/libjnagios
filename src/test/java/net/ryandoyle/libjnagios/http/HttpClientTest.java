package net.ryandoyle.libjnagios.http;

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

}