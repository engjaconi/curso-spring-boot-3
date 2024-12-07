package br.com.engjaconi.curso_springboot_3.mock;

import br.com.engjaconi.curso_springboot_3.data.dto.v1.BookDTO;
import br.com.engjaconi.curso_springboot_3.model.Book;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookMock {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookDTO> mockDTOList() {
        List<BookDTO> books = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            books.add(mockDTO(i));
        }
        return books;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setAuthor("Test" + number);
        book.setLaunchDate(new Date());
        book.setPrice(new BigDecimal(number));
        book.setTitle("Test" + number);
        return book;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO book = new BookDTO();
        book.setKey(number.longValue());
        book.setAuthor("Test" + number);
        book.setLaunchDate(new Date());
        book.setPrice(new BigDecimal(number));
        book.setTitle("Test" + number);
        return book;
    }

}

