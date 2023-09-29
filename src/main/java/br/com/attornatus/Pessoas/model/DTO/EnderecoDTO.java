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
    @NotBlank (message = "logradouro não pode ser vazio")
    private String logradouro; 
    @NotBlank (message = "cep não pode ser vazio")
    private String cep;
    @NotNull (message = "numero não pode ser nulo")
    private int numero;
    @NotBlank (message = "cidade não pode ser vazio")
    private String cidade ;
    @NotNull (message = "idPessoa não pode ser nulo")
    private Long idPessoa;
}
