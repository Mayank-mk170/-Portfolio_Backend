package com.portfolio.controller;

import com.portfolio.dto.ContactRequest;
import com.portfolio.entity.Contact;
import com.portfolio.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<?> submitContact(
            @Valid @RequestBody ContactRequest request
    ) {

        try {

            Contact contact =
                    contactService.saveContact(request);

            return ResponseEntity.ok(
                    Map.of(
                            "message",
                            "Message sent successfully",
                            "id",
                            contact.getId()
                    )
            );

        } catch (Exception e) {

            return new ResponseEntity<>("Unable to send message", HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {

        return ResponseEntity.ok(
                contactService.getAllContacts()
        );
    }


    // ========================================
    // ADMIN - GET ONE MESSAGE
    // ========================================

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                contactService.getContactById(id)
        );
    }


    // ========================================
    // ADMIN - DELETE MESSAGE
    // ========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(
            @PathVariable Long id
    ) {

        try {

            contactService.deleteContact(id);

            return ResponseEntity.ok(
                    Map.of(
                            "message",
                            "Message deleted successfully"
                    )
            );

        } catch (Exception e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}