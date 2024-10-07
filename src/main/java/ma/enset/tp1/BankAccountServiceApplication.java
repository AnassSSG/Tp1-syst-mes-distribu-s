package ma.enset.tp1;

import ma.enset.tp1.entities.BankAccount;
import ma.enset.tp1.entities.Customer;
import ma.enset.tp1.enums.AccountType;
import ma.enset.tp1.repositories.BankAccountRepository;
import ma.enset.tp1.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository,
							CustomerRepository customerRepository){
		return args -> {
			Stream.of("Hicham","Oussama","Walid").forEach(name -> {
				Customer customer = customerRepository.save(new Customer(null,name,null));
				for (int i = 0; i < 3; i++) {
					BankAccount bankAccount = BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.type(Math.random()>0.5 ? AccountType.SAVING_ACCOUNT : AccountType.CURRENT_ACCOUNT)
							.balance(Math.random()*90_000 + 10_000)
							.createdAt(new Date())
							.currency("MAD")
							.owner(customer)
							.build();
					bankAccountRepository.save(bankAccount);
				}
			});


		};
	}

}
