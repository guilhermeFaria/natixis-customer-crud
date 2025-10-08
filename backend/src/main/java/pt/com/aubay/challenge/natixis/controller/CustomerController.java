package pt.com.aubay.challenge.natixis.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.com.aubay.challenge.natixis.dto.CustomerDTO;
import pt.com.aubay.challenge.natixis.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController( CustomerService customerService ) {
        this.customerService = customerService;
    }

    /**
     * Load customers
     *
     * @param pageable the pageable
     * @return a list of Customers
     */
    @GetMapping
    public Page< CustomerDTO > getAll(Pageable pageable ) {
        return customerService.findByAll( pageable );
    }

    /**
     * Creates a customer
     *
     * @param dto information given in the request
     * @return the created customer
     */
    @PostMapping
    public CustomerDTO create( @Valid @RequestBody CustomerDTO dto ) {
        return customerService.create( dto );
    }

    /**
     * Update the register of customer
     *
     * @param customerId the identifier of customer
     * @param dto information given in the request
     * @return the updated customer
     */
    @PutMapping( "/{customerId}" )
    public CustomerDTO update( @PathVariable( "customerId" ) Long customerId, @Valid @RequestBody CustomerDTO dto ) {
        return customerService.update( customerId, dto );
    }

    /**
     * Delete a register of Customer
     *
     * @param customerId the identifier of customer
     */
    @DeleteMapping( "/{customerId}" )
    public ResponseEntity< Void > delete(@PathVariable( "customerId" ) Long customerId ) {
        customerService.delete( customerId );
        return ResponseEntity.noContent().build();
    }

}
