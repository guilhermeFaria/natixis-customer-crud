package pt.com.aubay.challenge.natixis.service;

import org.springframework.stereotype.Service;
import pt.com.aubay.challenge.natixis.api.GeoApi;
import pt.com.aubay.challenge.natixis.dto.AddressDTO;
import pt.com.aubay.challenge.natixis.mappers.GeoMapper;

@Service
public class AddressService {

    private final GeoApi geoApi;

    private final GeoMapper mapper;

    public AddressService( GeoApi geoApi, GeoMapper geoMapper ) {
        this.geoApi = geoApi;
        this.mapper = geoMapper;
    }

    /**
     * Search Address data by Postal code
     *
     * @param cp4 the first 4 numbers of identification of postal code
     * @param cp3 the last 3 numbers of identification of postal code
     * @return the address data
     */
    public AddressDTO findByPostalCode(String cp4, String cp3 ) {
        return mapper.toAddressDTO( geoApi.findByPostalCode( cp4, cp3 ) );
    }
}
