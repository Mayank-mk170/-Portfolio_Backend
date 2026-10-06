package com.portfolio.controller;

import com.portfolio.dto.ServiceRequest;
import com.portfolio.entity.PortfolioService;
import com.portfolio.service.ServiceService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<?> createService(
            @Valid @RequestBody ServiceRequest request
    ) {

        PortfolioService service =
                serviceService.createService(request);

        return new ResponseEntity<>(
                service,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<PortfolioService>> getAllServices() {

        return new ResponseEntity<>(
                serviceService.getAllServices(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getService(
            @PathVariable Long id
    ) {

        PortfolioService service =
                serviceService.getServiceById(id);

        if (service == null) {

            return new ResponseEntity<>(
                    "Service not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                service,
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(
            @PathVariable Long id,
            @Valid @RequestBody ServiceRequest request
    ) {

        PortfolioService service =
                serviceService.updateService(
                        id,
                        request
                );

        if (service == null) {

            return new ResponseEntity<>(
                    "Service not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                service,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(
            @PathVariable Long id
    ) {

        boolean deleted =
                serviceService.deleteService(id);

        if (!deleted) {

            return new ResponseEntity<>(
                    "Service not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                "Service deleted successfully",
                HttpStatus.OK
        );
    }
}