package br.com.attornatus.Pessoas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.Pessoas.model.Endereco;
import br.com.attornatus.Pessoas.model.Pessoas;
import br.com.attornatus.Pessoas.model.DTO.PessoasDTO;
import br.com.attornatus.Pessoas.repository.EnderecoRespository;
import br.com.attornatus.Pessoas.repository.PessoasRepository;
import br.com.attornatus.Pessoas.service.PessoasService;
import jakarta.persistence.EntityNotFoundException;


@Service
public class PessoasServiceImpl implements PessoasService{

    @Autowired
    private PessoasRepository repository;

    @Autowired
    private EnderecoRespository enderecoRespository;

    @Override
    public Pessoas salvar(Pessoas pessoas) {
        return repository.save(pessoas);
    }

    @Override
    public List<Pessoas> listar() {
        return repository.findAll();
    }

    @Override
    public Pessoas editar(PessoasDTO pessoa) {
        Pessoas editarPessoa = repository.findById(pessoa.idPessoa())
        .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + pessoa.idPessoa()));

        if(pessoa.dataNascimento() != null){
            editarPessoa.setDataNascimento(pessoa.dataNascimento());
        }
        if(pessoa.idEnderecoPrincipal() != null){
            Endereco novoEndereco = enderecoRespository.findById(pessoa.idEnderecoPrincipal())
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com o ID: " + pessoa.idEnderecoPrincipal()));

              if(novoEndereco.getPessoas() == editarPessoa){
                editarPessoa.setEnderecoPrincipal(novoEndereco);
            }else{
                throw new RuntimeException("O endereço não pertence a mesma pessoa");
            }
        }
        if(pessoa.nome() != null){
            editarPessoa.setNome(pessoa.nome());
        }
        return repository.save(editarPessoa);
    }
    
}
