package br.com.attornatus.Pessoas;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import br.com.attornatus.Pessoas.model.Pessoa;
import br.com.attornatus.Pessoas.model.DTO.PessoaDTO;
import br.com.attornatus.Pessoas.repository.EnderecoRespository;
import br.com.attornatus.Pessoas.repository.PessoaRepository;
import br.com.attornatus.Pessoas.service.impl.PessoaServiceImpl;

public class PessoaServiceTests {

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private EnderecoRespository enderecoRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
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

}
