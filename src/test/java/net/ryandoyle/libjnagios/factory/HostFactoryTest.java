package net.ryandoyle.libjnagios.factory;

import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.domain.Service;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyListOf;

public class HostFactoryTest extends BaseHtmlTestFactory {

    Host host;

    @BeforeTest
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