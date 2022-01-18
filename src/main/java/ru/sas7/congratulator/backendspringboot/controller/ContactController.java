package ru.sas7.congratulator.backendspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<Contact> test() {
        List<Contact> list = contactRepository.findAll();
        System.out.println("List = " + list);
        return list;
    }
}
