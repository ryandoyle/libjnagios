package net.ryandoyle.libjnagios;


import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class NagiosClientTest {

    @Test
    public void aNagiosClientCanBeUsedWithoutAnyAuthentication() throws IOException {
        NagiosClient client = new NagiosClient("http://test");
        assertNotNull(client);
    }

    @Test
    public void aNagiosClientCanBeUsedWithAnNagiosInstanceThatRequiresAuthentication() throws IOException {
        NagiosClient client = new NagiosClient("http://test", "username", "password");
        assertNotNull(client);
    }

}