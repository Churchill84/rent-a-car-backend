package com.rentacar.implementation;

import com.rentacar.exception.CarNotFoundException;
import com.rentacar.model.Car;
import com.rentacar.repo.CarRepository;
import com.rentacar.request.CarRequest;
import com.rentacar.service.CarService;
import com.rentacar.util.CarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    }

    @Override
    public Car createCar(CarRequest request, long userId) throws IOException {
        var car = Car.builder()
                .carName(request.getCarName())
                .description(request.getDescription())
                .carModelYear(request.getCarModelYear())
                .carBrand(request.getCarBrand())
                .color(request.getColor())
                .capacity(request.getCapacity())
                .plateNumber(request.getPlateNumber())
                .rate(request.getRate())
                .carStatus(request.getCarStatus())
                .createDate(LocalDateTime.now())
                .lastModified(LocalDateTime.now())
                .createdBy(userId)
                .imagePath(CarUtils.downloadAndStoreImage(request.getImageUrl()))
                .build();
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, CarRequest request, long userId) throws IOException {
        // Find the existing car by id
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));

        // Update car details with request data
        car.setCarName(request.getCarName());
        car.setDescription(request.getDescription());
        car.setCarModelYear(request.getCarModelYear());
        car.setCarBrand(request.getCarBrand());
        car.setColor(request.getColor());
        car.setCapacity(request.getCapacity());
        car.setPlateNumber(request.getPlateNumber());
        car.setRate(request.getRate());
        car.setCarStatus(request.getCarStatus());
        car.setLastModified(LocalDateTime.now());
        car.setLastModifiedBy(userId);
        car.setImagePath(CarUtils.downloadAndStoreImage(request.getImageUrl()));
        return carRepository.save(car);
    }


    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
