package fuel.newaccounting.dto.reponse; // Paket nomidagi xatoni to'g'rilashni unutmang

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private Long id;                // Bazadagi ID (albatta kerak)

    private String regNum;          // Mashina raqami

    // Model haqida to'liqroq ma'lumot
    private Long modelId;
    private String modelName;       // Masalan: "Tahoe"

    // Haydovchi haqida ma'lumot
    private Long driverId;
    private String driverFullName;  // Masalan: "Eshmatov Toshmat"

    private double avaibleFuel;     // Yoqilg'i qoldig'i

    private double odometrBegin;    // Boshlang'ich kilometr

    private double odometrCurrent;  // Hozirgi kilometr

    private double averageFuel;     // 100 km ga sarf
}
