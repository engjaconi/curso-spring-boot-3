package br.com.engjaconi.curso_springboot_3.controller;

import br.com.engjaconi.curso_springboot_3.data.dto.v1.PersonDTO;
import br.com.engjaconi.curso_springboot_3.data.dto.v2.PersonV2DTO;
import br.com.engjaconi.curso_springboot_3.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(personService.findById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok().body(personService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }

    @PostMapping(value = "/v2",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonV2DTO> create(@RequestBody PersonV2DTO person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
        return ResponseEntity.ok().body(personService.update(person));
    }

    @DeleteMapping(value = "/{id}")
    public <T> ResponseEntity<T> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
