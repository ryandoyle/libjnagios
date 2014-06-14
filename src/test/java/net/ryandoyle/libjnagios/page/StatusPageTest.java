package net.ryandoyle.libjnagios.page;

import net.ryandoyle.libjnagios.http.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.List;

import static net.ryandoyle.libjnagios.page.PageFixtures.ALL_HOSTS_STATUS_PAGE;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class StatusPageTest {

    public static final int FIRST_SERVICE = 0;
    private static final int SECOND_SERVICE = 2;

    private StatusPage page;

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpClient navigatedHttpClient;

    @Before
    public void setUp() throws IOException {
        initMocks(this);
        when(httpClient.navigateTo("/status.cgi?embedded=1&noheader=1&limit=0")).thenReturn(navigatedHttpClient);
        when(navigatedHttpClient.getBody()).thenReturn(ALL_HOSTS_STATUS_PAGE);
        page = new StatusPage(httpClient);
    }

    @Test
    public void containTheHostnamesOfAllHostsOnThePage(){
        assertThat(page.getHosts(), hasItems("localhost", "precise64"));
    }

    @Test
    public void onlyHasTheHostnamesWhenGettingHosts(){
        assertThat(page.getHosts().size(), is(2));
    }

    @Test
    public void containsTheCurrentLoadServiceForTheFirstHost(){
        assertThat(servicesForFirstHost().get(FIRST_SERVICE).get(StatusPage.SERVICE_NAME), is("Current Load"));
    }

    @Test
    public void containsTheDiskSpaceServiceForTheSecondHost(){
        assertThat(servicesForSecondHost().get(SECOND_SERVICE).get(StatusPage.SERVICE_NAME), is("Disk Space"));
    }

    private List<List<String>> servicesForFirstHost(){
        return getServicesForHostWithIndex(0);
    }

    private List<List<String>> servicesForSecondHost(){
        return getServicesForHostWithIndex(1);
    }

    private List<List<String>> getServicesForHostWithIndex(int index){
        return page.getHostServices(page.getHosts().get(index));
    }


}