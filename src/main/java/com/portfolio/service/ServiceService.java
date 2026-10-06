package com.portfolio.service;

import com.portfolio.dto.ServiceRequest;
import com.portfolio.entity.PortfolioService;
import com.portfolio.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public PortfolioService createService(
            ServiceRequest request
    ) {

        PortfolioService service = new PortfolioService();

        service.setTitle(request.getTitle());
        service.setDescription(request.getDescription());
        service.setIcon(request.getIcon());
        service.setImage(request.getImage());
        service.setOrder(request.getOrder());

        return serviceRepository.save(service);
    }

    public List<PortfolioService> getAllServices() {

        return serviceRepository.findAll();
    }

    public PortfolioService getServiceById(Long id) {

        return serviceRepository
                .findById(id)
                .orElse(null);
    }

    public PortfolioService updateService(
            Long id,
            ServiceRequest request
    ) {

        PortfolioService service =
                serviceRepository
                        .findById(id)
                        .orElse(null);

        if (service == null) {
            return null;
        }

        service.setTitle(request.getTitle());
        service.setDescription(request.getDescription());
        service.setIcon(request.getIcon());
        service.setImage(request.getImage());
        service.setOrder(request.getOrder());

        return serviceRepository.save(service);
    }

    public boolean deleteService(Long id) {

        PortfolioService service =
                serviceRepository
                        .findById(id)
                        .orElse(null);

        if (service == null) {
            return false;
        }

        serviceRepository.delete(service);

        return true;
    }
}