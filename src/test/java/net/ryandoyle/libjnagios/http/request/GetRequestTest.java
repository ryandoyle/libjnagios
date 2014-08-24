package net.ryandoyle.libjnagios.http.request;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetRequestTest {

    @Mock
    HttpURLConnection connection;

    GetRequest request;

    @Before
    public void setUp(){
        initMocks(this);
        request = new GetRequest(connection);
    }

    @Test
    public void itReturnsTheResponseBodyOfARequest() throws IOException {
        InputStream stream = new ByteArrayInputStream("<html>foops</html>".getBytes());
        when(connection.getInputStream()).thenReturn(stream);
        assertThat(request.getResponseBody(), is("<html>foops</html>"));
    }

    @Test
    public void itReturnsTheStatusCodeOfTheRequest() throws IOException {
        when(connection.getResponseCode()).thenReturn(100);
        assertThat(request.getResponseCode(), is(100));
    }




}