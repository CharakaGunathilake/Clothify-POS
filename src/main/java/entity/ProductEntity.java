package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

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
