package ru.elPrezidanto.FinanceTrackerApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elPrezidanto.FinanceTrackerApp.controller.common.ICrudController;
import ru.elPrezidanto.FinanceTrackerApp.model.dto.TransactionDTO;

/**
 * REST-интерфейс транзакции
 */
@RestController
@RequestMapping("/transactions")
public interface ITransactionController extends ICrudController<TransactionDTO, String> {

}