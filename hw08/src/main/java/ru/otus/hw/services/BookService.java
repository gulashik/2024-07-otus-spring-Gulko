package ru.otus.hw.services;

import ru.otus.hw.models.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<BookDto> findById(String id);

    List<BookDto> findAll();

    BookDto insert(String title, String authorId, String genreId);

    BookDto update(String id, String title, String authorId, String genreId);

    void deleteById(String id);
}
