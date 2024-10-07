package ma.enset.tp1.service;

import ma.enset.tp1.dto.BankAccountRequestDTO;
import ma.enset.tp1.dto.BankAccountResponseDTO;

public interface BankAccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
