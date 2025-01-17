package ru.elPrezidanto.FinanceTrackerApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elPrezidanto.FinanceTrackerApp.model.BankAccount;
import ru.elPrezidanto.FinanceTrackerApp.repo.BankAccountRepository;

import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public Optional<BankAccount> getBankAccountById(Long id) {
        return bankAccountRepository.findById(id);
    }

    public BankAccount updateBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount getBankAccountByUserUsername(String username) {
        return bankAccountRepository.findBankAccountByUserUsername(username);
    }
}