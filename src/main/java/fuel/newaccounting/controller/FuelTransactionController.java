package fuel.newaccounting.controller;


import fuel.newaccounting.entity.FuelTransaction;
import fuel.newaccounting.repository.CarRepository;
import fuel.newaccounting.repository.FuelRepository;
import fuel.newaccounting.repository.FuelTransactionRepository;
import fuel.newaccounting.service.FuelTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class FuelTransactionController {

    private final FuelTransactionRepository fuelTransactionRepository;
    private final FuelTransactionService transactionService;
    private final CarRepository carRepository;
    private final FuelRepository fuelRepository;


    @PostMapping("/add")
    public void newTransaction(Long carId,
                               Long amount,
                               Long balance,
                               Long odometrCurrent) {
        transactionService.newTransaction(carId, amount, balance, odometrCurrent);
    }

    @GetMapping("/history")
    public List<FuelTransaction> getAllFuelTransactions() {
        return fuelTransactionRepository.findAll();
    }

    /*
     *   @DeleteMapping("/delete")
     *   public void deleteFuelTransaction(@RequestParam Long transactionId) {
     *       fuelTransactionRepository.deleteById(transactionId);
     *   }
     */
}
