package ma.enset.tp1.web;

import lombok.AllArgsConstructor;
import ma.enset.tp1.dto.BankAccountRequestDTO;
import ma.enset.tp1.dto.BankAccountResponseDTO;
import ma.enset.tp1.entities.BankAccount;
import ma.enset.tp1.repositories.BankAccountRepository;
import ma.enset.tp1.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BankAccountController {
    private BankAccountRepository bankAccountRepository;
    private BankAccountService bankAccountService;

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount getBankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id).orElse(null);
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO saveBankAccount(@RequestBody BankAccountRequestDTO bankAccount){
        return bankAccountService.addAccount(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount updateBankAccount(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount bankAcc = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("No account with this id "+id));
        if(bankAccount.getBalance()!=null) bankAcc.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency()!=null) bankAcc.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getType()!=null) bankAcc.setType(bankAccount.getType());
        return bankAccountRepository.save(bankAcc);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public BankAccount deleteBankAccount(@PathVariable String id){
        BankAccount bankAcc = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("No account with this id "+id));
        bankAccountRepository.deleteById(id);
        return bankAcc;
    }
}
