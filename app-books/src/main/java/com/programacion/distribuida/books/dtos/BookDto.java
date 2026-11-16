package com.programacion.distribuida.books.dtos;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link com.programacion.distribuida.books.db.Book}
 */
@Getter
@Setter
@ToString
@Builder
public class BookDto {
    private String isbn;
    private String title;
    private BigDecimal price;

    private Integer inventorySold;
    private Integer inventorySupplied;
    private Integer inventoryVersion;

    private List<AuthorDto> authors;
}