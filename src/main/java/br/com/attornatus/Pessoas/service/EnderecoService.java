package br.com.attornatus.Pessoas.service;

import java.util.List;

import br.com.attornatus.Pessoas.model.DTO.EnderecoDTO;


public interface EnderecoService {

    EnderecoDTO salvar(EnderecoDTO endereco);

    List<EnderecoDTO> listar();

    List<EnderecoDTO> listarPorPessoa(Long idPessoa);

}
