package ru.elPrezidanto.FinanceTrackerApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elPrezidanto.FinanceTrackerApp.model.Budget;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByBankAccountId(Long accountId);

    List<Budget> findByBankAccountUserUsername(String username);

}