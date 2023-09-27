package br.com.attornatus.Pessoas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import br.com.attornatus.Pessoas.model.Endereco;
import br.com.attornatus.Pessoas.model.Pessoa;
import br.com.attornatus.Pessoas.model.DTO.PessoaDTO;
import br.com.attornatus.Pessoas.repository.EnderecoRespository;
import br.com.attornatus.Pessoas.repository.PessoaRepository;
import br.com.attornatus.Pessoas.service.impl.EnderecoServiceImpl;
import br.com.attornatus.Pessoas.service.impl.PessoaServiceImpl;

public class PessoaServiceTests {

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

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        when(modelMapper.map(any(Pessoa.class), eq(PessoaDTO.class)))
                .thenAnswer(invocation -> {
                    Pessoa pessoa = invocation.getArgument(0);
                    PessoaDTO pessoaDTO = new PessoaDTO();
                    pessoaDTO.setIdPessoa(pessoa.getId());
                    pessoaDTO.setNome(pessoa.getNome());
                    return pessoaDTO;
                });
    }

    @Test
    public void testSalvarPessoa() {
        // Preparar dados de entrada
        PessoaDTO pessoaDTOEntrada = new PessoaDTO();
        pessoaDTOEntrada.setNome("João");
        pessoaDTOEntrada.setDataNascimento(new Date(1992));

        Pessoa pessoaSalva = new Pessoa();
        pessoaSalva.setId(1L);
        pessoaSalva.setNome("João");
        pessoaSalva.setDataNascimento(new Date(1993));

        // Preparar comportamento do repositório
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaSalva);

        // Executar a ação do serviço
        PessoaDTO resultado = pessoaService.salvar(pessoaDTOEntrada);

        // Verificar resultados
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdPessoa());
        assertEquals("João", resultado.getNome());
    }

    @Test
    public void testListarPessoas() {
        // Preparar comportamento do repositório
        List<Pessoa> pessoas = Arrays.asList(new Pessoa(), new Pessoa());
        when(pessoaRepository.findAll()).thenReturn(pessoas);

        // Executar a ação do serviço
        List<PessoaDTO> resultado = pessoaService.listar();

        // Verificar resultados
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    public void testConverterParaDTO() {
        // Preparar dados de entrada
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");

        // Executar a ação do serviço
        PessoaDTO resultado = pessoaService.converterParaDTO(pessoa);

        // Verificar resultados
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdPessoa());
        assertEquals("João", resultado.getNome());
    }

    @Test
    public void testConsultarPessoa() {
        Long pessoaId = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        pessoa.setNome("Maria");

        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(pessoa));
        PessoaDTO resultado = pessoaService.consultarPessoa(pessoaId);

        assertNotNull(resultado);
        assertEquals(pessoaId, resultado.getIdPessoa());
        assertEquals("Maria", resultado.getNome());
    }
}
