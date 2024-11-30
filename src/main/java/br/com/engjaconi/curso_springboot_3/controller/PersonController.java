package br.com.engjaconi.curso_springboot_3.controller;

import br.com.engjaconi.curso_springboot_3.data.dto.v1.PersonDTO;
import br.com.engjaconi.curso_springboot_3.service.PersonService;
import br.com.engjaconi.curso_springboot_3.util.converter.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity<PersonDTO> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(personService.findById(id));
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok().body(personService.findAll());
    }

    @PostMapping(
            consumes =  { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }

    @PutMapping(
            consumes =  { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
        return ResponseEntity.ok().body(personService.update(person));
    }

    @DeleteMapping(value = "/{id}")
    public <T> ResponseEntity<T> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
