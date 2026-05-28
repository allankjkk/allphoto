package com.allan.allphoto.mapper;

import com.allan.allphoto.DTO.CompraDTO;
import com.allan.allphoto.model.Compra;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompraMapper {

    private final ClienteMapper clienteMapper;
    private final FotoMapper fotoMapper;

    public CompraDTO toDTO(Compra compra) {
        return CompraDTO.builder()
                .id(compra.getId())
                .cliente(clienteMapper.toDTO(compra.getCliente()))
                .foto(fotoMapper.toDTO(compra.getFoto()))
                .dataCompra(compra.getDataCompra())
                .status(compra.getStatus())
                .build();
    }

    public Compra toEntity(CompraDTO compraDTO) {
        return Compra.builder()
                .id(compraDTO.getId())
                .dataCompra(compraDTO.getDataCompra())
                .status(compraDTO.getStatus())
                .build();
    }
}