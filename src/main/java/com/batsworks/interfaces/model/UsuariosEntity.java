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
public class UsuariosEntity {

    private Long id;
    private String nome;
    private Long idade;
    private String email;
    private String endereco;
    private Long senha;
    private Boolean adm;

    public static UsuariosEntity rowMapper(ResultSet rs) {
        try {
            return UsuariosEntity.builder().id(rs.getLong("id")).nome(rs.getString("nome"))
                    .idade(rs.getLong("idade")).email(rs.getString("email")).adm(rs.getBoolean("adm"))
                    .endereco(rs.getString("endereco")).senha(rs.getLong("senha")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
