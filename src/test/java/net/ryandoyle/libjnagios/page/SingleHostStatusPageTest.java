package net.ryandoyle.libjnagios.page;


import net.ryandoyle.libjnagios.http.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SingleHostStatusPageTest {

    private static final int FIRST = 0;
    @Mock
    private HttpClient initialHttpClient;

    @Mock
    private HttpClient navigatedHttpClient;

    private String rawHtmlPage = PageFixtures.SINGLE_HOST_STATUS_PAGE;

    private String testHostname = "localhost";

    private SingleHostStatusPage page;

    private List<String> firstService;


    @Before
    public void setUp() throws IOException {
        initMocks(this);
        when(initialHttpClient.navigateTo("/status.cgi?embedded=1&noheader=1&limit=0&host=" + testHostname)).thenReturn(navigatedHttpClient);
        when(navigatedHttpClient.getBody()).thenReturn(rawHtmlPage);
        page = new SingleHostStatusPage(initialHttpClient, testHostname);
        firstService = page.getHostServices().get(FIRST);
    }

    @Test
    public void hasTheHostnameOfTheHostOnThePage() throws IOException {
        assertThat(page.getHostname(), is(testHostname));
    }

    @Test
    public void containsTheNameForAService(){
        assertThat(firstService.get(SingleHostStatusPage.SERVICE_NAME), is("Current Load"));
    }

    @Test
    public void containsTheStatusForAService(){
        assertThat(firstService.get(SingleHostStatusPage.SERVICE_STATUS), is("OK"));
    }

    @Test
    public void containsTheLastCheckForAService(){
        assertThat(firstService.get(SingleHostStatusPage.SERVICE_LAST_CHECK), is("2014-05-17 05:35:10"));
    }

    @Test
    public void containsTheDurationForAService(){
        assertThat(firstService.get(SingleHostStatusPage.SERVICE_DURATION), is("5d 12h 28m 29s"));
    }

    @Test
    public void containsTheAttemptForAService(){
        assertThat(firstService.get(SingleHostStatusPage.SERVICE_ATTEMPT), is("1/4"));
    }

    @Test
    public void containsTheStatusInformationForAService(){
        assertThat(firstService.get(SingleHostStatusPage.SERVICE_STATUS_INFORMATION), is("OK - load average: 0.26, 0.28, 0.26 "));
    }
}