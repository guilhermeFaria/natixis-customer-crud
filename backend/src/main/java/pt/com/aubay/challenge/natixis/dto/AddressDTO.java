package pt.com.aubay.challenge.natixis.dto;

public record AddressDTO(
        Long id,
        String street,
        String number,
        String complement,
        String postalCode,
        String county,
        String district ) {
}
