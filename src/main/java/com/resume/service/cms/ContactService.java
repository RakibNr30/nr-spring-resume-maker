package com.resume.service.cms;

import com.resume.entity.cms.Contact;
import com.resume.repository.cms.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAll() {
        return this.contactRepository.findAll();
    }

    public Contact save(Contact contact) {
        return this.contactRepository.save(contact);
    }

    public Contact findById(Long id) {
        return this.contactRepository.findById(id).orElse(null);
    }

    public Contact saveOrUpdate(Contact contact) {
        return this.contactRepository.save(contact);
    }

    public Contact update(Contact contact) {
        return this.contactRepository.save(contact);
    }

    public void delete(Contact contact) {
        this.contactRepository.delete(contact);
    }

    public Contact findOrSave(Long id) {
        Contact contact = this.contactRepository.findById(id).orElse(null);

        System.out.println();
        System.out.println();
        System.out.println(contact);
        System.out.println();
        System.out.println();

        if (contact == null) {
            contact = new Contact();
            contact.setId(id);
            this.contactRepository.save(contact);
        }

        return contact;
    }
}
