package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

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
    private String empName;
    private Double netTotal;
}
