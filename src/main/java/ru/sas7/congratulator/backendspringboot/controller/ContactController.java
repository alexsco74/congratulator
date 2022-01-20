package ru.sas7.congratulator.backendspringboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sas7.congratulator.backendspringboot.entity.Contact;
import ru.sas7.congratulator.backendspringboot.repo.ContactRepository;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("")
    public List<Contact> getContact() {
        List<Contact> list = contactRepository.findAll();
        System.out.println("List = " + list);
        return list;
    }

    @PostMapping("/add")
    public ResponseEntity<Contact> add(@RequestBody Contact contact) {

        // Проверяем чтобы не было id
        if (contact.getId() != null && contact.getId() != 0) {
            return new ResponseEntity("Неверный параметр: в объекте не должно быть параметра id", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(contactRepository.save(contact));
    }
}
