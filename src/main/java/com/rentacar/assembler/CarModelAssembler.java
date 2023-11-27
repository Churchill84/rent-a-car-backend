package com.rentacar.assembler;

import com.rentacar.controller.CarController;
import com.rentacar.model.Car;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarModelAssembler implements RepresentationModelAssembler<Car, EntityModel<Car>> {

    @Override
    public EntityModel<Car> toModel(Car car) {
        return EntityModel.of(car, //
                linkTo(methodOn(CarController.class).findCarById(car.getCarId())).withSelfRel(),
                linkTo(methodOn(CarController.class).findAll()).withRel("cars"));
    }
}
