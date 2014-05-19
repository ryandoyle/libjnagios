package net.ryandoyle.libjnagios.repository;

import org.jsoup.Connection;
import org.mockito.Mock;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class HttpRepositoryTest {

    @Mock
    Connection connection;

    @BeforeTest
    /*void setUp() {
        HttpRepository repository = new HttpRepository(connection);
    }*/

    @Test
    void returnsAListOfAllHosts() {

    }

}