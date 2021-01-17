package com.imdb.resource.proxy.handler;

import static com.imdb.resource.proxy.IMDB_ProxyServer.*;

import com.imdb.model.JsonUtil;
import com.imdb.model.MovieTitleSearch;
import com.imdb.service.httpclient.Connection;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.OutputStream;

public class SearchMovieByNameHandler implements HttpHandler
{
    private Connection connection;

    private final String TITLE_URI;

    public static final String NOT_FOUND_MESSAGE = "No results.";

    public SearchMovieByNameHandler(Connection connection)
    {
        this.connection = connection;
        TITLE_URI = BASE_URI.concat("SearchMovie/").concat(connection.getConfiguration().getApiKey());
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        String query = exchange.getRequestURI().getQuery();

        String[] keyValue = query.split("=");

        String action = keyValue[0];
        String movieTitle = keyValue[1];

        String response = null;
        int status;

        if (action == null || !action.equals("title"))
        {
            response = "query param title e obrigatorio";
            status = 400;
        }
        else
        {
            status = 200;
            MovieTitleSearch movieTitleSearch = executeRequest(movieTitle);

            if (movieTitleSearch == null || movieTitleSearch.getMovies().isEmpty())
                response = NOT_FOUND_MESSAGE;

            else
                response = movieTitleSearch.getMovies().stream()
                                .map(movie -> movie.getTitle())
                                .reduce("", (movie1, next) -> movie1.concat(next.concat("\n- ")), String::concat);
        }

        response.concat("\n>>>>>>>>>>>>>>>> end results");
        byte[] resp = response.getBytes();
        exchange.sendResponseHeaders(status, resp.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(resp);
        outputStream.close();
    }

    private MovieTitleSearch executeRequest(String movieTitle)
    {
        Client client = connection.getHttpClient();
        WebTarget target = client.target(TITLE_URI.concat(String.format("/%s", movieTitle)));
        Invocation invocation = target.request(MediaType.TEXT_PLAIN).buildGet();
        Response response = invocation.invoke();

        String entityResponse = response.readEntity(String.class);
        MovieTitleSearch result = JsonUtil.getPojo(entityResponse, MovieTitleSearch.class);

        return result;
    }
}
