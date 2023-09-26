package br.com.attornatus.Pessoas.service;

import java.util.List;

import br.com.attornatus.Pessoas.model.Endereco;
import br.com.attornatus.Pessoas.model.DTO.EnderecoDTO;


public interface EnderecoService {

    Endereco salvar(EnderecoDTO endereco);

    List<Endereco> listar();

    List<Endereco> listarPorPessoa(Long idPessoa);

}
