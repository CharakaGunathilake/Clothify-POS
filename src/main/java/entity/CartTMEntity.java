package entity;

import jakarta.persistence.*;
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
public class CartTMEntity {
    @Id
    private String itemCode;
    private String name;
    private Integer qty;
    private Double unitPrice;
    private Double total;
    @ManyToMany(cascade = CascadeType.ALL
            ,mappedBy = "cartDetails")
    @JoinTable(name = "OrderEntity",joinColumns = @JoinColumn(name = "orderId"))
    private List<OrderEntity> order;
}
