package net.ryandoyle.libjnagios.builder;

import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.domain.HostFactory;
import net.ryandoyle.libjnagios.page.SingleHostStatusPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HostFactoryTest  {

    @Mock
    SingleHostStatusPage page;

    Host host;

    @Before
    public void setup(){
        initMocks(this);
        when(page.getHostname()).thenReturn("localhost");
        when(page.getHostServices()).thenReturn(new ArrayList<List<String>>());
        host = new HostFactory(page).buildHost();
    }

    @Test
    public void itShouldCreateAHostFromAValidHtmlString(){
        assertThat(host.getName(), is("localhost"));
    }

    @Test
    public void theHostShouldBePopulatedWithServices(){
        assertThat(host.getServices().isEmpty(), is(false));
    }

}