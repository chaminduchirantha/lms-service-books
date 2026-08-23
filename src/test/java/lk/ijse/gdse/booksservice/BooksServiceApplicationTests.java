package lk.ijse.gdse.booksservice;

import lk.ijse.gdse.booksservice.Entity.Books;
import lk.ijse.gdse.booksservice.dto.BooksDto;
import lk.ijse.gdse.booksservice.repo.BookRepo;
import lk.ijse.gdse.booksservice.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BooksServiceApplicationTests {

	@Test
	void contextLoads() {
	}
}
