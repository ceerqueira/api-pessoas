package br.com.attornatus.Pessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.attornatus.Pessoas.model.Pessoas;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Long> {
    
}
