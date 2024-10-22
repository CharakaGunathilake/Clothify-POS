package entity;

import dto.OrderDetail;
import dto.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
public class OrderEntity {
    @Id
    private String orderId;
    private LocalDate orderDate;
    private Double netTotal;
    @ManyToMany
    private List<OrderDetailEntity> orderDetail;
}
