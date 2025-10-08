package pt.com.aubay.challenge.natixis.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.com.aubay.challenge.natixis.dto.CustomerDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String vatNumber;

    @OneToMany( mappedBy = "customer", cascade = CascadeType.ALL )
    private List< Address > addressList = new ArrayList<>();

    public Customer( CustomerDTO dto ) {
        this.id = dto.id();
        this.name = dto.name();
        this.birthDate = dto.birthDate();
        this.vatNumber = dto.vatNumber();

        this.addressList.addAll( dto.addressList().stream()
                .map( address -> new Address( address, this ) )
                .toList() );
    }

}
