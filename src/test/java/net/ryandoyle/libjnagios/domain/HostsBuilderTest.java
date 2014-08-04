package net.ryandoyle.libjnagios.domain;

import net.ryandoyle.libjnagios.page.StatusPage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HostsBuilderTest {

    @Mock
    StatusPage page;

    List<String> hosts;

    @Before
    public void setup(){
        initMocks(this);
        hosts = new ArrayList<String>();
        hosts.add("localhost");
        when(page.getHostnames()).thenReturn(hosts);
        when(page.getServicesForHost("localhost")).thenReturn(buildServices());
    }

    @Test
    public void itShouldCreateAHostWithTheHostnameFromThePage() throws NoHostsFoundException {
        Host host = new HostsBuilder(page).buildHosts().get(0);
        assertThat(host.getName(), is("localhost"));
    }

    @Test(expected = NoHostsFoundException.class)
    public void itShouldRaiseAnExceptionIfTheHostOnThePageDoesNotExist() throws NoHostsFoundException {
        when(page.getHostnames()).thenReturn(anEmptyListOfStrings());
        new HostsBuilder(page).buildHosts();
    }

    @Test
    public void theHostShouldBePopulatedWithServices() throws NoHostsFoundException {
        Host host = new HostsBuilder(page).buildHosts().get(0);
        assertThat(host.getServices().get(0), isA(Service.class));
    }

    private List<String> anEmptyListOfStrings(){
        return new ArrayList<String>();
    }

    private List<List<String>> buildServices(){
        List<List<String>> services = new ArrayList<List<String>>();
        List<String> service = new ArrayList<String>();
        service.add("name");
        service.add("status");
        service.add("check");
        service.add("duration");
        service.add("attempt");
        service.add("info");
        services.add(service);
        return services;
    }

}