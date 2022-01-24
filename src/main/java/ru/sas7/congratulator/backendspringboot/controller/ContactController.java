package ru.sas7.congratulator.backendspringboot.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sas7.congratulator.backendspringboot.entity.Contact;
import ru.sas7.congratulator.backendspringboot.request.UpcomingBirthdaysParams;
import ru.sas7.congratulator.backendspringboot.search.ContactSearchParams;
import ru.sas7.congratulator.backendspringboot.service.ContactService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    private List<String> checkRequiredProperties(Contact contact) {
        List<String> messages = new ArrayList<String>();

        // Проверяем наличие имени
        if (contact.getFirstName() == null || contact.getFirstName().trim().length() == 0) {
            messages.add("Неверный параметр: имя - обязательный параметр");
        }

        // Проверяем наличие отчества
        if (contact.getMiddleName() == null || contact.getMiddleName().trim().length() == 0) {
            messages.add("Неверный параметр: отчество - обязательный параметр");
        }

        // Проверяем наличие фамилии
        if (contact.getLastName() == null || contact.getLastName().trim().length() == 0) {
            messages.add("Неверный параметр: фамилия - обязательный параметр");
        }

        // Проверяем наличие дня рождения
        System.out.println(contact.getBirthday());
        if (contact.getBirthday() == null) {
            messages.add("Неверный параметр: день рождения - обязательный параметр");
        }

        return messages;
    }

    @GetMapping("")
    public List<Contact> getContact() {
        List<Contact> list = contactService.findAllByOrderByLastNameAsc();
        return list;
    }

    // Добавление контакта
    @PostMapping("/add")
    public ResponseEntity<Contact> add(@RequestBody Contact contact) {

        // Проверяем чтобы не было id
        if (contact.getId() != null && contact.getId() != 0) {
            return new ResponseEntity("Неверный параметр: в объекте не должно быть параметра id",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        List<String> emptyPropertyMessages = checkRequiredProperties(contact);
        if (!emptyPropertyMessages.isEmpty()) {
            return new ResponseEntity(String.join("\n", emptyPropertyMessages), HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(contactService.add(contact));
    }

    // Получение контакта
    @GetMapping("/{id}")
    public ResponseEntity<Contact> findById(@PathVariable int id) {

        Contact contact = null;

        try {
            contact = contactService.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("Контакт с id: " + id + " не найден", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(contact, HttpStatus.OK);
    }

    // Изменение контакта
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Contact contact) {

        // Проверяем чтобы было id
        if (contact.getId() == null || contact.getId() == 0) {
            return new ResponseEntity("Неверный параметр: id обязательный параметр", HttpStatus.NOT_ACCEPTABLE);
        }

        List<String> emptyPropertyMessages = checkRequiredProperties(contact);
        if (!emptyPropertyMessages.isEmpty()) {
            return new ResponseEntity(String.join("\n", emptyPropertyMessages), HttpStatus.NOT_ACCEPTABLE);
        }

        contactService.update(contact);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable int id) {

        try {
            contactService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("Категория с id: " + id + " не найдена.", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    // Поиск по параметрам
    @GetMapping("/search")
    public ResponseEntity<List<Contact>> findByParams(@RequestBody ContactSearchParams contactSearchParams) {
        return ResponseEntity.ok(contactService.findByParams(contactSearchParams));
    }

    // Получить ближайшие дни рождения

    /**
     * Получить ближайшие дни рождения постранично
     *
     * @param upcomingBirthdaysParams
     * @return страница контактов
     */
    @GetMapping("/upcoming-birthdays")
    public ResponseEntity<Page<Contact>>
    findUpcomingBirthdays(@RequestBody UpcomingBirthdaysParams upcomingBirthdaysParams) {
        LocalDate nowDate = LocalDate.parse(upcomingBirthdaysParams.getFromDate());

        PageRequest pageRequest = PageRequest.of(upcomingBirthdaysParams.getPageIndex(),
                upcomingBirthdaysParams.getPageSize()
        );

        Page<Contact> pageResponse = contactService.findUpcomingBirthdays(nowDate, pageRequest);
        return ResponseEntity.ok(pageResponse);
    }

}
