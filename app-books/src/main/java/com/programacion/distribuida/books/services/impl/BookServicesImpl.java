package com.programacion.distribuida.books.services.impl;

import com.programacion.distribuida.books.db.Book;
import com.programacion.distribuida.books.dtos.BookDto;
import com.programacion.distribuida.books.repo.BookRepository;
import com.programacion.distribuida.books.services.interfaces.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class BookServicesImpl implements BookService {
    private final BookRepository bookRepository;

    @Inject
    public BookServicesImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private BookDto toDto(Book book) {
        return BookDto.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .price(book.getPrice())
                .inventorySold(book.getInventory() != null ? book.getInventory().getSold() : null)
                .inventorySupplied(book.getInventory() != null ? book.getInventory().getSupplied() : null)
                .inventoryVersion(book.getInventory() != null ? book.getInventory().getVersion() : null)
                .build();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.listAll();
    }

    @Override
    public Optional<BookDto> findByIdOptional(String id) {
        return bookRepository.findByIdOptional(id)
                .map(this::toDto);
    }
}
