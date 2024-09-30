package br.com.unifacisa.Fase2Projeto.repository;


import br.com.unifacisa.Fase2Projeto.entities.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface QuartoRepository extends JpaRepository<Quarto, Integer> {

    }

