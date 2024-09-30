package br.com.unifacisa.Fase2Projeto.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "tb_reserva")
    public class Reserva {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false)
        private LocalDate dataCheckin;

        @Column(nullable = false)
        private LocalDate dataCheckout;

        @ManyToOne
        @JoinColumn(name = "hospede_id", nullable = false)
        private Hospede hospede;

        @ManyToOne
        @JoinColumn(name = "quarto_id", nullable = false)
        private Quarto quarto;
}
