package br.com.attornatus.Pessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.Pessoas.model.Pessoas;
import br.com.attornatus.Pessoas.model.DTO.PessoasDTO;
import br.com.attornatus.Pessoas.service.PessoasService;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {

    @Autowired
    private PessoasService service;
    
    @PostMapping
    public ResponseEntity<Pessoas> salvar (@RequestBody Pessoas pessoas){
        Pessoas resultado = service.salvar(pessoas);
        return ResponseEntity.ok().body(resultado);

    }

    @GetMapping
    public ResponseEntity<List< Pessoas>> listar (){

        List<Pessoas> listaDePessoas = service.listar();

        return ResponseEntity.ok().body(listaDePessoas);
    }

    @PutMapping
    public ResponseEntity<Pessoas> atualizar(@RequestBody PessoasDTO pessoa){
        Pessoas pessoaEditada = service.editar(pessoa);
        return ResponseEntity.ok().body(pessoaEditada);
    }
    
}
