
package com.sistema.nutricao_qualidade.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private Date data_Nascimento;
    private String email;
    private String telefone;
    private String celular;
    private String rg;
    private String cpf;
    private String crn;
    
     @ManyToOne
    private Endereco endereco; 
    
     @ManyToOne
    private Usuario usuario; 
   
    
    
}
