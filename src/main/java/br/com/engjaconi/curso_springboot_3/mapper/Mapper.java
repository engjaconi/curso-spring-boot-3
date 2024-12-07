package br.com.engjaconi.curso_springboot_3.mapper;

import br.com.engjaconi.curso_springboot_3.data.dto.v1.BookDTO;
import br.com.engjaconi.curso_springboot_3.data.dto.v1.PersonDTO;
import br.com.engjaconi.curso_springboot_3.model.Book;
import br.com.engjaconi.curso_springboot_3.model.Person;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    private Mapper() { }

    static {
        // necessário para mapear pois atributos tem nomes diferentes, devido o uso do hateoas.
        modelMapper.createTypeMap(
                Person.class,
                PersonDTO.class
        ).addMapping(Person::getId, PersonDTO::setKey);

        modelMapper.createTypeMap(
                PersonDTO.class,
                Person.class
        ).addMapping(PersonDTO::getKey, Person::setId);

        modelMapper.createTypeMap(
                Book.class,
                BookDTO.class
        ).addMapping(Book::getId, BookDTO::setKey);

        modelMapper.createTypeMap(
                BookDTO.class,
                Book.class
        ).addMapping(BookDTO::getKey, Book::setId);
    }

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return modelMapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<>();

        for(O object : origin) {
            destinationObjects.add(parseObject(object, destination));
        }

        return destinationObjects;
    }
}
