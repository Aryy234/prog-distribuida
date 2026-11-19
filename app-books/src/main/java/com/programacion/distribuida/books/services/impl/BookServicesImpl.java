package com.programacion.distribuida.books.services.impl;

import com.programacion.distribuida.books.clients.AuthorRestClient;
import com.programacion.distribuida.books.db.Book;
import com.programacion.distribuida.books.dtos.AuthorDto;
import com.programacion.distribuida.books.dtos.BookDto;
import com.programacion.distribuida.books.repo.BookRepository;
import com.programacion.distribuida.books.services.interfaces.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class BookServicesImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRestClient authorRestClient;

    @Inject
    public BookServicesImpl(BookRepository bookRepository, @RestClient AuthorRestClient authorRestClient) {
        this.bookRepository = bookRepository;
        this.authorRestClient = authorRestClient;
    }

    private BookDto toDto(Book book) {
        List<AuthorDto> authors = authorRestClient.findBookByIsbn(book.getIsbn());

        return BookDto.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .price(book.getPrice())
                .inventorySold(book.getInventory() != null ? book.getInventory().getSold() : null)
                .inventorySupplied(book.getInventory() != null ? book.getInventory().getSupplied() : null)
                .inventoryVersion(book.getInventory() != null ? book.getInventory().getVersion() : null)
                .authors(authors)
                .build();
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.streamAll()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDto> findByIdOptional(String id) {
        return bookRepository.findByIdOptional(id)
                .map(this::toDto);
    }

}
