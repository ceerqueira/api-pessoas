package br.com.attornatus.Pessoas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.attornatus.Pessoas.model.Pessoa;
import br.com.attornatus.Pessoas.model.DTO.PessoaDTO;
import br.com.attornatus.Pessoas.repository.EnderecoRespository;
import br.com.attornatus.Pessoas.repository.PessoaRepository;
import br.com.attornatus.Pessoas.service.impl.EnderecoServiceImpl;
import br.com.attornatus.Pessoas.service.impl.PessoaServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PessoaServiceImplTests {

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @InjectMocks
    private EnderecoServiceImpl enderecoService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private EnderecoRespository enderecoRepository;

    @Mock
    private ModelMapper modelMapper;

/*     @Test
    void testSalvarPessoa() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("João");
        pessoaDTO.setDataNascimento(new Date(1992));
        pessoaDTO.setIdEnderecoPrincipal(1L);

        Pessoa pessoaSalva = new Pessoa();
        pessoaSalva.setId(1L);
        pessoaSalva.setNome("João");
        pessoaSalva.setDataNascimento(new Date(1993));

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaSalva);

        PessoaDTO resultado = pessoaService.salvar(pessoaDTO);

        assertNull(resultado);
    }

    @Test
    void testListarPessoas() {
        List<Pessoa> pessoas = Arrays.asList(new Pessoa(), new Pessoa());
        when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<PessoaDTO> resultado = pessoaService.listar();

        assertNull(resultado);
        assertEquals(pessoas.size(), resultado.size());
    } */
}