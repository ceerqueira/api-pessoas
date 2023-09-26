package br.com.attornatus.Pessoas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.attornatus.Pessoas.model.Endereco;
import br.com.attornatus.Pessoas.model.Pessoas;
import br.com.attornatus.Pessoas.model.DTO.EnderecoDTO;
import br.com.attornatus.Pessoas.repository.EnderecoRespository;
import br.com.attornatus.Pessoas.repository.PessoasRepository;
import br.com.attornatus.Pessoas.service.EnderecoService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoServiceImpl implements EnderecoService{

    @Autowired
    private EnderecoRespository respository;

    @Autowired
    private PessoasRepository pessoasRepository;

    @Override
    @JsonIgnore
    public Endereco salvar(EnderecoDTO endereco) {

        Pessoas pessoas = pessoasRepository.findById(endereco.idPessoa())
            .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + endereco.idPessoa()));

        if(pessoas.getEnderecoPrincipal() == null){
            pessoas.setEnderecoPrincipal(null);
        }

        Endereco enderecoNovo = Endereco.builder()
        .cep(endereco.cep())
        .cidade(endereco.cidade())
        .logradouro(endereco.logradouro())
        .numero(endereco.numero())
        .pessoas(pessoas)
        .build();

        enderecoNovo = respository.save(enderecoNovo);
        if(pessoas.getEnderecoPrincipal() == null){
            pessoas.setEnderecoPrincipal(enderecoNovo);
            pessoasRepository.save(pessoas);
        } 
        
        return enderecoNovo;
    }

    @Override
    public List<Endereco> listar() {
        return respository.findAll();
    }

    @Override
    public List<Endereco> listarPorPessoa(Long idPessoa) {
        return respository.findByPessoasId(idPessoa);
    }
    
}
