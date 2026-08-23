package lk.ijse.gdse.booksservice.service.impl;

import lk.ijse.gdse.booksservice.Entity.Books;
import lk.ijse.gdse.booksservice.dto.BooksDto;
import lk.ijse.gdse.booksservice.repo.BookRepo;
import lk.ijse.gdse.booksservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final ModelMapper modelMapper;
    private final BookRepo bookRepo;
    private final CloudStorageService cloudStorageService;

    @Override
    public BooksDto saveBook(BooksDto bookDto, MultipartFile imageFile) {
        Books book = modelMapper.map(bookDto, Books.class);

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String imageUrl = cloudStorageService.uploadImage(imageFile);
                book.setImageUrl(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save book image to GCP: " + e.getMessage());
            }
        }

        Books savedBook = bookRepo.save(book);
        return modelMapper.map(savedBook, BooksDto.class);
    }

    @Override
    public BooksDto updateBook(String id, BooksDto bookDto, MultipartFile imageFile) {
        Books book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

        book.setName(bookDto.getName());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(bookDto.getCategory());
        book.setTotalQty(bookDto.getTotalQty());
        book.setAvailableQty(bookDto.getAvailableQty());

        if (bookDto.getImageUrl() != null && !bookDto.getImageUrl().trim().isEmpty()) {
            book.setImageUrl(bookDto.getImageUrl().trim());
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String imageUrl = cloudStorageService.uploadImage(imageFile);
                book.setImageUrl(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException("Failed to update book image on GCP: " + e.getMessage());
            }
        }

        Books updatedBook = bookRepo.save(book);
        return modelMapper.map(updatedBook, BooksDto.class);
    }

    @Override
    public BooksDto getBookById(String id) {
        Books book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
        return modelMapper.map(book, BooksDto.class);
    }

    @Override
    public List<BooksDto> getAllBooks() {
        List<Books> books = bookRepo.findAll();
        List<BooksDto> bookDtos = new ArrayList<>();
        for (Books book : books) {
            bookDtos.add(modelMapper.map(book, BooksDto.class));
        }
        return bookDtos;
    }

    @Override
    public void deleteBook(String id) {
        Books book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
        bookRepo.delete(book);
    }

    @Override
    public List<BooksDto> getBooksByCategory(String category) {
        List<Books> books = bookRepo.findByCategoryIgnoreCase(category);
        return modelMapper.map(books, new TypeToken<List<BooksDto>>() {}.getType());
    }
}