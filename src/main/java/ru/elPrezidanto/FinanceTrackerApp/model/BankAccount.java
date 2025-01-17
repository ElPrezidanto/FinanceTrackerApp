package ru.elPrezidanto.FinanceTrackerApp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.elPrezidanto.FinanceTrackerApp.model.base.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Сущность банковского аккаунта
 */
@Getter
@Setter
@Entity
@Table(name = "bank_account")
public class BankAccount extends BaseEntity {

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bankAccount", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Budget> budget;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bankAccount", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<FinancialGoal> financialGoals;

}
