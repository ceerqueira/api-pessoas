package br.com.attornatus.Pessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.Pessoas.model.Endereco;
import br.com.attornatus.Pessoas.model.DTO.EnderecoDTO;
import br.com.attornatus.Pessoas.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;
    
    @PostMapping
    public ResponseEntity<String> salvar (@RequestBody EnderecoDTO endereco){
        service.salvar(endereco);
        return ResponseEntity.ok().body("Endereço cadastrado com sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<List< Endereco>> listar (@PathVariable ("id")Long id){

        List<Endereco> listaDePessoas = service.listarPorPessoa(id);

        return ResponseEntity.ok().body(listaDePessoas);
    }
}
