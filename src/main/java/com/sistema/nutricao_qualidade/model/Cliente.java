package com.sistema.nutricao_qualidade.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity// p/ mostrar que é uma entidade.
@Data // p/ não precisar criar GET e SET.
@NoArgsConstructor // p/ não precisar criar os métodos Construtores. Construtor vazio
@AllArgsConstructor // p/ não precisar criar métodos com todos os argumentos.
@Table(name = "Cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// faz o auto incremento
    private Integer id;
    
    private String nome_Fantasia;
    private String razao_Social;
    private String cnpj;
    private String categoria;
    private String nome_responsavel_legal;
    private String sobrenome_responsavel_legal;
    private String email_responsavel_legal;
    private String telefone_responsavel_legal;
    private String celular_responsavel_legal;
    private String nome_gerente;
    private String sobrenome_gerente;
    private String email_gerente;
    private String telefone_gerente;
    private String celular_gerente;
    private String nome_subgerente;
    private String sobrenome_subgerente;
    private String email_subgerente;
    private String telefone_subgerente;
    private String celular_subgerente;
    
    @ManyToOne
    private Endereco endereco; 

}
       
