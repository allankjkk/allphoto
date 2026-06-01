package com.allan.allphoto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.allan.allphoto.DTO.FotoDTO;
import com.allan.allphoto.mapper.FotoMapper;
import com.allan.allphoto.model.Compra;
import com.allan.allphoto.model.Foto;
import com.allan.allphoto.repository.CompraRepository;
import com.allan.allphoto.repository.FotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FotoService {

    private final FotoRepository FotoRepo;
    private final FotoMapper FotoMap;
    private final CompraRepository compraRepo;

    public FotoDTO salvar(FotoDTO fotoDTO) {
        Foto foto = FotoMap.toEntity(fotoDTO);
        return FotoMap.toDTO(FotoRepo.save(foto));
    }

    public FotoDTO buscarPorId(Long id) {
        Optional<Foto> foto = FotoRepo.findById(id);
        if (foto.isEmpty()) {
            throw new RuntimeException("Foto não encontrada");
        } else {
            return FotoMap.toDTO(foto.get());
        }
    }

    public List<FotoDTO> listar() {
        return FotoRepo.findAll()
                .stream()
                .map(FotoMap::toDTO)
                .collect(Collectors.toList());
    }

    public FotoDTO atualizar(Long id, FotoDTO fotoDTO) {
        Optional<Foto> fotoExistente = FotoRepo.findById(id);
        if (fotoExistente.isEmpty()) {
            throw new RuntimeException("Foto não encontrada");
        } else {
            Foto foto = FotoMap.toEntity(fotoDTO);
            foto.setId(id);
            return FotoMap.toDTO(FotoRepo.save(foto));
        }
    }

    public void deletar(Long id) {
        if (!FotoRepo.existsById(id)) {
            throw new RuntimeException("Foto não encontrada");
        } else {
            FotoRepo.deleteById(id);
        }
    }

    public String download(Long id, Long clienteId) {
        Optional<Foto> foto = FotoRepo.findById(id);
        if (foto.isEmpty()) {
            throw new RuntimeException("Foto não encontrada");
        }

        Optional<Compra> compra = compraRepo.findByFotoIdAndClienteId(id, clienteId);
        if (compra.isEmpty()) {
            throw new RuntimeException("Compra não encontrada para esta foto e cliente");
        }

        if (!compra.get().getStatus().equals("PAGO")) {
            throw new RuntimeException("Pagamento não confirmado para esta compra");
        }

        return foto.get().getUrlOfc();
    }

    public List<FotoDTO> buscarPorFaixaDePreco(Double precoMin, Double precoMax) {
        return FotoRepo.faixaDePreco(precoMin, precoMax)
                .stream()
                .map(FotoMap::toDTO)
                .collect(Collectors.toList());
    }

    public Page<FotoDTO> listarPaginado(Pageable pageable) {
        return FotoRepo.findAll(pageable)
                .map(FotoMap::toDTO);
    }
}