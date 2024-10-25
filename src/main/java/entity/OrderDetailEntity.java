package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OrderDetail")
public class OrderDetailEntity {
    @Id
    private String orderId;
    @Id
    private String itemCode;
    private String name;
    private Integer qty;
    private Double unitPrice;
    private Double total;
}
