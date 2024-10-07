package ma.enset.tp1.mappers;

import ma.enset.tp1.entities.BankAccount;
import ma.enset.tp1.dto.BankAccountResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
        return BankAccountResponseDTO.builder()
                .id(bankAccount.getId())
                .createdAt(bankAccount.getCreatedAt())
                .type(bankAccount.getType())
                .currency(bankAccount.getCurrency())
                .balance(bankAccount.getBalance())
                .build();
    }
}
