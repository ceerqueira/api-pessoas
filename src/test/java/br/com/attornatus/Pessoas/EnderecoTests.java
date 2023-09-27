package br.com.attornatus.Pessoas;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.attornatus.Pessoas.model.Endereco;
import br.com.attornatus.Pessoas.model.Pessoa;
import br.com.attornatus.Pessoas.model.DTO.EnderecoDTO;
import br.com.attornatus.Pessoas.repository.EnderecoRespository;
import br.com.attornatus.Pessoas.repository.PessoaRepository;
import br.com.attornatus.Pessoas.service.impl.EnderecoServiceImpl;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceImplTests {
/* 
    @InjectMocks
    private EnderecoServiceImpl enderecoService;

    @Mock
    private EnderecoRespository enderecoRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    void testSalvarEndereco() {
        Long pessoaId = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(pessoa));

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setIdPessoa(pessoaId);

        Endereco enderecoNovo = new Endereco(); // Crie um objeto Endereco simulado
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoNovo);

        EnderecoDTO resultado = enderecoService.salvar(enderecoDTO);

        verify(enderecoRepository, times(1)).save(any(Endereco.class));

        assertNotNull(resultado);
    }

    @Test
    void testListarEnderecos() {
        // Simule uma lista de endereços
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco());
        enderecos.add(new Endereco());
        
        when(enderecoRepository.findAll()).thenReturn(enderecos);

        List<EnderecoDTO> resultado = enderecoService.listar();

        assertNotNull(resultado);
        assertEquals(enderecos.size(), resultado.size());
    }

    @Test
    void testListarEnderecosPorPessoa() {
        Long pessoaId = 1L;
        // Simule uma lista de endereços associados a uma pessoa
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco());
        enderecos.add(new Endereco());

        when(enderecoRepository.findByPessoaId(pessoaId)).thenReturn(Optional.of(enderecos));

        List<EnderecoDTO> resultado = enderecoService.listarPorPessoa(pessoaId);
        assertNotNull(resultado);
        assertEquals(enderecos.size(), resultado.size());
    } */
}
