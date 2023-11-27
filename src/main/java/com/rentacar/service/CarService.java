package com.rentacar.service;

import com.rentacar.model.Car;
import com.rentacar.request.CarRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public interface CarService {
    List<Car> getAllCars();
    Car getCarById(Long id);
    Car createCar(CarRequest request, long userId) throws IOException;
    Car updateCar(Long id, CarRequest request, long userId) throws IOException;
    void deleteCar(Long id);
}
