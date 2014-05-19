package net.ryandoyle.libjnagios;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class HttpClientTest {

    @Test
    public void itShouldConnectWithBasicAuthenticationIfConstructedWithAUsernameAndPassword(){
        HttpClient client = new HttpClient("http://foops", "myuser", "mypassword");
        assertThat(client.getHeaders().get("Authorization"), is("Basic bXl1c2VyOm15cGFzc3dvcmQ="));
    }

    @Test
    public void itDoesNotConnectWithBasicAuthenticationIfJustConstructedWithTheUrl(){
        HttpClient client = new HttpClient("http://foops");
        assertThat(client.getHeaders().get("Authorization"), is(nullValue()));
    }
}