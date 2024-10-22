package entity;

import jakarta.persistence.*;
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
@Table(name = "Orders")
public class OrderEntity {
    @Id
    private String orderId;
    private LocalDate orderDate;
    private String orderTime;
    private String empId;
    private Double netTotal;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "OrderDetail",
            joinColumns = { @JoinColumn(name = "orderId") },
            inverseJoinColumns = { @JoinColumn(name = "itemcode") }
    )
    private List<CartTMEntity> cartDetails;
}
