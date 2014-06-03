package net.ryandoyle.libjnagios.builder;

import net.ryandoyle.libjnagios.domain.Host;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class HtmlHostBuilderTest extends BaseHtmlTestFactory {

    Host host;

    @Before
    public void setup(){
        initMocks(this);
        host = new HtmlHostBuilder(html).build();
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