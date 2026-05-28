package com.allan.allphoto.service;

import com.allan.allphoto.DTO.CompraDTO;
import com.allan.allphoto.mapper.CompraMapper;
import com.allan.allphoto.model.Cliente;
import com.allan.allphoto.model.Compra;
import com.allan.allphoto.model.Foto;
import com.allan.allphoto.repository.ClienteRepository;
import com.allan.allphoto.repository.CompraRepository;
import com.allan.allphoto.repository.FotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository CompraRepo;
    private final CompraMapper CompraMap;
    private final ClienteRepository clienteRepo;
    private final FotoRepository fotoRepo;

    public CompraDTO salvar(CompraDTO compraDTO) {
        Cliente cliente = clienteRepo.findById(compraDTO.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Foto foto = fotoRepo.findById(compraDTO.getFoto().getId())
                .orElseThrow(() -> new RuntimeException("Foto não encontrada"));

        Compra compra = CompraMap.toEntity(compraDTO);
        compra.setCliente(cliente);
        compra.setFoto(foto);

        return CompraMap.toDTO(CompraRepo.save(compra));
    }

    public CompraDTO buscarPorId(Long id) {
        Optional<Compra> compra = CompraRepo.findById(id);
        if (compra.isEmpty()) {
            throw new RuntimeException("Compra não encontrada");
        } else {
            return CompraMap.toDTO(compra.get());
        }
    }

    public List<CompraDTO> listar() {
        return CompraRepo.findAll()
                .stream()
                .map(CompraMap::toDTO)
                .collect(Collectors.toList());
    }

    public CompraDTO atualizar(Long id, CompraDTO compraDTO) {
        CompraRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

        Cliente cliente = clienteRepo.findById(compraDTO.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Foto foto = fotoRepo.findById(compraDTO.getFoto().getId())
                .orElseThrow(() -> new RuntimeException("Foto não encontrada"));

        Compra compra = CompraMap.toEntity(compraDTO);
        compra.setId(id);
        compra.setCliente(cliente);
        compra.setFoto(foto);

        return CompraMap.toDTO(CompraRepo.save(compra));
    }

    public void deletar(Long id) {
        if (!CompraRepo.existsById(id)) {
            throw new RuntimeException("Compra não encontrada");
        } else {
            CompraRepo.deleteById(id);
        }
    }
}