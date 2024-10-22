package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartTM {
    private String itemCode;
    private String name;
    private String category;
    private Integer qty;
    private Double unitPrice;
    private Double total;
}
