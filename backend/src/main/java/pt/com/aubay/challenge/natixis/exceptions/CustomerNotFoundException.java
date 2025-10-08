package pt.com.aubay.challenge.natixis.exceptions;

import lombok.Data;

@Data
public class CustomerNotFoundException extends RuntimeException {

    private static final String TITLE = "Customer not found!";

    private static final String DETAIL = "Not found customer with id ";

    public CustomerNotFoundException( String id ) {
        super( TITLE + ": " + DETAIL + id );
    }
}
