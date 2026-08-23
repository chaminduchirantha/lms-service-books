package lk.ijse.gdse.booksservice.service;

import lk.ijse.gdse.booksservice.dto.BooksDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
    BooksDto saveBook(BooksDto bookDto, MultipartFile imageFile);
    BooksDto updateBook(String id, BooksDto bookDto, MultipartFile imageFile);
    BooksDto getBookById(String id);
    List<BooksDto> getAllBooks();
    void deleteBook(String id);
    List<BooksDto> getBooksByCategory(String category);
}