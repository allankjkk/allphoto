package com.allan.allphoto.service;

import com.allan.allphoto.model.Compra;
import com.allan.allphoto.model.Foto;
import com.allan.allphoto.repository.CompraRepository;
import com.allan.allphoto.repository.FotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FotoService {

    private final FotoRepository FotoRepo;
    private final CompraRepository compraRepo;

    public Foto salvar(Foto foto) {
        try {
            return FotoRepo.save(foto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar foto: ");
        }
    }

    public Foto buscarPorId(Long id) {
        Optional<Foto> foto = FotoRepo.findById(id);
        if (foto.isEmpty()) {
            throw new RuntimeException("Foto não encontrada");
        } else {
            return foto.get();
        }
    }

    public List<Foto> listar() {
        try {
            return FotoRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar fotos: ");
        }
    }

    public Foto atualizar(Long id, Foto foto) {
        Optional<Foto> fotoExistente = FotoRepo.findById(id);
        if (fotoExistente.isEmpty()) {
            throw new RuntimeException("Foto não encontrada");
        } else {
            foto.setId(id);
            return FotoRepo.save(foto);
        }
    }

    public void deletar(Long id) {
        if (!FotoRepo.existsById(id)) {
            throw new RuntimeException("Foto não encontrada");
        } else {
            FotoRepo.deleteById(id);
        }
    }

    public String download(Long id, Long ClienteId){
        Foto foto = buscarPorId(id);

        Optional<Compra> compra = compraRepo.findByFotoIdAndClienteId(id, ClienteId);
        if (compra.isEmpty()) {
            throw new RuntimeException("Compra não encontrada para esta foto e cliente");
        }

        if (!compra.get().getStatus().equals("PAGO")) {
            throw new RuntimeException("Pagamento não confirmado para esta compra");
        } return foto.getUrlOfc();
    }
}