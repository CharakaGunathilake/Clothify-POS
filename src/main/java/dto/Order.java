package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Order {
    private String orderId;
    private LocalDate orderDate;
    private String orderTime;
    private String empId;
    private String empName;
    private Double netTotal;
    private Set<OrderDetail> orderDetails;
}
