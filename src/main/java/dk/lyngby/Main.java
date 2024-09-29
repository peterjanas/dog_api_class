package dk.lyngby;


import dk.lyngby.config.AppConfig;
import dk.lyngby.routes.DogRoute;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main
{
    public static void main(String[] args)
    {

        AppConfig.startServer();


    }
}