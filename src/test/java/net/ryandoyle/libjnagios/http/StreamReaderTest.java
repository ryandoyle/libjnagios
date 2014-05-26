package net.ryandoyle.libjnagios.http;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StreamReaderTest {

    @Test
    public void itShouldReturnAStringFromAnInputStream(){
        InputStream stream = new ByteArrayInputStream("test\nstream".getBytes());
        StreamReader streamReader = new StreamReader(stream);

        assertThat(streamReader.read(), is("test\nstream"));
    }

}