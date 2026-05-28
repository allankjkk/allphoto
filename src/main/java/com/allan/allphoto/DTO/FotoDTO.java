package com.allan.allphoto.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FotoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String urlMarca;
    private Double preco;
}