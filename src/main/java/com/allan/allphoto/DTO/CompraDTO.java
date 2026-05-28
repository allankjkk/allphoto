package com.allan.allphoto.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraDTO {

    private Long id;
    private ClienteDTO cliente;
    private FotoDTO foto;
    private LocalDateTime dataCompra;
    private String status;
}