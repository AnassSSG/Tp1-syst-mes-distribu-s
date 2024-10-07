package ma.enset.tp1.web;

import lombok.AllArgsConstructor;
import ma.enset.tp1.dto.BankAccountRequestDTO;
import ma.enset.tp1.dto.BankAccountResponseDTO;
import ma.enset.tp1.entities.BankAccount;
import ma.enset.tp1.entities.Customer;
import ma.enset.tp1.repositories.BankAccountRepository;
import ma.enset.tp1.repositories.CustomerRepository;
import ma.enset.tp1.service.BankAccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class BankAccountGraphQLController {
    private BankAccountRepository bankAccountRepository;
    private BankAccountService bankAccountService;
    private CustomerRepository customerRepository;

    @QueryMapping
    public List<BankAccount> accountList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount getAccount(@Argument String id){
        return bankAccountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No account with id : "+id)
                );
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return bankAccountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccount updateBankAccount(@Argument String id, @Argument BankAccount bankAccount){
        BankAccount bankAcc = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("No account with this id "+id));
        if(bankAccount.getBalance()!=null) bankAcc.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency()!=null) bankAcc.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getType()!=null) bankAcc.setType(bankAccount.getType());
        return bankAccountRepository.save(bankAcc);
    }

    @MutationMapping
    public boolean deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
        return true;
    }

    @QueryMapping
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }
}

