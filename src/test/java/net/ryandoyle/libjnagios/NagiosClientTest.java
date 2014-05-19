package net.ryandoyle.libjnagios;


import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NagiosClientTest {

    @Test
    public void aNagiosClientCanBeUsedWithoutAnyAuthentication() {
        NagiosClient client = new NagiosClient("url");
        assertNotNull(client);
    }

    @Test
    public void aNagiosClientCanBeUsedWithAnNagiosInstanceThatRequiresAuthentication(){
        NagiosClient client = new NagiosClient("url", "username", "password");
        assertNotNull(client);
    }

}