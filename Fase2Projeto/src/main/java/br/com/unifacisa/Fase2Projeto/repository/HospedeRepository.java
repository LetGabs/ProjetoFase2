package br.com.unifacisa.Fase2Projeto.repository;

import br.com.unifacisa.Fase2Projeto.entities.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Integer> {

}
