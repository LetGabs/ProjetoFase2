package br.com.unifacisa.Fase2Projeto.repository;

import br.com.unifacisa.Fase2Projeto.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    }
