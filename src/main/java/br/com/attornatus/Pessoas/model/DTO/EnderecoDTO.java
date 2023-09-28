package br.com.attornatus.Pessoas.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoDTO {
    @NotBlank
    private String logradouro; 
    @NotBlank
    private String cep;
    @NotNull
    private int numero;
    private String cidade ;
    @NotNull
    private Long idPessoa;
}
