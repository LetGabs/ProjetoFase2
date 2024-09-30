package br.com.unifacisa.Fase2Projeto.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

public class HospedeRecordDTO {

    private Integer id;

    // Nome é obrigatório
    @NotNull(message = "O nome é obrigatório")
    private String nome;

    // CPF deve conter exatamente 11 dígitos numéricos
    @NotNull(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos")
    private String cpf;

    // Data de nascimento deve ser obrigatoriamente no passado
    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    // Endereço é obrigatório
    @NotNull(message = "O endereço é obrigatório")
    private String endereco;

    // O número de celular deve ter exatamente 11 dígitos
    @NotNull(message = "O número de celular é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O número de celular deve ter 11 dígitos")
    private String contato;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
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
