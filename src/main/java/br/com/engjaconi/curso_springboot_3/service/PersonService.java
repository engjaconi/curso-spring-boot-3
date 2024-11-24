package br.com.engjaconi.curso_springboot_3.service;

import br.com.engjaconi.curso_springboot_3.exception.ResourceNotFoundException;
import br.com.engjaconi.curso_springboot_3.model.Person;
import br.com.engjaconi.curso_springboot_3.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(Long id){
        logger.log(Level.INFO, "Buscando a pessoa {0}", id);
        return findPersonById(id);
    }

    public List<Person> findAll() {
        logger.info("Buscando todas as pessoas");
        return personRepository.findAll();
    }

    public Person create(Person person){
        logger.info("Adicionando uma pessoa");
        return personRepository.save(person);
    }

    public Person update(Person person){
        logger.info("Atualizando a pessoa");
        Person entity = findPersonById(person.getId());

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }

    public void delete(Long id){
        Person entity = findPersonById(id);
        logger.log(Level.INFO, "Deletando a pessoa id {0}", entity.getId());
        personRepository.delete(entity);
    }

    private Person findPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Não foi localizado uma pessoa com esse ID")
        );
    }

}
