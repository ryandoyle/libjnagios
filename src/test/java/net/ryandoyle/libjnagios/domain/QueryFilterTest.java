package net.ryandoyle.libjnagios.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QueryFilterTest {

    @Test
    public void itShouldBeAnEmptyFilterIfNothingIsSetToBeFiltered(){
        QueryFilter filter = new QueryFilter();
        assertThat(filter.toString(), is(""));

    }

    @Test
    public void itShouldSetTheFilterForASingleHost(){
        QueryFilter filter = new QueryFilter().setHostName("myhost");
        assertThat(filter.toString(), is("host=myhost&"));
    }

}