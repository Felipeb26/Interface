package com.batsworks.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosEntity {

    private Long id;
    private String idUsuario;
    private String nome;
    private String descricao;
    private String valor;

    public static ProdutosEntity rowMapper(ResultSet rs) {
        try {
            return ProdutosEntity.builder()
                    .id(rs.getLong("id"))
                    .idUsuario(rs.getString("idUsuario"))
                    .nome(rs.getString("nome"))
                    .descricao(rs.getString("descricao"))
                    .valor(rs.getBigDecimal("valor").toString())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
