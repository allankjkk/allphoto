package com.allan.allphoto.controller;

import com.allan.allphoto.DTO.FotoDTO;
import com.allan.allphoto.service.FotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/fotos")
@RequiredArgsConstructor
public class FotoController {

    private final FotoService fotoService;

    @GetMapping
    public List<FotoDTO> listar() {
        return fotoService.listar();
    }

    @PostMapping
    public FotoDTO salvar(@Valid @RequestBody FotoDTO fotoDTO) {
        return fotoService.salvar(fotoDTO);
    }

    @GetMapping("/{id}")
    public FotoDTO buscarPorId(@PathVariable Long id) {
        return fotoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public FotoDTO atualizar(@Valid @PathVariable Long id, @RequestBody FotoDTO fotoDTO) {
        return fotoService.atualizar(id, fotoDTO);
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

    @GetMapping("/faixa-de-preco")
    public List<FotoDTO> buscarPorFaixaDePreco(@RequestParam Double precoMin,
                                               @RequestParam Double precoMax) {
        return fotoService.buscarPorFaixaDePreco(precoMin, precoMax);
    }

    @GetMapping("/paginado")
    public Page<FotoDTO> listarPaginado(Pageable pageable) {
        return fotoService.listarPaginado(pageable);
    }
}