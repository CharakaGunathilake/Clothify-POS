package dto;

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
public class Order {
    private String orderId;
    private LocalDate orderDate;
    private Double netTotal;
    private List<OrderDetail> orderDetails;
}
