package br.com.engjaconi.curso_springboot_3.unittest.service;

import br.com.engjaconi.curso_springboot_3.data.dto.v1.PersonDTO;
import br.com.engjaconi.curso_springboot_3.exception.RequiredObjectIsNullException;
import br.com.engjaconi.curso_springboot_3.service.PersonService;
import br.com.engjaconi.curso_springboot_3.unittest.mock.PersonMock;
import br.com.engjaconi.curso_springboot_3.model.Person;
import br.com.engjaconi.curso_springboot_3.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    PersonMock personMock;

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    @BeforeEach
    void setUpMocks() {
        personMock = new PersonMock();
    }

    @Test
    void testFindById() {
        Person entity = personMock.mockEntity(1);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertEquals(1L, result.getKey());
        assertEquals("</api/v1/person/1>;rel=\"self\"", result.getLinks().toString());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Female", result.getGender());
        assertEquals("Last Name Test1", result.getLastName());
    }

    @Test
    void testCreate() {
        Person entity = personMock.mockEntity(1);
        Person entityPersisted = personMock.mockEntity(1);

        when(repository.save(entity)).thenReturn(entityPersisted);

        PersonDTO personDTO = personMock.mockDTO(1);
        var result = service.create(personDTO);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertEquals(1L, result.getKey());
        assertEquals("</api/v1/person/1>;rel=\"self\"", result.getLinks().toString());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Female", result.getGender());
        assertEquals("Last Name Test1", result.getLastName());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.create(null));
        String mensagemEsperada = "Não é permitido persistir um objeto nulo!";
        String mensagemRecebida = exception.getMessage();

        assertEquals(mensagemEsperada, mensagemRecebida);
    }

    @Test
    void testEdit() {
        Person entity = personMock.mockEntity(1);
        Person entityPersisted = personMock.mockEntity(1);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entityPersisted);

        PersonDTO personDTO = personMock.mockDTO(1);
        var result = service.update(personDTO);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertEquals(1L, result.getKey());
        assertEquals("</api/v1/person/1>;rel=\"self\"", result.getLinks().toString());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Female", result.getGender());
        assertEquals("Last Name Test1", result.getLastName());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.update(null));
        String mensagemEsperada = "Não é permitido persistir um objeto nulo!";
        String mensagemRecebida = exception.getMessage();

        assertEquals(mensagemEsperada, mensagemRecebida);
    }

    @Test
    void testDelete() {
        Person entity = personMock.mockEntity(1);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        service.delete(result.getKey());
    }

    @Test
    void testFindAll() {
        List<Person> list = personMock.mockEntityList();
        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.getFirst();

        assertNotNull(personOne);
        assertNotNull(personOne.getLinks());
        assertEquals(1L, personOne.getKey());
        assertEquals("</api/v1/person/1>;rel=\"self\"", personOne.getLinks().toString());
        assertEquals("Addres Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Female", personOne.getGender());
        assertEquals("Last Name Test1", personOne.getLastName());
    }
}