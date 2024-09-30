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
    @NotNull(message = "O hotelId é obrigatório")
    private Integer hospedeId;

    // Quarto da reserva é obrigatório
    @NotNull(message = "O quarto é obrigatório")
    private Integer quartoId;

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

    public Integer getHospedeId() {
        return hospedeId;
    }

    public void setHospedeId(Integer hospedeId) {
        this.hospedeId = hospedeId;
    }

    public Integer getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(Integer quartoId) {
        this.quartoId = quartoId;
    }
}
