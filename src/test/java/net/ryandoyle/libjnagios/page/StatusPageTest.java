package net.ryandoyle.libjnagios.page;


import net.ryandoyle.libjnagios.http.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class StatusPageTest {

    private static final int FIRST = 0;
    @Mock
    private HttpClient initialHttpClient;

    @Mock
    private HttpClient navigatedHttpClient;

    private String rawHtmlPage = PageFixtures.ALL_HOSTS_STATUS_PAGE;

    private StatusPage page;

    private List<String> firstServiceForFirstHost;


    @Before
    public void setUp() throws IOException {
        initMocks(this);
        when(initialHttpClient.navigateTo("/status.cgi?embedded=1&noheader=1&limit=0")).thenReturn(navigatedHttpClient);
        when(navigatedHttpClient.get()).thenReturn(rawHtmlPage);
        page = new StatusPage(initialHttpClient);
        firstServiceForFirstHost = page.getServicesForHost("localhost").get(FIRST);
    }

    @Test
    public void hasTheHostnamesOfAllHostsOnThePage() {
        assertThat(page.getHostnames(), hasItems("localhost", "precise64"));
    }

    @Test
    public void containsTheNameForAService(){
        assertThat(firstServiceForFirstHost.get(StatusPage.SERVICE_NAME), is("Current Load"));
    }

    @Test
    public void containsTheStatusForAService(){
        assertThat(firstServiceForFirstHost.get(StatusPage.SERVICE_STATUS), is("OK"));
    }

    @Test
    public void containsTheLastCheckForAService(){
        assertThat(firstServiceForFirstHost.get(StatusPage.SERVICE_LAST_CHECK), is("2014-06-14 02:40:22"));
    }

    @Test
    public void containsTheDurationForAService(){
        assertThat(firstServiceForFirstHost.get(StatusPage.SERVICE_DURATION), is("29d 1h 3m 13s"));
    }

    @Test
    public void containsTheAttemptForAService(){
        assertThat(firstServiceForFirstHost.get(StatusPage.SERVICE_ATTEMPT), is("1/4"));
    }

    @Test
    public void containsTheStatusInformationForAService(){
        assertThat(firstServiceForFirstHost.get(StatusPage.SERVICE_STATUS_INFORMATION), is("OK - load average: 0.00, 0.01, 0.01"));
    }
}