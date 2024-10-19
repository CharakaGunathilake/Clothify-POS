package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private String category;
    private Integer size;
    private Double price;
    private Integer qty;
    private String supplier;
}
