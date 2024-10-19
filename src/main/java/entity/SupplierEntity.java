package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SupplierEntity {
    private String id;
    private String name;
    private String company;
    private String email;
    private String item;
}
