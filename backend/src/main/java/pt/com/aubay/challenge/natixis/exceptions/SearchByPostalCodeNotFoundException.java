package pt.com.aubay.challenge.natixis.exceptions;

public class SearchByPostalCodeNotFoundException extends RuntimeException {

    private static final String TITLE = "Address not found!";

    private static final String DETAIL = "Not found address with postal code ";

    public SearchByPostalCodeNotFoundException( String cp4, String cp3 ) {
        super( TITLE + ": " + DETAIL + cp4 + "-" + cp3 );
    }
}
