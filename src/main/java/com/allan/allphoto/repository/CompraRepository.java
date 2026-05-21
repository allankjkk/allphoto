package com.allan.allphoto.repository;

import com.allan.allphoto.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    Optional<Compra> findByFotoIdAndClienteId(Long fotoId, Long clienteId);
}