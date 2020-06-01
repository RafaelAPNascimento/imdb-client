package com.imdb.resource.proxy;

import com.imdb.service.httpclient.Connection;

import java.io.IOException;

public final class IMDB_ProxyServer
{
    private static Connection connection;
    private static IMDB_ProxyServer proxyInstance;

    public static final String BASE_URI = "https://imdb-api.com/eng/API/";

    private IMDB_ProxyServer() throws IOException
    {
        connection = Connection.getInstance();
    }

    public static IMDB_ProxyServer getInstance() throws IOException
    {
        if (proxyInstance == null)
        {
            synchronized (IMDB_ProxyServer.class)
            {
                proxyInstance = new IMDB_ProxyServer();
            }
        }
        return proxyInstance;
    }

    public void start() throws IOException
    {
        connection.connect();
    }

    public void stop() throws IOException
    {
        connection.close();
    }


}
