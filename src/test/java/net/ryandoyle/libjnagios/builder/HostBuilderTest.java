package net.ryandoyle.libjnagios.builder;

import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.page.SingleHostStatusPage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HostBuilderTest extends BaseHtmlTestFactory {

    Host host;

    @Mock
    SingleHostStatusPage singleHostStatusPage;

    @Before
    public void setup(){
        initMocks(this);
        when(singleHostStatusPage.toString()).thenReturn(html);
        host = new HostBuilder(singleHostStatusPage).build();
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