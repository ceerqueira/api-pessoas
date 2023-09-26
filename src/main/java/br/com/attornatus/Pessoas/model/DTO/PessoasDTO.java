package br.com.attornatus.Pessoas.model.DTO;

import java.sql.Date;

public record PessoasDTO(Long idPessoa,String nome, Date dataNascimento, Long idEnderecoPrincipal) {
    
}
