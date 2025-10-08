package pt.com.aubay.challenge.natixis.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import pt.com.aubay.challenge.natixis.validator.Adult;

import java.time.LocalDate;
import java.util.List;

public record CustomerDTO(

        Long id,

        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Birth date is required")
        @Adult()
        LocalDate birthDate,

        @NotBlank(message = "Tax Identification Number is required")
        String vatNumber,

        @NotEmpty( message = "Must have at least one address")
        List< @Valid AddressDTO > addressList ) {
}
