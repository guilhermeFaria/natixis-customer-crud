package pt.com.aubay.challenge.natixis.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import pt.com.aubay.challenge.natixis.dto.AddressDTO;
import pt.com.aubay.challenge.natixis.dto.GeoDTO;

@Named( "AddressMapper" )
@Mapper( componentModel = "spring" )
public interface GeoMapper {

    default AddressDTO toAddressDTO(GeoDTO geo ) {
        if (geo == null) return null;

        String street = geo.partes() != null && !geo.partes().isEmpty()
                ? geo.partes().get(0).arteria() : null;

        String postalCode = geo.cp() != null ? geo.cp() : geo.cp4() + "-" + geo.cp3();

        return new AddressDTO(
                null,
                street,
                null,
                null,
                postalCode,
                geo.county(),
                geo.district()
        );
    }

}
