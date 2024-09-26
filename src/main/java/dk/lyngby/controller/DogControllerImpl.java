package dk.lyngby.controller;

import dk.lyngby.dao.DogDaoImpl;
import dk.lyngby.dto.DogDto;
import dk.lyngby.model.Dog;
import io.javalin.http.Context;

import java.util.List;


public class DogControllerImpl implements DogController {

    private final DogDaoImpl dogDaoImpl;

    public DogControllerImpl(DogDaoImpl dogDaoImpl) {
        this.dogDaoImpl = dogDaoImpl;
    }

    @Override
    public void getDogById(Context ctx)  {
        // == request ==
        long id = Long.parseLong(ctx.pathParam("id"));

        // == querying ==
        Dog dog = dogDaoImpl.getDogById(id);

        // == response ==
        DogDto dogDto = new DogDto(dog);
        ctx.res().setStatus(200);
        ctx.json(dogDto, DogDto.class);
    }

    @Override
    public void getAllDogs(Context ctx) {
        // == querying ==
        List<Dog> dogs = dogDaoImpl.getAllDogs();

        // == response ==
        List<DogDto> dogDtos = DogDto.toDogDTOList(dogs);
        ctx.res().setStatus(200);
        ctx.json(dogDtos, DogDto.class);
    }

    @Override
    public void createDog(Context ctx) {
            // == request ==
            DogDto dogDto = ctx.bodyAsClass(DogDto.class);

            // == querying ==
            Dog newDog = new Dog(dogDto);
            dogDaoImpl.createDog(newDog);

            // == response ==
            ctx.res().setStatus(201);
    }

    @Override
    public void updateDog(Context ctx) {
        // == request ==
        long id = Long.parseLong(ctx.pathParam("id"));
        DogDto dogDto = ctx.bodyAsClass(DogDto.class);

        // == querying ==
        Dog dog = dogDaoImpl.getDogById(id);
        dogDaoImpl.updateDog(dog, new Dog(dogDto));

        // == response ==
        ctx.res().setStatus(200);
    }

    @Override
    public void deleteDog(Context ctx) {
        // == request ==
        long id = Long.parseLong(ctx.pathParam("id"));

        // == querying ==
        Dog dog = dogDaoImpl.getDogById(id);

        // == response ==
        dogDaoImpl.deleteDog(id);
        ctx.res().setStatus(204);
    }
}
