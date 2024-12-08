package br.com.engjaconi.curso_springboot_3.unittest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import br.com.engjaconi.curso_springboot_3.mapper.Mapper;
import br.com.engjaconi.curso_springboot_3.unittest.mock.PersonMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.engjaconi.curso_springboot_3.data.dto.v1.PersonDTO;
import br.com.engjaconi.curso_springboot_3.model.Person;

class MapperTests {

    PersonMock inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new PersonMock();
    }

    @Test
    void parseEntityToVOTest() {
        PersonDTO output = Mapper.parseObject(inputObject.mockEntity(), PersonDTO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Addres Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    void parseEntityListToVOListTest() {
        List<PersonDTO> outputList = Mapper.parseListObjects(inputObject.mockEntityList(), PersonDTO.class);
        PersonDTO outputZero = outputList.getFirst();

        assertEquals(Long.valueOf(1L), outputZero.getKey());
        assertEquals("First Name Test1", outputZero.getFirstName());
        assertEquals("Last Name Test1", outputZero.getLastName());
        assertEquals("Addres Test1", outputZero.getAddress());
        assertEquals("Female", outputZero.getGender());

        PersonDTO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(8L), outputSeven.getKey());
        assertEquals("First Name Test8", outputSeven.getFirstName());
        assertEquals("Last Name Test8", outputSeven.getLastName());
        assertEquals("Addres Test8", outputSeven.getAddress());
        assertEquals("Male", outputSeven.getGender());

        PersonDTO outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(13L), outputTwelve.getKey());
        assertEquals("First Name Test13", outputTwelve.getFirstName());
        assertEquals("Last Name Test13", outputTwelve.getLastName());
        assertEquals("Addres Test13", outputTwelve.getAddress());
        assertEquals("Female", outputTwelve.getGender());
    }

    @Test
    void parseVOToEntityTest() {
        Person output = Mapper.parseObject(inputObject.mockDTO(), Person.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Addres Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    void parserVOListToEntityListTest() {
        List<Person> outputList = Mapper.parseListObjects(inputObject.mockDTOList(), Person.class);
        Person outputZero = outputList.getFirst();

        assertEquals(Long.valueOf(1L), outputZero.getId());
        assertEquals("First Name Test1", outputZero.getFirstName());
        assertEquals("Last Name Test1", outputZero.getLastName());
        assertEquals("Addres Test1", outputZero.getAddress());
        assertEquals("Female", outputZero.getGender());

        Person outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(8L), outputSeven.getId());
        assertEquals("First Name Test8", outputSeven.getFirstName());
        assertEquals("Last Name Test8", outputSeven.getLastName());
        assertEquals("Addres Test8", outputSeven.getAddress());
        assertEquals("Male", outputSeven.getGender());

        Person outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(13L), outputTwelve.getId());
        assertEquals("First Name Test13", outputTwelve.getFirstName());
        assertEquals("Last Name Test13", outputTwelve.getLastName());
        assertEquals("Addres Test13", outputTwelve.getAddress());
        assertEquals("Female", outputTwelve.getGender());
    }
}
