package pt.com.aubay.challenge.natixis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.com.aubay.challenge.natixis.domain.Customer;
import pt.com.aubay.challenge.natixis.dto.CustomerDTO;
import pt.com.aubay.challenge.natixis.mappers.CustomerMapper;
import pt.com.aubay.challenge.natixis.exceptions.CustomerNotFoundException;
import pt.com.aubay.challenge.natixis.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper ) {
        this.customerRepository = customerRepository;
        this.mapper = customerMapper;
    }

    /**
     * Creates a customer
     * @param customerDTO information given in the request
     * @return the created customer
     */
    @Transactional
    public CustomerDTO create( CustomerDTO customerDTO ) {
        return mapper.toDTO( customerRepository.save( new Customer( customerDTO ) ) );
    }

    /**
     * Update the register of customer
     * @param customerId the identifier of customer
     * @param customerDTO information given in the request
     * @return the updated customer
     */
    @Transactional
    public CustomerDTO update( Long customerId, CustomerDTO customerDTO ) {
        customerRepository.findById( customerId )
                .orElseThrow( () -> new CustomerNotFoundException( customerId.toString() ) );
        Customer customer = new Customer( customerDTO );
        return mapper.toDTO( customerRepository.save( customer ) );
    }

    /**
     * Delete a register of Customer
     *
     * @param customerId the identifier of customer
     */
    @Transactional
    public void delete( Long customerId ) {
        customerRepository.findById( customerId )
                .orElseThrow( () -> new CustomerNotFoundException( customerId.toString() ) );
        customerRepository.deleteById( customerId );
    }

    /**
     * Load customers
     * @param pageable the pageable
     * @return a list of Customers
     */
    @Transactional
    public Page< CustomerDTO > findByAll(Pageable pageable ) {
        return mapper.toDTOsWithPage( customerRepository.findAll( pageable ) );
    }
}
