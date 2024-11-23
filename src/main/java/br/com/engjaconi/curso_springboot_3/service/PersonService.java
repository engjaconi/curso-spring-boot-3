package br.com.engjaconi.curso_springboot_3.service;

import br.com.engjaconi.curso_springboot_3.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());
    private final AtomicLong counter = new AtomicLong();

    public Person findById(String id){
        logger.log(Level.INFO, "Buscando uma pessoa {0}", id);

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Tiago");
        person.setLastName("Jaconi");
        person.setAddress("Brasil");
        person.setGender("M");

        return person;
    }

    public List<Person> findAll() {
        logger.info("Buscando todas as pessoas");

        return mockPerson();
    }

    public Person create(Person person){
        logger.info("Adicionando uma pessoa");
        person.setId(counter.incrementAndGet());
        return person;
    }

    public Person update(Person person){
        logger.info("Atualizando a pessoa");
        return person;
    }

    public void delete(String id){
        logger.log(Level.INFO, "Deletando a pessoa id {0}", id);
    }

    private List<Person> mockPerson() {
        List<Person> people = new ArrayList<>();

        for(int i = 1; i <= 5; i++) {
            Person person = new Person();
            person.setId(counter.incrementAndGet());
            person.setFirstName("firstName " + i);
            person.setLastName("lastName " + i);
            person.setAddress("address " + i);
            person.setGender("M");

            people.add(person);
        }

        return people;
    }
}
