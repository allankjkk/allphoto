package com.allan.allphoto.DTO;

import com.allan.allphoto.exception.StatusValido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "O cliente é obrigatório")
    private ClienteDTO cliente;

    @NotNull(message = "A foto é obrigatória")
    private FotoDTO foto;

    @NotNull(message = "A data da compra é obrigatória")
    private LocalDateTime dataCompra;

    @NotBlank(message = "O status da compra é obrigatório")
    @StatusValido
    private String status;
}