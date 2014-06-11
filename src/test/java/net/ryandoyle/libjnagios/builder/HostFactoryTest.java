package net.ryandoyle.libjnagios.builder;

import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.domain.HostFactory;
import net.ryandoyle.libjnagios.domain.Service;
import net.ryandoyle.libjnagios.domain.UnknownHostException;
import net.ryandoyle.libjnagios.page.SingleHostStatusPage;
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

public class HostFactoryTest  {

    @Mock
    SingleHostStatusPage page;

    @Before
    public void setup(){
        initMocks(this);
        when(page.getHostname()).thenReturn("localhost");
        when(page.getHostServices()).thenReturn(buildServices());
    }

    @Test
    public void itShouldCreateAHostWithTheHostnameFromThePage() throws UnknownHostException {
        Host host = new HostFactory(page).buildHost();
        assertThat(host.getName(), is("localhost"));
    }

    @Test(expected = UnknownHostException.class)
    public void itShouldRaiseAnExceptionIfTheHostOnThePageDoesNotExist() throws UnknownHostException {
        when(page.getHostname()).thenReturn("");
        new HostFactory(page).buildHost();
    }

    @Test
    public void theHostShouldBePopulatedWithServices() throws UnknownHostException {
        Host host = new HostFactory(page).buildHost();
        assertThat(host.getServices().get(0), isA(Service.class));
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