package pt.com.aubay.challenge.natixis.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import pt.com.aubay.challenge.natixis.domain.Customer;
import pt.com.aubay.challenge.natixis.dto.CustomerDTO;

@Named( "CustomerMapper" )
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity( CustomerDTO dto );

    CustomerDTO toDTO( Customer entity );

    default Page<CustomerDTO> toDTOsWithPage(Page<Customer> entities) {
        return entities.map(this::toDTO);
    }
}
