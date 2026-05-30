package com.allan.allphoto.controller;

import com.allan.allphoto.DTO.CompraDTO;
import com.allan.allphoto.service.CompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @GetMapping
    public List<CompraDTO> listar() {
        return compraService.listar();
    }

    @PostMapping
    public CompraDTO salvar(@Valid @RequestBody CompraDTO compraDTO) {
        return compraService.salvar(compraDTO);
    }

    @GetMapping("/{id}")
    public CompraDTO buscarPorId(@PathVariable Long id) {
        return compraService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public CompraDTO atualizar(@Valid @PathVariable Long id, @RequestBody CompraDTO compraDTO) {
        return compraService.atualizar(id, compraDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        compraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}