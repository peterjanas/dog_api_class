package dk.lyngby.routes;


import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.DogController;
import dk.lyngby.controller.DogControllerImpl;
import dk.lyngby.dao.DogDaoImpl;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class DogRoute {
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private final DogDaoImpl dogDao = new DogDaoImpl(emf);
    private final DogController dogController = new DogControllerImpl(dogDao);

    public EndpointGroup getDogRoutes() {
        return () -> {
            get("/", dogController::getAllDogs);
            get("/{id}", dogController::getDogById);
            post("/", dogController::createDog);
            put("/{id}", dogController::updateDog);
            delete("/{id}", dogController::deleteDog);
        };
    }
}
