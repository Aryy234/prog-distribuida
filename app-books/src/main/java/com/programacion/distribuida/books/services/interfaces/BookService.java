package com.programacion.distribuida.books.services.interfaces;

import com.programacion.distribuida.books.db.Book;
import com.programacion.distribuida.books.dtos.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<BookDto> findByIdOptional(String id);

}
