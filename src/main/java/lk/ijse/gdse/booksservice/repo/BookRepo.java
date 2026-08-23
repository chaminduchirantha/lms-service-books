package lk.ijse.gdse.booksservice.repo;

import lk.ijse.gdse.booksservice.Entity.Books;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface BookRepo extends MongoRepository<Books, String> {
    Collection<Object> findByCategory(String category);

    List<Books> findByCategoryIgnoreCase(String category);
}
