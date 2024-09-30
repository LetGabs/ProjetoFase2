package br.com.unifacisa.Fase2Projeto.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;


public class ReservaRecordDTO {

    private Integer id;

    // Data de check-in deve ser no futuro
    @NotNull(message = "A data de check-in é obrigatória")
    @Future(message = "A data de check-in deve ser no futuro")
    private LocalDate dataCheckin;

    // Data de check-out deve ser obrigatória
    @NotNull(message = "A data de check-out é obrigatória")
    private LocalDate dataCheckout;

    // Hóspede da reserva é obrigatório
    @NotNull(message = "O hóspede é obrigatório")
    private HospedeRecordDTO hospede;

    // Quarto da reserva é obrigatório
    @NotNull(message = "O quarto é obrigatório")
    private QuartoRecordDTO quarto;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(LocalDate dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public LocalDate getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(LocalDate dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public @NotNull(message = "O hóspede é obrigatório") HospedeRecordDTO getHospede() {
        return hospede;
    }

    public void setHospede(HospedeRecordDTO hospede) {
        this.hospede = hospede;
    }

    public @NotNull(message = "O quarto é obrigatório") QuartoRecordDTO getQuarto() {
        return quarto;
    }

    public void setQuarto(QuartoRecordDTO quarto) {
        this.quarto = quarto;
    }
}
