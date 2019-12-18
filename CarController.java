package com.okta.Car.Carname.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.okta.Car.FileSharing.entity.Employee;
import com.okta.Car.Carname.entity.Province;
import com.okta.Car.Carname.entity.Brand;
import com.okta.Car.Carname.entity.Cartype;
import com.okta.Car.Carname.entity.Car;

import com.okta.Car.FileSharing.repository.EmployeeRepository;
import com.okta.Car.Carname.repository.BrandRepository;
import com.okta.Car.Carname.repository.CarRepository;
import com.okta.Car.Carname.repository.CartypeRepository;
import com.okta.Car.Carname.repository.ProvinceRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CarController {
    @Autowired
    private final CarRepository carRepository;
    @Autowired
    private CartypeRepository cartypeRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/car")
    public Collection<Car> Cars() {
        return carRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/car/{vins}/{plates}/{type_id}/{brand_id}/{province_id}/{employee_id}")
    public Car newCar(Car newCar,
    @PathVariable String plates,
    @PathVariable String vins,
    @PathVariable long type_id,
    @PathVariable long brand_id,
    @PathVariable long province_id,
    @PathVariable long employee_id) 
    {


    Cartype cartype = cartypeRepository.findById(type_id);
    Province province = provinceRepository.findById(province_id);
    Brand brandd = brandRepository.findById(brand_id);
    Employee employee = employeeRepository.findById(employee_id);

    
    newCar.setCartype(cartype);
    newCar.setPlateprovince(province);
    newCar.setCarbrand(brandd);
    newCar.setCreatedby(employee);
    newCar.setPlate(plates);
    newCar.setVin(vins);

    return carRepository.save(newCar); 
    
    }
}