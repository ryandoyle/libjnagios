package net.ryandoyle.libjnagios.functional;

import net.ryandoyle.libjnagios.NagiosClient;
import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.domain.UnknownHostException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GetAllHostsFunctionalTest {

    private NagiosClient nagiosClient;

    private List<Host> hosts;

    private Host firstHost;

    private Host secondHost;

    @Before
    public void setUp() throws IOException {
        nagiosClient = new NagiosClient("http://localhost:8088/cgi-bin/nagios3/", "nagiosadmin", "nagiosadmin");
        hosts = nagiosClient.getAllHosts();
        firstHost = hosts.get(0);
        secondHost = hosts.get(1);

    }

    @Test
    public void theFirstHostsNameShouldBeLocalhost(){
        assertThat(firstHost.getName(), is("localhost"));
    }

    @Test
    public void theSecondHostsNameShouldBePrecise64(){
        assertThat(secondHost.getName(), is("precise64"));
    }

}
