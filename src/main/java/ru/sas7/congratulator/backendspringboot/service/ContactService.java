package ru.sas7.congratulator.backendspringboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.sas7.congratulator.backendspringboot.entity.Contact;
import ru.sas7.congratulator.backendspringboot.repo.ContactRepository;
import ru.sas7.congratulator.backendspringboot.search.ContactSearchParams;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAllByOrderByLastNameAsc() {
        return contactRepository.findAllByOrderByLastNameAsc();
    }

    public Contact add(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact findById(Integer id) {
        return contactRepository.findById(id).get();
    }

    public void update(Contact contact) {
        contactRepository.save(contact);
    }

    public void deleteById(Integer id) {
        contactRepository.deleteById(id);
    }

    public List<Contact> findByParams(ContactSearchParams contactSearchParams) {
        return contactRepository.findByParams(contactSearchParams.getFirstName(),
                contactSearchParams.getMiddleName(),
                contactSearchParams.getLastName(),
                contactSearchParams.getCategoryId()
        );
    }

    public Page<Contact> findUpcomingBirthdays(LocalDate nowDate, PageRequest pageRequest) {
     return contactRepository.findUpcomingBirthdays(nowDate, pageRequest);
    }
}
