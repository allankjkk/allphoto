package com.allan.allphoto.mapper;

import com.allan.allphoto.DTO.FotoDTO;
import com.allan.allphoto.model.Foto;
import org.springframework.stereotype.Component;

@Component
public class FotoMapper {

    public FotoDTO toDTO(Foto foto) {
        return FotoDTO.builder()
                .id(foto.getId())
                .titulo(foto.getTitulo())
                .descricao(foto.getDescricao())
                .urlMarca(foto.getUrlMarca())
                .preco(foto.getPreco())
                .build();
    }

    public Foto toEntity(FotoDTO fotoDTO) {
        return Foto.builder()
                .id(fotoDTO.getId())
                .titulo(fotoDTO.getTitulo())
                .descricao(fotoDTO.getDescricao())
                .urlMarca(fotoDTO.getUrlMarca())
                .preco(fotoDTO.getPreco())
                .build();
    }
}