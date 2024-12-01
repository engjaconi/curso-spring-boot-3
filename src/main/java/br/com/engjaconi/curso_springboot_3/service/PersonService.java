package br.com.engjaconi.curso_springboot_3.service;

import br.com.engjaconi.curso_springboot_3.controller.PersonController;
import br.com.engjaconi.curso_springboot_3.data.dto.v1.PersonDTO;
import br.com.engjaconi.curso_springboot_3.exception.ResourceNotFoundException;
import br.com.engjaconi.curso_springboot_3.mapper.Mapper;
import br.com.engjaconi.curso_springboot_3.model.Person;
import br.com.engjaconi.curso_springboot_3.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDTO findById(Long id){
        logger.log(Level.INFO, "Buscando a pessoa {0}", id);
        PersonDTO personDTO = Mapper.parseObject(findPersonById(id), PersonDTO.class);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return  personDTO;
    }

    public List<PersonDTO> findAll() {
        logger.info("Buscando todas as pessoas");
        var persons = Mapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
        persons.forEach(
                        p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
                );

        return persons;
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Adicionando uma pessoa");
        PersonDTO personDTO =  Mapper.parseObject(personRepository.save(Mapper.parseObject(person, Person.class)), PersonDTO.class);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());

        return  personDTO;
    }

    public PersonDTO update(PersonDTO person){
        logger.info("Atualizando a pessoa");
        Person entity = findPersonById(person.getKey());

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonDTO personDTO = Mapper.parseObject(personRepository.save(entity), PersonDTO.class);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());

        return  personDTO;
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
