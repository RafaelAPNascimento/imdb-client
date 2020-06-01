package com.imdb.tests.integration;

import com.imdb.resource.proxy.Configuration;
import com.imdb.tests.categories.IntegrationTests;
import com.imdb.resource.proxy.handler.SearchMovieByNameHandler;
import org.apache.http.HttpStatus;
import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static io.restassured.RestAssured.given;

@RunWith(JUnit4.class)
@Category(IntegrationTests.class)
public class MovieSearchTest
{
    /*
    Run Integration Tests when Application is Up & Running
     */
    private static final String BASE_URI = "http://localhost:8181/search/movie";

    private static final Logger LOGGER = Logger.getLogger(Configuration.class);

    @Test
    public void shouldReturnOK()
    {
        LOGGER.info("texto de pesquisa >>>>>  terminator");

        given().baseUri(BASE_URI)
                .request().queryParam("title", "terminator")
                .when()
                .get().peek().then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldReturnOK_AndNotFound()
    {
        String message = given().baseUri(BASE_URI)
                            .request().queryParam("title", "kfmkfjkrjkr")
                            .when()
                            .get().peek().then()
                            .assertThat()
                            .statusCode(HttpStatus.SC_OK)
                            .extract().asString();

        Assert.assertEquals(message, SearchMovieByNameHandler.NOT_FOUND_MESSAGE);
    }
}
