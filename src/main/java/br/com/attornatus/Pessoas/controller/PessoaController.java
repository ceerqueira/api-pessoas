package br.com.attornatus.Pessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.attornatus.Pessoas.model.DTO.PessoaDTO;
import br.com.attornatus.Pessoas.service.PessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    public ResponseEntity<PessoaDTO> salvar (@RequestBody @Valid PessoaDTO pessoa){
        PessoaDTO resultado = service.salvar(pessoa);
        return ResponseEntity.ok().body(resultado);

    }

    @GetMapping
    public ResponseEntity<List< PessoaDTO>> listar (){

        List<PessoaDTO> listaDePessoas = service.listar();

        return ResponseEntity.ok().body(listaDePessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> consultarPessoa (@PathVariable ("id")Long id){

        PessoaDTO pessoa = service.consultarPessoa(id);

        return ResponseEntity.ok().body(pessoa);
    }

    @PutMapping
    public ResponseEntity<PessoaDTO> atualizar(@RequestBody PessoaDTO pessoa){
        PessoaDTO pessoaEditada = service.editar(pessoa);
        return ResponseEntity.ok().body(pessoaEditada);
    }
    
}
