package ru.elPrezidanto.FinanceTrackerApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elPrezidanto.FinanceTrackerApp.model.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByBudgetId(long budgetId);

    List<Transaction> findByFinancialGoalId(Long financialGoalId);
}

