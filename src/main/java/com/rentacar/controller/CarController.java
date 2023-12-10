package com.rentacar.controller;

import com.rentacar.assembler.CarModelAssembler;
import com.rentacar.model.Car;
import com.rentacar.model.User;
import com.rentacar.request.CarRequest;
import com.rentacar.service.CarService;
import com.rentacar.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    Logger logger = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;
    private final CarModelAssembler carModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Car>> findAll() {

        List<EntityModel<Car>> cars = carService.getAllCars().stream()
                .map(carModelAssembler::toModel)
                .toList();

        return CollectionModel.of(cars, linkTo(methodOn(CarController.class).findAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Car> findCarById(@PathVariable Long id) {
        return carModelAssembler.toModel(carService.getCarById(id));
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<EntityModel<Car>> updateCar(
            @PathVariable Long id,
            @ModelAttribute CarRequest carRequest,
            @RequestParam(required = false) MultipartFile carImage) throws IOException {

        logger.debug("Received car update request: {}, {}", carRequest, carImage);

        User user = UserUtils.getUser();

        if (carImage != null && !carImage.isEmpty()) {
            carRequest.setImageUrl(carImage);
        }

        Optional<Car> updated = Optional.ofNullable(carService.updateCar(id, carRequest, user.getId()));

        return updated
                .map(admin -> {
                    EntityModel<Car> entityModel = carModelAssembler.toModel(admin);
                    return ResponseEntity.ok(entityModel);
                })
                .orElseGet(() -> {

                    EntityModel<Car> entityModel = null;
                    try {
                        entityModel = carModelAssembler.toModel(carService.createCar(carRequest, user.getId()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
                });
    }

    @PostMapping(consumes = {"multipart/form-data"})
    ResponseEntity<EntityModel<Car>> newCar(
            @ModelAttribute CarRequest carRequest,
            @RequestParam(required = false) MultipartFile carImage) throws IOException {

        User user = UserUtils.getUser();

        if (carImage != null && !carImage.isEmpty()) {
            carRequest.setImageUrl(carImage);
        }

        EntityModel<Car> entityModel = carModelAssembler.toModel(carService.createCar(carRequest, user.getId()));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
