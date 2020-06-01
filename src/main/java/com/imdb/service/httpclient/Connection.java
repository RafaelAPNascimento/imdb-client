package com.imdb.service.httpclient;

import com.imdb.resource.proxy.Configuration;
import com.imdb.resource.proxy.handler.SearchMovieByNameHandler;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public final class Connection
{
    private static Connection instance;

    private Configuration configuration;

    private HttpServer server;
    private Client httpClient;

    private Connection() throws IOException
    {
        setUpConnection();
    }

    public static Connection getInstance() throws IOException
    {
        if (instance == null)
        {
            synchronized (Connection.class)
            {
                instance = new Connection();
            }
        }
        return instance;
    }

    private void setUpConnection() throws IOException
    {
        configuration = new Configuration();

        server = HttpServer.create(new InetSocketAddress(configuration.getTcpPort()), 0);
        server.createContext("/search/movie", new SearchMovieByNameHandler(this));

        Executor executor = Executors.newFixedThreadPool(configuration.getNumberOfThreads());

        server.setExecutor(executor);

        httpClient = ClientBuilder.newClient();
    }

    public void connect()
    {
        server.start();
    }

    public Client getHttpClient()
    {
        return httpClient;
    }

    public void close()
    {
        server.stop(0);
        httpClient.close();
    }

    public Configuration getConfiguration()
    {
        return configuration;
    }

    public int getServerPort()
    {
        return server.getAddress().getPort();
    }
}
