package ru.elPrezidanto.FinanceTrackerApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.elPrezidanto.FinanceTrackerApp.model.Transaction;
import ru.elPrezidanto.FinanceTrackerApp.repo.BudgetRepository;
import ru.elPrezidanto.FinanceTrackerApp.service.BankAccountService;
import ru.elPrezidanto.FinanceTrackerApp.service.BudgetService;
import ru.elPrezidanto.FinanceTrackerApp.service.TransactionService;

import java.math.BigDecimal;
import java.util.List;

import static ru.elPrezidanto.FinanceTrackerApp.controller.BudgetPageController.getCurrentUsername;

@Controller
@RequestMapping("/transactions")
public class TransactionPageController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/create")
    public String createTransactionForm(Model model) {
        String username = getCurrentUsername();
        model.addAttribute("budgets", budgetService.findByBankAccountUserUsername(username));
        model.addAttribute("transaction", new Transaction());
        return "transactions/create-transaction";
    }

    @PostMapping
    public String createTraSnsaction(@RequestParam Long budgetId,
                                    @RequestParam String name,
                                    @RequestParam BigDecimal amount,
                                    @RequestParam String type,
                                    @RequestParam String transactionDate,
                                    @RequestParam String description) {

        transactionService.createTransaction(budgetId, name, amount, type, transactionDate, description);
        return "redirect:/transactions";
    }

    @GetMapping
    public String viewTransactions(Model model) {
        String username = getCurrentUsername();
        List<Transaction> transactions = transactionService.getTransactionsByUserName(username);
        model.addAttribute("transactions", transactions);
        return "transactions/transactions";
    }

}