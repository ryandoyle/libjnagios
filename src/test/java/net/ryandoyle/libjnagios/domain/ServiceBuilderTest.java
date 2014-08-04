package net.ryandoyle.libjnagios.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ServiceBuilderTest {

    Service service;

    List<String> serviceAttributes;

    @Before
    public void setUp(){
        serviceAttributes = new ArrayList<String>();
        serviceAttributes.add("My Service Name");
        serviceAttributes.add("OK");
        serviceAttributes.add("1970-1-1 1:23:45");
        serviceAttributes.add("1d 1h 1m 1s");
        serviceAttributes.add("1/4");
        serviceAttributes.add("My service is operating normally!");
        service = new ServiceBuilder(serviceAttributes).buildService();
    }

    @Test
    public void shouldCreateAServiceWithAServiceName(){
        assertThat(service.getName(), is("My Service Name"));
    }

    @Test
    public void shouldCreateAServiceWithAStatus(){
        assertThat(service.getStatus(), is("OK"));
    }

    @Test
    public void shouldCreateAServiceWithTheLastCheck(){
        assertThat(service.getLastCheck(), is("1970-1-1 1:23:45"));
    }

    @Test
    public void shouldCreateAServiceWithTheDuration(){
        assertThat(service.getDuration(), is("1d 1h 1m 1s"));
    }

    @Test
    public void shouldCreateAServiceWithAnAttempt(){
        assertThat(service.getAttempt(), is("1/4"));
    }

    @Test
    public void shouldCreateAServiceWithStatusInformation(){
        assertThat(service.getStatusInformation(), is("My service is operating normally!"));
    }

}