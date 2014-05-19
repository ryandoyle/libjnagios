package net.ryandoyle.libjnagios.repository;

import org.jsoup.Connection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;


public class HttpRepositoryTest {

    @Mock
    Connection connection;

    @Before
    /*void setUp() {
        HttpRepository repository = new HttpRepository(connection);
    }*/

    @Test
    void returnsAListOfAllHosts() {

    }

}