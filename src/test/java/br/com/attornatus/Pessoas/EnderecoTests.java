package br.com.attornatus.Pessoas;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.attornatus.Pessoas.model.Endereco;
import br.com.attornatus.Pessoas.model.Pessoas;
import br.com.attornatus.Pessoas.model.DTO.EnderecoDTO;
import br.com.attornatus.Pessoas.repository.EnderecoRespository;
import br.com.attornatus.Pessoas.repository.PessoasRepository;
import br.com.attornatus.Pessoas.service.impl.EnderecoServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EnderecoServiceImplTests {

    @InjectMocks
    private EnderecoServiceImpl enderecoService;

    @Mock
    private EnderecoRespository enderecoRepository;

    @Mock
    private PessoasRepository pessoasRepository;

    @Test
    public void testSalvarEndereco() {
        EnderecoDTO enderecoDTO = new EnderecoDTO("Rua 123","2133212-23",321,"São Paulo",1L);

        Pessoas pessoa = new Pessoas();
        pessoa.setId(1L);

        Mockito.when(pessoasRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        Mockito.when(enderecoRepository.save(Mockito.any(Endereco.class))).thenReturn(new Endereco());

        Endereco enderecoSalvo = enderecoService.salvar(enderecoDTO);

        assertNotNull(enderecoSalvo);
    }
}
