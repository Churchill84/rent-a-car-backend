package com.rentacar.model;

import com.rentacar.enumeration.CarStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * @created type Car
 * /p
 * @created by dstankovski
 * /p
 * @since 25/10/2023
 */
@Entity(name = "car")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @Column(name = "car_name", nullable = false, length = 30)
    private String carName;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "car_model_year", nullable = false)
    private int carModelYear;

    @Column(name = "car_brand", nullable = false, length = 25)
    private String carBrand;

    @Column(name = "color", nullable = false, length = 15)
    private String color;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "plate_number", nullable = false, length = 11, unique = true)
    private String plateNumber;

    @Column(name = "rate", nullable = false)
    private float rate;

    @Column(name = "car_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModified;


    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private Long createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Long lastModifiedBy;

    @Column(name = "image_path", length = 255)
    private String imagePath;
}
