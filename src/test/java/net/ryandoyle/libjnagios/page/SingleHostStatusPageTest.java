package net.ryandoyle.libjnagios.page;

import net.ryandoyle.libjnagios.http.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SingleHostStatusPageTest {
    @Mock
    private HttpClient initialHttpClient;

    @Mock
    private HttpClient navigatedHttpClient;

    private SingleHostStatusPage page;

    @Before
    public void setUp() throws IOException {
        initMocks(this);
        when(initialHttpClient.navigateTo("/status.cgi?embedded=1&noheader=1&limit=0&host=localhost")).thenReturn(navigatedHttpClient);
        when(navigatedHttpClient.getBody()).thenReturn(PageFixtures.SINGLE_HOST_STATUS_PAGE);
        page = new SingleHostStatusPage(initialHttpClient, "localhost");
    }

    @Test
    public void itShouldOnlyContainASingleHost(){
        assertThat(page.getHostnames().size(), is(1));
    }

}