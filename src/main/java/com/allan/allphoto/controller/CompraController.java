package com.allan.allphoto.controller;

import com.allan.allphoto.model.Compra;
import com.allan.allphoto.service.CompraService;
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
    public List<Compra> listar() {
        return compraService.listar();
    }

    @PostMapping
    public Compra salvar(@RequestBody Compra compra) {
        return compraService.salvar(compra);
    }

    @GetMapping("/{id}")
    public Compra buscarPorId(@PathVariable Long id) {
        return compraService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Compra atualizar(@PathVariable Long id, @RequestBody Compra compra) {
        return compraService.atualizar(id, compra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        compraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}