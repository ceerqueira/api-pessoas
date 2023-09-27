package br.com.attornatus.Pessoas.service;

import java.util.List;

import br.com.attornatus.Pessoas.model.DTO.PessoaDTO;

public interface PessoaService {

    public PessoaDTO salvar(PessoaDTO pessoa);

    public List<PessoaDTO> listar();

    public PessoaDTO editar(PessoaDTO pessoa);

    public PessoaDTO consultarPessoa(Long id);

}
