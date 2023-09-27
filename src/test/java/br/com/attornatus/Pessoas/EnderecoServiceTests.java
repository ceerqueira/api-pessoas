package br.com.attornatus.Pessoas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
import br.com.attornatus.Pessoas.model.DTO.EnderecoDTO;
import br.com.attornatus.Pessoas.repository.EnderecoRespository;
import br.com.attornatus.Pessoas.repository.PessoaRepository;
import br.com.attornatus.Pessoas.service.impl.EnderecoServiceImpl;

public class EnderecoServiceTests {

    @InjectMocks
    private EnderecoServiceImpl enderecoService;

    @Mock
    private EnderecoRespository enderecoRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarEndereco() {
        // Preparar dados de entrada
        Long pessoaId = 1L;
        EnderecoDTO enderecoDTOEntrada = new EnderecoDTO();
        enderecoDTOEntrada.setIdPessoa(pessoaId);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);

        // Preparar comportamento do repositório
        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(pessoa));

        Endereco enderecoNovo = new Endereco();
        enderecoNovo.setId(1L);
        enderecoNovo.setCep("12345-678");
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoNovo);

        EnderecoDTO enderecoDTOSalvo = new EnderecoDTO();
        enderecoDTOSalvo.setIdPessoa(1L);
        enderecoDTOSalvo.setCep("12345-678");
        when(modelMapper.map(enderecoNovo, EnderecoDTO.class)).thenReturn(enderecoDTOSalvo);

        // Executar a ação do serviço
        EnderecoDTO resultado = enderecoService.salvar(enderecoDTOEntrada);

        // Verificar resultados
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdPessoa());
        assertEquals("12345-678", resultado.getCep());
    }

    @Test
    public void testListarEnderecos() {
        // Preparar comportamento do repositório
        List<Endereco> enderecos = Arrays.asList(new Endereco(), new Endereco());
        when(enderecoRepository.findAll()).thenReturn(enderecos);

        // Executar a ação do serviço
        List<EnderecoDTO> resultado = enderecoService.listar();

        // Verificar resultados
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    public void testListarEnderecosPorPessoa() {
        // Preparar dados de entrada
        Long pessoaId = 1L;

        // Preparar comportamento do repositório
        List<Endereco> enderecos = Arrays.asList(new Endereco(), new Endereco());
        when(enderecoRepository.findByPessoaId(pessoaId)).thenReturn(Optional.of(enderecos));

        // Executar a ação do serviço
        List<EnderecoDTO> resultado = enderecoService.listarPorPessoa(pessoaId);

        // Verificar resultados
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }
}
