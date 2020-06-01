package com.imdb.main;

import com.imdb.resource.proxy.IMDB_ProxyServer;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        IMDB_ProxyServer server = IMDB_ProxyServer.getInstance();
        server.start();
    }
}
