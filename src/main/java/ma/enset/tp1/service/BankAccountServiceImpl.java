package ma.enset.tp1.service;

import lombok.AllArgsConstructor;
import ma.enset.tp1.entities.BankAccount;
import ma.enset.tp1.mappers.BankAccountMapper;
import ma.enset.tp1.repositories.BankAccountRepository;
import ma.enset.tp1.dto.BankAccountRequestDTO;
import ma.enset.tp1.dto.BankAccountResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private BankAccountRepository bankAccountRepository;
    private BankAccountMapper bankAccountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .balance(bankAccountDTO.getBalance())
                .build();
        final BankAccount saved = bankAccountRepository.save(bankAccount);

        return bankAccountMapper.fromBankAccount(saved);
    }
}
