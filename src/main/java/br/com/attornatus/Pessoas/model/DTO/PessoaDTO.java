package br.com.attornatus.Pessoas.model.DTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.attornatus.Pessoas.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaDTO {

    private Long idPessoa;
    @NotBlank (message = "nome não pode ser vazio")

    private String nome;
    @NotNull (message = "dataNascimento não pode ser nulo")

    private Date dataNascimento;

    private Long idEnderecoPrincipal;

    private Endereco enderecoPrincipal;

private List<Endereco> listaEnderecos = new ArrayList<>();


}