package fuel.newaccounting.controller;


import fuel.newaccounting.entity.Transaction;
import fuel.newaccounting.repository.CarRepository;
import fuel.newaccounting.repository.FuelRepository;
import fuel.newaccounting.repository.TransactionRepository;
import fuel.newaccounting.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
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
    public List<Transaction> getAllFuelTransactions() {
        return transactionRepository.findAll();
    }

    /*
     *   @DeleteMapping("/delete")
     *   public void deleteFuelTransaction(@RequestParam Long transactionId) {
     *       fuelTransactionRepository.deleteById(transactionId);
     *   }
     */
}
