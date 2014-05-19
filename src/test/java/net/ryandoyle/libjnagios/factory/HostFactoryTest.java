package net.ryandoyle.libjnagios.factory;

import net.ryandoyle.libjnagios.domain.Host;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HostFactoryTest extends BaseHtmlTestFactory {

    Host host;

    @Before
    public void setup(){
        host = new HostFactory(html).build();
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