package br.com.attornatus.Pessoas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

import br.com.attornatus.Pessoas.controller.EnderecoController;
import br.com.attornatus.Pessoas.model.DTO.EnderecoDTO;
import br.com.attornatus.Pessoas.service.EnderecoService;

public class EnderecoControllerTests {

    @InjectMocks
    private EnderecoController enderecoController;

    @Mock
    private EnderecoService enderecoService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarEndereco() {
        // Preparar dados de entrada
        EnderecoDTO enderecoDTOEntrada = new EnderecoDTO();
        enderecoDTOEntrada.setCep("12345-678");

        // Preparar comportamento do serviço
        EnderecoDTO enderecoDTOSalvo = new EnderecoDTO();
        enderecoDTOSalvo.setCep("12345-678");
        when(enderecoService.salvar(enderecoDTOEntrada)).thenReturn(enderecoDTOSalvo);

        // Executar a ação do controlador
        ResponseEntity<EnderecoDTO> response = enderecoController.salvar(enderecoDTOEntrada);

        // Verificar resultados
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("12345-678", response.getBody().getCep());
    }

    @Test
    public void testListarTodosEnderecos() {
        // Preparar comportamento do serviço
        List<EnderecoDTO> enderecos = Arrays.asList(new EnderecoDTO(), new EnderecoDTO());
        when(enderecoService.listar()).thenReturn(enderecos);

        // Executar a ação do controlador
        ResponseEntity<List<EnderecoDTO>> response = enderecoController.listarTodosEnderecos();

        // Verificar resultados
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testListarEnderecosPorPessoa() {
        // Preparar dados de entrada
        Long pessoaId = 1L;

        // Preparar comportamento do serviço
        List<EnderecoDTO> enderecos = Arrays.asList(new EnderecoDTO(), new EnderecoDTO());
        when(enderecoService.listarPorPessoa(pessoaId)).thenReturn(enderecos);

        // Executar a ação do controlador
        ResponseEntity<List<EnderecoDTO>> response = enderecoController.listar(pessoaId);

        // Verificar resultados
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }
}
