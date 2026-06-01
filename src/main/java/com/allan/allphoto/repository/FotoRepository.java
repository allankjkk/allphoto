package com.allan.allphoto.repository;

import com.allan.allphoto.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FotoRepository extends JpaRepository<Foto, Long> {

    @Query("SELECT f FROM Foto f WHERE f.preco BETWEEN :precoMin AND :precoMax")
    List<Foto> faixaDePreco(@Param("precoMin") Double precoMin,
                                     @Param("precoMax") Double precoMax);

}