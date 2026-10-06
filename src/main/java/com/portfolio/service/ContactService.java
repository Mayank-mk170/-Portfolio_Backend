package com.portfolio.service;

import com.portfolio.dto.ContactRequest;
import com.portfolio.entity.Contact;
import com.portfolio.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public Contact saveContact(ContactRequest request) {

        Contact contact = Contact.builder()
                .name(request.getName())
                .email(request.getEmail())
                .subject(request.getSubject())
                .message(request.getMessage())
                .build();

        return contactRepository.save(contact);
    }

   // GET ALL CONTACT MESSAGES
    // ========================================

    public List<Contact> getAllContacts() {

        return contactRepository
                .findAllByOrderByCreatedAtDesc();
    }


    // ========================================
    // GET ONE CONTACT MESSAGE
    // ========================================

    public Contact getContactById(Long id) {

        return contactRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Contact message not found"
                        )
                );
    }


    // ========================================
    // DELETE CONTACT MESSAGE
    // ========================================

    public void deleteContact(Long id) {

        if (!contactRepository.existsById(id)) {
            throw new RuntimeException(
                    "Contact message not found"
            );
        }

        contactRepository.deleteById(id);
    }
}