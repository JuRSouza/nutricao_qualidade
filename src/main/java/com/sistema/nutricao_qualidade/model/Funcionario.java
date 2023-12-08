package com.sistema.nutricao_qualidade.model;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;
    private String sobrenome;

   // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_Nascimento;
    private String email;
    private String telefone;
    private String celular;
    private String rg;
    private String cpf;
    private String crn;

    @Transient
    private Integer usuarioId;

    @Transient
    private Integer enderecoId;

    @ManyToOne
    @JoinColumn(name = "id_Endereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

}
