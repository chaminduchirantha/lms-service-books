package lk.ijse.gdse.booksservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BooksDto {
    private String id;
    private String name;
    private String title;
    private String author;
    private String category;
    private Integer totalQty;
    private Integer availableQty;

    private String imageUrl;
}
