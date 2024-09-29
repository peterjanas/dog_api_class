package dk.lyngby.routes;


import io.javalin.apibuilder.EndpointGroup;

import javax.swing.*;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes
{
    private DogRoute dogRoute = new DogRoute();

    public EndpointGroup getApiRoutes()
    {
        return () ->
        {
            path("/dog", dogRoute.getDogRoutes());
        };
    }

}
