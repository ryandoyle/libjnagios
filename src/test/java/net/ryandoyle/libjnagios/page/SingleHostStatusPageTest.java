package net.ryandoyle.libjnagios.page;


import net.ryandoyle.libjnagios.http.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SingleHostStatusPageTest {

    @Mock
    HttpClient httpClient;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void itUsesTheCorrectUrlToGetThe() throws IOException {
        String host = "localhost";
        SingleHostStatusPage page = new SingleHostStatusPage(httpClient, host);
        verify(httpClient).navigateTo("/status.cgi?embedded=1&noheader=1&limit=0&host=" + host);
    }

}