package com.imdb.tests;

import com.imdb.tests.categories.UnitTests;
import com.imdb.service.httpclient.Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
import static com.imdb.resource.proxy.Configuration.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.imdb.resource.proxy.Configuration.IMDB_CLIENT_PROPERTIES;

@Category(UnitTests.class)
@RunWith(JUnit4.class)
public class ConnectionTest
{
    private Connection connection;
    private Properties properties;

    @Before
    public void init() throws IOException
    {
        connection = Connection.getInstance();

        InputStream is = ClassLoader.getSystemResourceAsStream(IMDB_CLIENT_PROPERTIES);
        properties = new Properties();
        properties.load(is);
    }

    @After
    public void end()
    {
        connection.close();
    }

    @Test
    public void assertTcpPort()
    {
        int port = Integer.parseInt(properties.getProperty(IMDB_PROXY_PORT));
        assertEquals(connection.getServerPort(), port);
    }

    @Test
    public void assertSingleton() throws IOException
    {
        Connection connection2 = Connection.getInstance();
        assertEquals(connection, connection2);
    }


}
