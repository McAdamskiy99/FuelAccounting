package fuel.newaccounting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FuelTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car  car;   // mashina id

    private double amount;  // quyish kerak bo'lgan yoqilg'i miqdori

    private double balance;

    private double odometr;     // odometr ko'rsatkichi

    private LocalDateTime currentDate;

}
