package ru.elPrezidanto.FinanceTrackerApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.elPrezidanto.FinanceTrackerApp.controller.common.ICrudController;
import ru.elPrezidanto.FinanceTrackerApp.model.dto.BankAccountDTO;

/**
 * REST-интерфейс банковского аккаунта
 */
@RequestMapping("/bank-account")
public interface IBankAccountController extends ICrudController<BankAccountDTO, String> {

}
