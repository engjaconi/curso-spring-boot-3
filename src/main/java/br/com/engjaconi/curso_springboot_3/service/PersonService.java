package br.com.engjaconi.curso_springboot_3.service;

import br.com.engjaconi.curso_springboot_3.data.dto.v1.PersonDTO;
import br.com.engjaconi.curso_springboot_3.data.dto.v2.PersonV2DTO;
import br.com.engjaconi.curso_springboot_3.exception.ResourceNotFoundException;
import br.com.engjaconi.curso_springboot_3.mapper.Mapper;
import br.com.engjaconi.curso_springboot_3.mapper.custom.PersonMapper;
import br.com.engjaconi.curso_springboot_3.model.Person;
import br.com.engjaconi.curso_springboot_3.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public PersonDTO findById(Long id){
        logger.log(Level.INFO, "Buscando a pessoa {0}", id);
        return Mapper.parseObject(findPersonById(id), PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        logger.info("Buscando todas as pessoas");
        return Mapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Adicionando uma pessoa");
        return Mapper.parseObject(personRepository.save(Mapper.parseObject(person, Person.class)), PersonDTO.class);
    }

    public PersonV2DTO create(PersonV2DTO person){
        logger.info("Adicionando uma pessoa v2");
        return personMapper.convertEntityToDTO(personRepository.save(personMapper.convertDTOToEntity(person)));
    }

    public PersonDTO update(PersonDTO person){
        logger.info("Atualizando a pessoa");
        Person entity = findPersonById(person.getId());

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return Mapper.parseObject(personRepository.save(entity), PersonDTO.class);
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
