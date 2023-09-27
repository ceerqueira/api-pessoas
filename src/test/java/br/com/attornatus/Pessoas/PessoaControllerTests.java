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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.attornatus.Pessoas.controller.PessoaController;
import br.com.attornatus.Pessoas.model.DTO.PessoaDTO;
import br.com.attornatus.Pessoas.service.PessoaService;

public class PessoaControllerTests {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaService;

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

        // Preparar comportamento do serviço
        PessoaDTO pessoaDTOSalva = new PessoaDTO();
        pessoaDTOSalva.setNome("João");
        pessoaDTOSalva.setDataNascimento(new Date(1992));
        when(pessoaService.salvar(pessoaDTOEntrada)).thenReturn(pessoaDTOSalva);

        // Executar a ação do controlador
        ResponseEntity<PessoaDTO> response = pessoaController.salvar(pessoaDTOEntrada);

        // Verificar resultados
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("João", response.getBody().getNome());
    }

    @Test
    public void testListarPessoas() {
        // Preparar comportamento do serviço
        List<PessoaDTO> pessoas = Arrays.asList(new PessoaDTO(), new PessoaDTO());
        when(pessoaService.listar()).thenReturn(pessoas);

        // Executar a ação do controlador
        ResponseEntity<List<PessoaDTO>> response = pessoaController.listar();

        // Verificar resultados
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testConsultarPessoa() {
        // Preparar dados de entrada
        Long pessoaId = 1L;

        // Preparar comportamento do serviço
        PessoaDTO pessoa = new PessoaDTO();
        pessoa.setIdPessoa(1L);
        pessoa.setNome("Maria");
        when(pessoaService.consultarPessoa(pessoaId)).thenReturn(pessoa);

        // Executar a ação do controlador
        ResponseEntity<PessoaDTO> response = pessoaController.consultarPessoa(pessoaId);

        // Verificar resultados
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getIdPessoa());
        assertEquals("Maria", response.getBody().getNome());
    }

    @Test
    public void testAtualizar() {
        // Preparar dados de entrada
        PessoaDTO pessoaDTOEntrada = new PessoaDTO();
        pessoaDTOEntrada.setIdPessoa(1L);
        pessoaDTOEntrada.setNome("Novo Nome");

        // Preparar comportamento do serviço
        PessoaDTO pessoaDTOAtualizada = new PessoaDTO();
        pessoaDTOAtualizada.setIdPessoa(1L);
        pessoaDTOAtualizada.setNome("Novo Nome");
        when(pessoaService.editar(pessoaDTOEntrada)).thenReturn(pessoaDTOAtualizada);

        // Executar a ação do controlador
        ResponseEntity<PessoaDTO> response = pessoaController.atualizar(pessoaDTOEntrada);

        // Verificar resultados
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getIdPessoa());
        assertEquals("Novo Nome", response.getBody().getNome());
    }
}
