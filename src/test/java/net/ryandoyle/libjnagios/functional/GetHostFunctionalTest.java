package net.ryandoyle.libjnagios.functional;

import net.ryandoyle.libjnagios.NagiosClient;
import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.domain.NoHostsFoundException;
import net.ryandoyle.libjnagios.domain.Service;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static net.ryandoyle.libjnagios.matchers.RegexMatcher.matches;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GetHostFunctionalTest {

    public static final int SSH_SERVICE = 4;
    NagiosClient nagiosClient;
    Host host;
    List<Service> services;
    Service sshService;

    @Before
    public void setUp() throws IOException, NoHostsFoundException {
        nagiosClient = new NagiosClient("http://localhost:8088/cgi-bin/nagios3/", "nagiosadmin", "nagiosadmin");
        host = nagiosClient.getHost("localhost");
        services = host.getServices();
        sshService = services.get(SSH_SERVICE);
    }

    @Test
    public void theHostShouldHaveTheNameOfTheRequestedHost() {
        assertThat(host.getName(), is("localhost"));
    }

    @Test(expected = NoHostsFoundException.class)
    public void anExceptionShouldBeRaisedIfTheHostDoesNotExist() throws IOException, NoHostsFoundException {
        nagiosClient.getHost("invalid");
    }

    @Test
    public void theHostsServicesContainAllTheServicesForThatHost(){
        assertThat(services.size(), is(6));
    }

    @Test
    public void theHostHasAnSSHService(){
        assertThat(sshService.getName(), is("SSH"));
    }

    @Test
    public void theHostsSSHServiceHasAStatus(){
        assertThat(sshService.getStatus(), is("OK"));
    }

    @Test
    public void theHostsSSHServiceHasALastCheck(){
        /* EG: 2014-06-08 03:33:00  */
        assertThat(sshService.getLastCheck(), matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$"));
    }

    @Test
    public void theHostsSSHServiceHasADuration(){
        /* 25d 7h 30m 47s */
        assertThat(sshService.getDuration(), endsWith("s"));
    }

    @Test
    public void theHostsSSHServiceHasAnAttempt(){
        assertThat(sshService.getAttempt(), matches("^\\d/\\d$"));
    }

    @Test
    public void theHostsSSHServiceHasStatusInformation(){
        assertThat(sshService.getStatusInformation(), containsString("SSH OK - OpenSSH"));
    }

}