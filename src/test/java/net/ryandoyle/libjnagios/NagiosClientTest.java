package net.ryandoyle.libjnagios;


import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class NagiosClientTest {

    @Test
    public void aNagiosClientCanBeUsedWithoutAnyAuthentication() throws IOException {
        NagiosClient client = new NagiosClient("url");
        assertNotNull(client);
    }

    @Test
    public void aNagiosClientCanBeUsedWithAnNagiosInstanceThatRequiresAuthentication() throws IOException {
        NagiosClient client = new NagiosClient("url", "username", "password");
        assertNotNull(client);
    }

}