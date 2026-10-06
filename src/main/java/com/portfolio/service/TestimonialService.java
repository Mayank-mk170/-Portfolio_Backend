package com.portfolio.service;

import com.portfolio.dto.TestimonialRequest;
import com.portfolio.entity.Testimonial;
import com.portfolio.repository.TestimonialRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestimonialService {

    private final TestimonialRepository testimonialRepository;

    // CREATE
    public Testimonial createTestimonial(
            TestimonialRequest request
    ) {

        Testimonial testimonial = new Testimonial();

        testimonial.setName(request.getName());
        testimonial.setPosition(request.getPosition());
        testimonial.setCompany(request.getCompany());
        testimonial.setContent(request.getContent());
        testimonial.setImage(request.getImage());
        testimonial.setRating(request.getRating());

        testimonial.setFeatured(
                request.getFeatured() != null
                        ? request.getFeatured()
                        : false
        );

        testimonial.setOrder(request.getOrder());

        return testimonialRepository.save(testimonial);
    }

    // READ ALL
    public List<Testimonial> getAllTestimonials() {

        return testimonialRepository.findAll();
    }

    // READ ONE
    public Testimonial getTestimonialById(Long id) {

        return testimonialRepository
                .findById(id)
                .orElse(null);
    }

    // UPDATE
    public Testimonial updateTestimonial(
            Long id,
            TestimonialRequest request
    ) {

        Testimonial testimonial =
                testimonialRepository
                        .findById(id)
                        .orElse(null);

        if (testimonial == null) {
            return null;
        }

        testimonial.setName(request.getName());
        testimonial.setPosition(request.getPosition());
        testimonial.setCompany(request.getCompany());
        testimonial.setContent(request.getContent());
        testimonial.setImage(request.getImage());
        testimonial.setRating(request.getRating());

        testimonial.setFeatured(
                request.getFeatured() != null
                        ? request.getFeatured()
                        : false
        );

        testimonial.setOrder(request.getOrder());

        return testimonialRepository.save(testimonial);
    }

    // DELETE
    public boolean deleteTestimonial(Long id) {

        Testimonial testimonial =
                testimonialRepository
                        .findById(id)
                        .orElse(null);

        if (testimonial == null) {
            return false;
        }

        testimonialRepository.delete(testimonial);

        return true;
    }
}