
package com.sistema.nutricao_qualidade.model;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Relatorio")
public class Relatorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
    private Date data;
    private String hora_Entrada;
    private String hora_Saida;
    private String texto_Relatorio;
   
    @Transient
    private Integer clienteId;

    @Transient
    private Integer funcionarioId;
    
    @ManyToOne
    @JoinColumn (name = "id_Cliente")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn (name = "id_Funcionário")
    private Funcionario funcionario;
    
}
