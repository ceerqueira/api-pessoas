package br.com.attornatus.Pessoas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.attornatus.Pessoas.model.Endereco;
@Repository
public interface EnderecoRespository extends JpaRepository<Endereco,Long>{
    List<Endereco> findByPessoasId(Long pessoaId);
}
