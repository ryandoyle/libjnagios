package net.ryandoyle.libjnagios.factory;

import net.ryandoyle.libjnagios.domain.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ServiceFactoryTest extends BaseHtmlTestFactory {

    private Service service;

    @BeforeTest
    public void setup(){
        Document document = Jsoup.parse(html);
        Element firstServiceLink = document.select("a[href~=^extinfo.cgi\\?type=2&host=localhost]").first();
        service = ServiceFactory.build(firstServiceLink);
    }

    @Test
    public void hasAServiceName() {
        assertThat(service.getName(), is("Current Load"));
    }

    @Test
    public void hasAStatus(){
        assertThat(service.getStatus(), is("OK"));
    }

    @Test
    public void hasALastCheck(){
        assertThat(service.getLastCheck(), is("2014-05-17 05:35:10"));
    }

    @Test
    public void hasTheDurationTheServiceHasBeenInThatState(){
        assertThat(service.getDuration(), is("5d 12h 28m 29s"));
    }

    @Test
    public void hasTheNumberOfAttemptsTheServiceHasBeenInBeforeItChangesState(){
        assertThat(service.getAttempt(), is("1/4"));
    }

    @Test
    public void hasTheServiceInformation(){
        assertThat(service.getStatusInformation(), is("OK - load average: 0.26, 0.28, 0.26 "));
    }

}