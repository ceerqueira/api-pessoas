package br.com.attornatus.Pessoas.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.Pessoas.model.Endereco;
import br.com.attornatus.Pessoas.model.Pessoa;
import br.com.attornatus.Pessoas.model.DTO.PessoaDTO;
import br.com.attornatus.Pessoas.repository.EnderecoRespository;
import br.com.attornatus.Pessoas.repository.PessoaRepository;
import br.com.attornatus.Pessoas.service.PessoaService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private EnderecoRespository enderecoRespository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PessoaDTO salvar(PessoaDTO pessoaDTO) {
        PessoaDTO pessoaCadastrada = cadastrarPessoa(pessoaDTO);
        return pessoaCadastrada;
    }

    private PessoaDTO cadastrarPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        pessoa.setNome(pessoaDTO.getNome());

        Pessoa pessoaCadastrada = repository.save(pessoa);
        PessoaDTO novaPessoaCadastrada = new PessoaDTO(pessoaCadastrada.getId(), pessoaCadastrada.getNome(),
                pessoaCadastrada.getDataNascimento(), 0L, null,null);

        return novaPessoaCadastrada;
    }

    @Override
    public List<PessoaDTO> listar() {
        List<Pessoa> pessoas = repository.findAll();

        List<PessoaDTO> pessoaDTOs = pessoas.stream()
                .map(pessoa -> modelMapper.map(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());

        return pessoaDTOs;
    }

    @Override
    public PessoaDTO editar(PessoaDTO pessoaDto) {
        Pessoa editarPessoa = repository.findById(pessoaDto.getIdPessoa())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pessoa não encontrada com o ID: " + pessoaDto.getIdPessoa()));

        atualizarPessoa(editarPessoa, pessoaDto);

        Pessoa converterParaDTO = repository.save(editarPessoa);

        PessoaDTO pessoaDTOImprimir = converterParaDTO(converterParaDTO);

        return pessoaDTOImprimir;
    }

    public PessoaDTO converterParaDTO(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    public void atualizarPessoa(Pessoa editarPessoa, PessoaDTO pessoaDto) {
        if (pessoaDto.getDataNascimento() != null) {
            editarPessoa.setDataNascimento(pessoaDto.getDataNascimento());
        }

        if (pessoaDto.getIdEnderecoPrincipal() != null) {
            Endereco novoEndereco = enderecoRespository.findById(pessoaDto.getIdEnderecoPrincipal())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Endereço não encontrado com o ID: " + pessoaDto.getIdEnderecoPrincipal()));

            if (novoEndereco.getPessoa() == editarPessoa) {
                editarPessoa.setEnderecoPrincipal(novoEndereco);
            } else {
                throw new RuntimeException("O endereço não pertence à mesma pessoa");
            }
        }

        if (pessoaDto.getNome() != null) {
            editarPessoa.setNome(pessoaDto.getNome());
        }
    }

    @Override
    public PessoaDTO consultarPessoa(Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + id));

        PessoaDTO pessoaDTO = converterParaDTO(pessoa);

        adicionarEnderecosAPessoaDTO(id,pessoaDTO);

        return pessoaDTO;
    }

    public void adicionarEnderecosAPessoaDTO(Long id, PessoaDTO pessoaDTO) {
        List<Endereco> listaEnderecoPessoa = enderecoRespository.findByPessoaId(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + id));

        pessoaDTO.getListaEnderecos().addAll(listaEnderecoPessoa);
    }

}
