package br.com.attornatus.Pessoas.service;

import java.util.List;

import br.com.attornatus.Pessoas.model.Pessoas;
import br.com.attornatus.Pessoas.model.DTO.PessoasDTO;

public interface PessoasService {

    public Pessoas salvar(Pessoas pessoas);

    public List<Pessoas> listar();

    public Pessoas editar(PessoasDTO pessoa);

}
