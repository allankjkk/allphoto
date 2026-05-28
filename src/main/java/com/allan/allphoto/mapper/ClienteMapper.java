package com.allan.allphoto.mapper;

import com.allan.allphoto.DTO.ClienteDTO;
import com.allan.allphoto.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .build();
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .id(clienteDTO.getId())
                .nome(clienteDTO.getNome())
                .email(clienteDTO.getEmail())
                .build();
    }
}