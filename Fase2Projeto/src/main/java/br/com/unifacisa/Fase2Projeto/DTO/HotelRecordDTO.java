package br.com.unifacisa.Fase2Projeto.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class HotelRecordDTO {

    private Integer id;

    // Nome do hotel é obrigatório
    @NotNull(message = "O nome do hotel é obrigatório")
    private String nome;

    // Endereço do hotel é obrigatório
    @NotNull(message = "O endereço do hotel é obrigatório")
    private String endereco;
    @NotNull(message = "A lista de quartos não pode ser nula")
    private List<Integer> idQuartos;

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Integer> getIdQuartos() {
        return idQuartos;
    }

    public void setIdQuartos(List<Integer> idQuartos) {
        this.idQuartos = idQuartos;
    }
}

