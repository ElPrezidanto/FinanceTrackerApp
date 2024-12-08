package ru.elPrezidanto.FinanceTrackerApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elPrezidanto.FinanceTrackerApp.model.BankAccount;
import ru.elPrezidanto.FinanceTrackerApp.model.Budget;
import ru.elPrezidanto.FinanceTrackerApp.model.Transaction;
import ru.elPrezidanto.FinanceTrackerApp.model.enums.TransactionType;
import ru.elPrezidanto.FinanceTrackerApp.repo.BudgetRepository;
import ru.elPrezidanto.FinanceTrackerApp.repo.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private BankAccountService bankAccountService;

    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }


    public String createTransaction(Long budgetId,
                                    String name,
                                    BigDecimal amount,
                                    String type,
                                    String transactionDate,
                                    String description) {

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        BankAccount bankAccount = budget.getBankAccount();

        Transaction transaction = new Transaction();
        transaction.setName(name);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.valueOf(type.toUpperCase()));
        transaction.setBudget(budget);

        // Устанавливаем дату транзакции текущей, если не указана
        LocalDate date = (transactionDate == null || transactionDate.isEmpty())
                ? LocalDate.now()
                : LocalDate.parse(transactionDate);
        transaction.setTransactionDate(date);

        transaction.setDescription(description);

        if (transaction.getType() == TransactionType.INCOME) {
            bankAccount.setBalance(bankAccount.getBalance().add(amount));
        } else if (transaction.getType() == TransactionType.EXPENSE) {
            if (amount.compareTo(budget.getAmount()) > 0) {
                throw new IllegalArgumentException("Transaction amount exceeds budget available.");
            }
            budget.setAmount(budget.getAmount().subtract(amount));
            bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
        }

        transactionRepository.save(transaction);
        bankAccountService.updateBankAccount(bankAccount);
        budgetRepository.save(budget);
        return "redirect:/transactions";
    }

    public List<Transaction> getTransactionsByUserName(String userName) {
        List<Budget> budgets = budgetRepository.findByBankAccountUserUsername(userName);
        List<Transaction> allUserTransactions = new ArrayList<>();
        for (Budget budget : budgets) {
            allUserTransactions.addAll(budget.getTransactions());
        }
        return allUserTransactions;
    }

    public List<Transaction> findTransactionsByBudgetId(Long budgetId) {
        return transactionRepository.findByBudgetId(budgetId);
    }

    public List<Transaction> findTransactionsByFinancialGoalId(Long financialGoalId) {
        return transactionRepository.findByFinancialGoalId(financialGoalId);
    }
}