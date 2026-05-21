package com.allan.allphoto.service;

import com.allan.allphoto.model.*;
import com.allan.allphoto.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository CompraRepo;
    private final ClienteRepository clienteRepo;
    private final FotoRepository fotoRepo;

    public Compra salvar(Compra compra) {
        Long clienteId = compra.getCliente().getId();
        Long fotoId = compra.getFoto().getId();

        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Foto foto = fotoRepo.findById(fotoId)
                .orElseThrow(() -> new RuntimeException("Foto não encontrada"));

        compra.setCliente(cliente);
        compra.setFoto(foto);

        return CompraRepo.save(compra);
    }

    public Compra buscarPorId(Long id) {
        Optional<Compra> compra = CompraRepo.findById(id);
        if (compra.isEmpty()) {
            throw new RuntimeException("Compra não encontrada");
        } else {
            return compra.get();
        }
    }

    public List<Compra> listar() {
        try {
            return CompraRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar compras: ");
        }
    }

    public Compra atualizar(Long id, Compra compra) {
        Optional<Compra> compraExistente = CompraRepo.findById(id);
        if (compraExistente.isEmpty()) {
            throw new RuntimeException("Compra não encontrada");
        } else {
            compra.setId(id);
            return CompraRepo.save(compra);
        }
    }

    public void deletar(Long id) {
        if (!CompraRepo.existsById(id)) {
            throw new RuntimeException("Compra não encontrada");
        } else {
            CompraRepo.deleteById(id);
        }
    }
}