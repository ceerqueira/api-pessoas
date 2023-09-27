package br.com.attornatus.Pessoas.model.DTO;

import java.sql.Date;


import br.com.attornatus.Pessoas.model.Endereco;
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
public class PessoaDTO {
    private Long idPessoa;
    @NotBlank
    private String nome;
    @NotNull
    private Date dataNascimento;
    private Long idEnderecoPrincipal;
    private Endereco enderecoPrincipal;

}