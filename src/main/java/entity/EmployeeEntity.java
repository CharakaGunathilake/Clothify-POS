package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee")
public class EmployeeEntity {
    @Id
    private String id;
    private String name;
    private String company;
    private String email;
    private String contact;
}
