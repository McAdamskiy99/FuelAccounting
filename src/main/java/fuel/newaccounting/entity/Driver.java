package fuel.newaccounting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Driver {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String lastname;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String license;


}
