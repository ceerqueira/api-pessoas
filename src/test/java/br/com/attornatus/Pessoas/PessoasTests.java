package br.com.attornatus.Pessoas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.attornatus.Pessoas.model.Pessoas;
import br.com.attornatus.Pessoas.model.DTO.PessoasDTO;
import br.com.attornatus.Pessoas.service.PessoasService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Date;

@SpringBootTest
class PessoasTests {

    @Autowired
    private PessoasService pessoasService;

    @Test
    public void testEditarPessoaComSucesso() {
        PessoasDTO pessoaDTO = new PessoasDTO(1L, "Novo Nome", Date.valueOf("1990-01-01"), null);

        Pessoas pessoa = new Pessoas();
        pessoa.setNome("Fulano 1");
        pessoa.setDataNascimento(Date.valueOf("1883-12-12"));

        pessoasService.salvar(pessoa);

        Pessoas pessoaEditada = pessoasService.editar(pessoaDTO);

        assertEquals("Novo Nome", pessoaEditada.getNome());
        assertEquals(Date.valueOf("1990-01-01"), pessoaEditada.getDataNascimento());
    }

}
