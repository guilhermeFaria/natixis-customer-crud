package pt.com.aubay.challenge.natixis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GeoDTO(

        String cp,
        @JsonProperty( "CP4" )
        String cp4,
        @JsonProperty( "CP3" )
        String cp3,
        @JsonProperty( "Concelho" )
        String county,
        @JsonProperty( "Distrito" )
        String district,
        List<Parte> partes
) {
    public record Parte(
            @JsonProperty( "Artéria")
            String arteria,
            String Local,
            @JsonProperty( "Troço" )
            String Troco,
            String Porta,
            String Cliente
    ) {}
}
