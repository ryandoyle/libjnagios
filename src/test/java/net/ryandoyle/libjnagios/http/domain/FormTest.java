package net.ryandoyle.libjnagios.http.domain;

import net.ryandoyle.libjnagios.http.domain.Form;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class FormTest {

    Form form;

    @Before
    public void setUp() throws Exception {
        form = new Form();
    }

    @Test
    public void itCanBeRepresentedAsAString(){
        form.add("derp", "erp").add("foops", "bar");
        assertThat(form.toString(), is("derp=erp&foops=bar"));
    }

    @Test
    public void itCorrectlyEncodesCharactersForFormEncoding(){
        form.add("mykey", "my value : &");
        assertThat(form.toString(), is("mykey=my+value+%3A+%26"));
    }

    @Test
    public void itContainsTheContentLength(){
        form.add("key", "val");
        assertThat(form.getContentLength(), is(7));
    }
}