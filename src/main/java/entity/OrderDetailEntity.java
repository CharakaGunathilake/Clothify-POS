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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetailEntity {
    @Id
    private String id;
    private String category;
    private Double total;
    private Integer qty;
    private Double discount;
    @ManyToMany(mappedBy = "orderDetail")
    private List<OrderEntity> order;
}
