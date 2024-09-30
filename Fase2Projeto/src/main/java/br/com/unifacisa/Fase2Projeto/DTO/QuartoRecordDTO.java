package br.com.unifacisa.Fase2Projeto.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

public class QuartoRecordDTO {

    private Integer id;

    // Tipo de quarto (ex: simples, duplo, suíte) é obrigatório
    @NotNull(message = "O tipo do quarto é obrigatório")
    private String tipoQuarto;

    // Status do quarto (ex: disponível, ocupado) é obrigatório
    @NotNull(message = "O status do quarto é obrigatório")
    private String status;

    // Capacidade mínima de um quarto deve ser de pelo menos 1 pessoa
    @NotNull(message = "A capacidade do quarto é obrigatória")
    @Min(value = 1, message = "A capacidade deve ser de pelo menos 1 pessoa")
    private Integer capacidade;

    // Preço do quarto deve ser maior que 0
    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    // O campo hotelId é obrigatório
    @NotNull(message = "O hotelId é obrigatório")
    private Integer hotelId;

    @NotNull(message = "A lista de reservas não pode ser nula")
    private List<Integer> idReservas;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public List<Integer> getIdReservas() {
        return idReservas;
    }

    public void setIdReservas(List<Integer> idReservas) {
        this.idReservas = idReservas;
    }
}
