package com.rentacar.request;

import com.rentacar.enumeration.CarStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    private String carName;
    private String description;
    private int carModelYear;
    private String carBrand;
    private String color;
    private int capacity;
    private String plateNumber;
    private float rate;
    private CarStatus carStatus;
    private LocalDateTime lastModified;
    private Long createdBy;
    private Long lastModifiedBy;
    private MultipartFile imageUrl;
}
