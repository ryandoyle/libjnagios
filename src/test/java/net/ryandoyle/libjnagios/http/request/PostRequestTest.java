package net.ryandoyle.libjnagios.http.request;

import net.ryandoyle.libjnagios.http.domain.Form;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PostRequestTest {

    @Mock
    HttpURLConnection connection;

    ByteArrayOutputStream outputSteam;

    PostRequest request;
    Form form;

    @Before
    public void setUp() throws IOException {
        initMocks(this);
        form = new Form();
        form.add("key", "val");
        outputSteam = new ByteArrayOutputStream();
        request = new PostRequest(connection, form);
    }

    @Test
    public void itPostsTheFormDataToTheConnection() throws IOException {
        when(connection.getOutputStream()).thenReturn(outputSteam);
        request.postForm();
        assertThat(outputSteam.toString(), is("key=val"));
    }

    @Test
    public void itSetsTheConnectionInOutputMode() {
        verify(connection).setDoOutput(true);
    }

    @Test
    public void itDoesNotUseCaching() {
        verify(connection).setUseCaches(false);
    }

    @Test
    public void itSetsTheCorrectMIMEEncodingForTheRequest(){
        verify(connection).setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    }


}