package com.imdb.tests;

import com.imdb.tests.categories.UnitTests;
import com.imdb.resource.proxy.Configuration;
import static com.imdb.resource.proxy.Configuration.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RunWith(JUnit4.class)
@Category(UnitTests.class)
public class ConfigurationTest
{
    private Configuration configuration;
    private Properties properties;

    @Before
    public void init() throws IOException
    {
        configuration = new Configuration();

        InputStream is = ClassLoader.getSystemResourceAsStream(IMDB_CLIENT_PROPERTIES);
        properties = new Properties();
        properties.load(is);
    }

    @Test
    public void assertApiKey()
    {
        assertEquals(configuration.getApiKey(), properties.getProperty(IMDB_CLIENT_APIKEY));
    }

    @Test
    public void assertNumberOfThreads()
    {
        int threads = Integer.parseInt(properties.getProperty(IMDB_PROXY_NUMBER_THREADS));
        assertEquals(configuration.getNumberOfThreads(), threads);
    }

    @Test
    public void assertServerTcpPort()
    {
        int tcpPort = Integer.parseInt(properties.getProperty(IMDB_PROXY_PORT));
        assertEquals(configuration.getTcpPort(), tcpPort);
    }
}
