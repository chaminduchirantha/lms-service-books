package lk.ijse.gdse.booksservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Books {
    @Id
    private String id;
    private String name;
    private String title;
    private String author;
    private String category;
    private Integer totalQty;
    private Integer availableQty;
    private String imageUrl;
}
