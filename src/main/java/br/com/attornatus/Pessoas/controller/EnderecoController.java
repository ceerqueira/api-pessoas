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
import br.com.attornatus.Pessoas.model.DTO.EnderecoDTO;
import br.com.attornatus.Pessoas.service.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;
    

    @PostMapping
    public ResponseEntity<EnderecoDTO> salvar (@RequestBody @Valid EnderecoDTO endereco){
        EnderecoDTO enderecoDTO = service.salvar(endereco);
        return ResponseEntity.ok().body(enderecoDTO);
    }

    @GetMapping
    public ResponseEntity<List< EnderecoDTO>> listarTodosEnderecos (){
        List<EnderecoDTO> listaDePessoas = service.listar();
        return ResponseEntity.ok().body(listaDePessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List< EnderecoDTO>> listar (@PathVariable ("id")Long id){

        List<EnderecoDTO> listaDePessoas = service.listarPorPessoa(id);

        return ResponseEntity.ok().body(listaDePessoas);
    }
}
