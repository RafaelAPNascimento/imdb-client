package com.imdb.resource.proxy;

import lombok.Getter;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class Configuration
{
    private static final Logger LOGGER = Logger.getLogger(Configuration.class);

    public static final String IMDB_CLIENT_PROPERTIES = "imdb-client.properties";
    public static final String IMDB_PROXY_PORT = "imdb.proxy.port";
    public static final String IMDB_PROXY_NUMBER_THREADS = "imdb.proxy.numberthreads";
    public static final String IMDB_CLIENT_APIKEY = "imdb.client.apikey";

    private final int tcpPort;
    private final int numberOfThreads;
    private final String apiKey;

    public Configuration() throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream(IMDB_CLIENT_PROPERTIES);

        Properties properties = new Properties();
        properties.load(is);

        tcpPort = Integer.parseInt(properties.getProperty(IMDB_PROXY_PORT, "8181"));
        apiKey = properties.getProperty(IMDB_CLIENT_APIKEY);

        if (apiKey == null)
            throw new NullPointerException(String.format("propriedade API Key deve ser informada no arquivo %s", IMDB_CLIENT_PROPERTIES));

        if (properties.containsKey(IMDB_PROXY_NUMBER_THREADS))
        {
            numberOfThreads = Integer.parseInt(properties.getProperty(IMDB_PROXY_NUMBER_THREADS));
        }
        else
        {
            numberOfThreads = Runtime.getRuntime().availableProcessors() - 1;
        }

        LOGGER.info(String.format("Listen to port %s", tcpPort));
        LOGGER.info(String.format("Number of threads %s", numberOfThreads));
    }
}
