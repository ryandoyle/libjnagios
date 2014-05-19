package net.ryandoyle.libjnagios;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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