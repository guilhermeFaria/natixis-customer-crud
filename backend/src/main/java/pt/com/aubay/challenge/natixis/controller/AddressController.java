package pt.com.aubay.challenge.natixis.controller;

import org.springframework.web.bind.annotation.*;
import pt.com.aubay.challenge.natixis.dto.AddressDTO;
import pt.com.aubay.challenge.natixis.exceptions.SearchByPostalCodeNotFoundException;
import pt.com.aubay.challenge.natixis.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController( AddressService addressService ) {
        this.addressService = addressService;
    }

    /**
     * Search Address data by Postal code
     *
     * @param cp4 the first 4 numbers of identification of postal code
     * @param cp3 the last 3 numbers of identification of postal code
     * @return the address data
     */
    @GetMapping("/postal-code/{cp4}/{cp3}")
    public AddressDTO findByPostalCode( @PathVariable( "cp4" ) String cp4, @PathVariable( "cp3" ) String cp3 ) {
        return addressService.findByPostalCode( cp4, cp3 );
    }

}
