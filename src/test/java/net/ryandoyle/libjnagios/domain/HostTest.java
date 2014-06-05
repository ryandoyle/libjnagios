package net.ryandoyle.libjnagios.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;


public class HostTest {

    Service service1;
    Host host;

    @Before
    public void beforeTest() {
        service1 = new Service("SSH");
        host = new Host("testhost.example.com");

    }

    @Test
    public void itShouldBeAbleToAddServicesToAHost() throws Exception {
        host.addService(service1);
        assertThat(host.getServices(), hasItem(service1));
    }
}