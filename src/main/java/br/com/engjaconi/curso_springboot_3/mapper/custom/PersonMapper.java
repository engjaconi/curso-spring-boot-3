package br.com.engjaconi.curso_springboot_3.mapper.custom;

import br.com.engjaconi.curso_springboot_3.data.dto.v2.PersonV2DTO;
import br.com.engjaconi.curso_springboot_3.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonV2DTO convertEntityToDTO(Person person) {
        PersonV2DTO dto = new PersonV2DTO();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
        dto.setBirthDay(new Date());

        return dto;
    }

    public Person convertDTOToEntity(PersonV2DTO person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return entity;
    }
}
