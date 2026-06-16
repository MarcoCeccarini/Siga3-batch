package it.finanze.siga.rest;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;


@Schema(name = "Esito", description = "Codice e descrizione esito operazione")
public record Esito(

        @NotNull(message = "{not.null}")
        @Schema(description = "Codice esito", name="codiceEsito")
        Integer codiceEsito,

        @NotNull(message = "{not.null}")
        @Schema(description = "Descrizione Esito", name="descrizioneEsito")
        String descrizioneEsito
        
        
) {
	public Esito {
		if(codiceEsito==null) {
			codiceEsito = -1;
			descrizioneEsito = "Inizializzazione";
		}
		
	}
	
}
