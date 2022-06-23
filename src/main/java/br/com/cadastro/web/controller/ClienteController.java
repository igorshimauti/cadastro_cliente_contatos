package br.com.cadastro.web.controller;

import br.com.cadastro.web.model.Cliente;
import br.com.cadastro.web.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> incluir(@RequestBody Cliente cliente) {
        Cliente clienteIncluido = clienteService.salvar(cliente);
        URI location = URI.create("cliente/" + cliente.getId());
        return ResponseEntity.created(location).body(clienteIncluido);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> recuperarPorId(@PathVariable Long id) {
        Cliente clienteRecuperado = clienteService.recuperarPorId(id);
        return ResponseEntity.ok().body(clienteRecuperado);
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        Cliente clienteAtualizado = clienteService.salvar(cliente);
        return ResponseEntity.ok().body(clienteAtualizado);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
