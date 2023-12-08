
package com.sistema.nutricao_qualidade.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiretoriaRepository  extends JpaRepository<Diretoria, Integer> {
    
}
