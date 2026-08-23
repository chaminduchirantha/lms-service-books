package lk.ijse.gdse.booksservice.controller;

import lk.ijse.gdse.booksservice.dto.BooksDto;
import lk.ijse.gdse.booksservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksDto> saveBookJson(@RequestBody BooksDto booksDto) {
        BooksDto savedBook = bookService.saveBook(booksDto, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BooksDto> saveBook(
            @RequestPart(value = "data", required = false) BooksDto booksDto,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "totalQty", required = false) Integer totalQty,
            @RequestParam(value = "availableQty", required = false) Integer availableQty,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        if (booksDto == null) {
            booksDto = new BooksDto();
        }

        if (name != null) booksDto.setName(name);
        if (title != null) booksDto.setTitle(title);
        if (author != null) booksDto.setAuthor(author);
        if (category != null) booksDto.setCategory(category);
        if (totalQty != null) booksDto.setTotalQty(totalQty);
        if (availableQty != null) booksDto.setAvailableQty(availableQty);

        BooksDto savedBook = bookService.saveBook(booksDto, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksDto> updateBookJson(@PathVariable String id, @RequestBody BooksDto booksDto) {
        BooksDto updatedBook = bookService.updateBook(id, booksDto, null);
        return ResponseEntity.ok(updatedBook);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BooksDto> updateBook(
            @PathVariable String id,
            @RequestPart(value = "data", required = false) BooksDto booksDto,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "totalQty", required = false) Integer totalQty,
            @RequestParam(value = "availableQty", required = false) Integer availableQty,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        if (booksDto == null) {
            booksDto = new BooksDto();
        }

        if (name != null) booksDto.setName(name);
        if (title != null) booksDto.setTitle(title);
        if (author != null) booksDto.setAuthor(author);
        if (category != null) booksDto.setCategory(category);
        if (totalQty != null) booksDto.setTotalQty(totalQty);
        if (availableQty != null) booksDto.setAvailableQty(availableQty);

        BooksDto updatedBook = bookService.updateBook(id, booksDto, file);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksDto> getBookById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BooksDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully!");
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<BooksDto>> getBooksByCategory(@PathVariable String category) {
        return ResponseEntity.ok(bookService.getBooksByCategory(category));
    }
}