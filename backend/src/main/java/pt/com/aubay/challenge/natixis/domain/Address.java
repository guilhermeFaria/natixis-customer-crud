package pt.com.aubay.challenge.natixis.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.com.aubay.challenge.natixis.dto.AddressDTO;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String complement;
    private String postalCode;
    private String county;
    private String district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Address(AddressDTO dto, Customer customer ) {
        this.id = dto.id();
        this.street = dto.street();
        this.number = dto.number();
        this.complement = dto.complement();
        this.postalCode = dto.postalCode();
        this.county = dto.county();
        this.district = dto.district();
        this.customer = customer;
    }
}
