package pt.com.aubay.challenge.natixis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.com.aubay.challenge.natixis.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
