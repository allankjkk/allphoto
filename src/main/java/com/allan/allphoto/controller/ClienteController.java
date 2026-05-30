package com.allan.allphoto.controller;

import com.allan.allphoto.DTO.ClienteDTO;
import com.allan.allphoto.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteService.listar();
    }

    @PostMapping
    public ClienteDTO salvar(@Valid @RequestBody ClienteDTO clienteDTO) {
        return clienteService.salvar(clienteDTO);
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@Valid @PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        return clienteService.atualizar(id, clienteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}