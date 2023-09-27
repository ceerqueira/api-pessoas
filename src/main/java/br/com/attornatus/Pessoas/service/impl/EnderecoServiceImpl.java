package br.com.attornatus.Pessoas.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.Pessoas.model.Endereco;
import br.com.attornatus.Pessoas.model.Pessoa;
import br.com.attornatus.Pessoas.model.DTO.EnderecoDTO;
import br.com.attornatus.Pessoas.repository.EnderecoRespository;
import br.com.attornatus.Pessoas.repository.PessoaRepository;
import br.com.attornatus.Pessoas.service.EnderecoService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRespository respository;

    @Autowired
    private PessoaRepository pessoasRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EnderecoDTO salvar(EnderecoDTO endereco) {

        Pessoa pessoa = pessoasRepository.findById(endereco.getIdPessoa()).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + endereco.getIdPessoa()));

        Endereco enderecoNovo = cadastrarEndereco(endereco, pessoa);

        enderecoNovo = respository.save(enderecoNovo);

        if (pessoa.getEnderecoPrincipal() == null) {
            pessoa.setEnderecoPrincipal(enderecoNovo);
            pessoasRepository.save(pessoa);
        }

        EnderecoDTO enderecoDTO = converterParaDTO(enderecoNovo);

        return enderecoDTO;
    }

    public EnderecoDTO converterParaDTO(Endereco endereco) {

        return modelMapper.map(endereco, EnderecoDTO.class);

    }

    public Endereco cadastrarEndereco(EnderecoDTO endereco, Pessoa pessoa) {
        Endereco enderecoNovo = Endereco.builder()
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .pessoa(pessoa)
                .build();

        return enderecoNovo;

    }

    @Override
    public List<EnderecoDTO> listar() {
        List<Endereco> enderecos = respository.findAll();
        List<EnderecoDTO> enderecoDTOs = converterParaDTOLista(enderecos);

        return enderecoDTOs;
    }

    @Override
    public List<EnderecoDTO> listarPorPessoa(Long idPessoa) {
        List<Endereco> enderecos = respository.findByPessoaId(idPessoa)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + idPessoa));

        List<EnderecoDTO> enderecoDTOs = converterParaDTOLista(enderecos);

        return enderecoDTOs;

    }

    public List<EnderecoDTO> converterParaDTOLista(List<Endereco> enderecos) {

        List<EnderecoDTO> enderecoDTOs = enderecos.stream()
                .map(endereco -> modelMapper.map(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());

        return enderecoDTOs;
    }

}
