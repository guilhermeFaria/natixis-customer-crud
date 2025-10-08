package pt.com.aubay.challenge.natixis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.com.aubay.challenge.natixis.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long > {

}
