package pt.com.aubay.challenge.natixis.api;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pt.com.aubay.challenge.natixis.dto.GeoDTO;
import pt.com.aubay.challenge.natixis.exceptions.SearchByPostalCodeNotFoundException;

import java.net.URI;

import static org.springframework.http.HttpMethod.GET;

@Service
public class GeoApi {

    private static final String URL = "https://json.geoapi.pt/";

    private final RestTemplate restTemplate;

    public GeoApi( RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

    /**
     * Search Address data by Postal code
     *
     * @param cp4 the first 4 numbers of identification of postal code
     * @param cp3 the last 3 numbers of identification of postal code
     * @return the address data
     */
    public GeoDTO findByPostalCode(String cp4, String cp3 ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity< ? > requestEntity = new HttpEntity<>( headers );

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromUriString( URL.concat("cp/").concat( cp4 ).concat("-" ).concat( cp3 ) );
        URI uri = builder.build().encode().toUri();

        ResponseEntity< GeoDTO > responseEntity = restTemplate.exchange( uri, GET, requestEntity, GeoDTO.class );

        if( responseEntity.getStatusCode() != HttpStatus.OK || responseEntity.getBody() == null ) {
            throw new SearchByPostalCodeNotFoundException( cp4, cp3 );
        }

        return responseEntity.getBody();

    }

}
