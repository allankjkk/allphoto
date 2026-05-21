package com.allan.allphoto.controller;

import com.allan.allphoto.model.Foto;
import com.allan.allphoto.service.FotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fotos")
@RequiredArgsConstructor
public class FotoController {

    private final FotoService fotoService;

    @GetMapping
    public List<Foto> listar() {
        return fotoService.listar();
    }

    @PostMapping
    public Foto salvar(@RequestBody Foto foto) {
        return fotoService.salvar(foto);
    }

    @GetMapping("/{id}")
    public Foto buscarPorId(@PathVariable Long id) {
        return fotoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Foto atualizar(@PathVariable Long id, @RequestBody Foto foto) {
        return fotoService.atualizar(id, foto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        fotoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/download")
    public String download(@PathVariable Long id, @RequestParam Long clienteId) {
        return fotoService.download(id, clienteId);
    }
}