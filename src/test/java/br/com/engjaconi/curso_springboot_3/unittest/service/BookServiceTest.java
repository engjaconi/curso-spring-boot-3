package br.com.engjaconi.curso_springboot_3.unittest.service;

import br.com.engjaconi.curso_springboot_3.data.dto.v1.BookDTO;
import br.com.engjaconi.curso_springboot_3.exception.RequiredObjectIsNullException;
import br.com.engjaconi.curso_springboot_3.service.BookService;
import br.com.engjaconi.curso_springboot_3.unittest.mock.BookMock;
import br.com.engjaconi.curso_springboot_3.model.Book;
import br.com.engjaconi.curso_springboot_3.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    BookMock bookMock;

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @BeforeEach
    void setUpMocks() {
        bookMock = new BookMock();
    }

    @Test
    void testFindById() {
        Book entity = bookMock.mockEntity(1);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertEquals(1L, result.getKey());
        assertEquals("</api/v1/book/1>;rel=\"self\"", result.getLinks().toString());
        assertEquals("Test1", result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(new BigDecimal(1), result.getPrice());
        assertEquals("Test1", result.getTitle());
    }

    @Test
    void testCreate() {
        Book entity = bookMock.mockEntity(1);
        Book entityPersisted = bookMock.mockEntity(1);

        when(repository.save(entity)).thenReturn(entityPersisted);

        BookDTO bookDTO = bookMock.mockDTO(1);
        var result = service.create(bookDTO);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertEquals(1L, result.getKey());
        assertEquals("</api/v1/book/1>;rel=\"self\"", result.getLinks().toString());
        assertEquals("Test1", result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(new BigDecimal(1), result.getPrice());
        assertEquals("Test1", result.getTitle());
    }

    @Test
    void testCreateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.create(null));
        String mensagemEsperada = "Não é permitido persistir um objeto nulo!";
        String mensagemRecebida = exception.getMessage();

        assertEquals(mensagemEsperada, mensagemRecebida);
    }

    @Test
    void testEdit() {
        Book entity = bookMock.mockEntity(1);
        Book entityPersisted = bookMock.mockEntity(1);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entityPersisted);

        BookDTO bookDTO = bookMock.mockDTO(1);
        var result = service.update(bookDTO);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertEquals(1L, result.getKey());
        assertEquals("</api/v1/book/1>;rel=\"self\"", result.getLinks().toString());
        assertEquals("Test1", result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(new BigDecimal(1), result.getPrice());
        assertEquals("Test1", result.getTitle());
    }

    @Test
    void testUpdateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.update(null));
        String mensagemEsperada = "Não é permitido persistir um objeto nulo!";
        String mensagemRecebida = exception.getMessage();

        assertEquals(mensagemEsperada, mensagemRecebida);
    }

    @Test
    void testDelete() {
        Book entity = bookMock.mockEntity(1);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        service.delete(result.getKey());
    }

    @Test
    void testFindAll() {
        List<Book> list = bookMock.mockEntityList();
        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var bookOne = people.getFirst();

        assertNotNull(bookOne);
        assertNotNull(bookOne.getLinks());
        assertEquals(1L, bookOne.getKey());
        assertEquals("</api/v1/book/1>;rel=\"self\"", bookOne.getLinks().toString());
        assertEquals("Test1", bookOne.getAuthor());
        assertNotNull(bookOne.getLaunchDate());
        assertEquals(new BigDecimal(1), bookOne.getPrice());
        assertEquals("Test1", bookOne.getTitle());
    }
}