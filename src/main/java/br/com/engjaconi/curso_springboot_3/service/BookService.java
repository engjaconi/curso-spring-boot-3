package br.com.engjaconi.curso_springboot_3.service;

import br.com.engjaconi.curso_springboot_3.controller.BookController;
import br.com.engjaconi.curso_springboot_3.data.dto.v1.BookDTO;
import br.com.engjaconi.curso_springboot_3.exception.RequiredObjectIsNullException;
import br.com.engjaconi.curso_springboot_3.exception.ResourceNotFoundException;
import br.com.engjaconi.curso_springboot_3.mapper.Mapper;
import br.com.engjaconi.curso_springboot_3.model.Book;
import br.com.engjaconi.curso_springboot_3.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private Logger logger = Logger.getLogger(BookService.class.getName());

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO findById(Long id){
        logger.log(Level.INFO, "Buscando o livro {0}", id);
        BookDTO bookDTO = Mapper.parseObject(findBookById(id), BookDTO.class);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());

        return  bookDTO;
    }

    public List<BookDTO> findAll() {
        logger.info("Buscando todas os livros");
        var books = Mapper.parseListObjects(bookRepository.findAll(), BookDTO.class);
        books.forEach(
                        p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel())
                );

        return books;
    }

    public BookDTO create(BookDTO book){
        if(book == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Adicionando um livro");
        BookDTO bookDTO =  Mapper.parseObject(bookRepository.save(Mapper.parseObject(book, Book.class)), BookDTO.class);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());

        return  bookDTO;
    }

    public BookDTO update(BookDTO book){
        if(book == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Atualizando o livro");
        Book entity = findBookById(book.getKey());

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        BookDTO bookDTO = Mapper.parseObject(bookRepository.save(entity), BookDTO.class);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());

        return  bookDTO;
    }

    public void delete(Long id){
        Book entity = findBookById(id);
        logger.log(Level.INFO, "Deletando o livro id {0}", entity.getId());
        bookRepository.delete(entity);
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Não foi localizado um livro com esse ID")
        );
    }

}
