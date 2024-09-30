package br.com.unifacisa.Fase2Projeto.service;


import br.com.unifacisa.Fase2Projeto.DTO.QuartoRecordDTO;
import br.com.unifacisa.Fase2Projeto.entities.Quarto;
import br.com.unifacisa.Fase2Projeto.repository.QuartoRepository;
import br.com.unifacisa.Fase2Projeto.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;
    private final ReservaRepository reservaRepository;
    @Autowired
    public QuartoService(QuartoRepository quartoRepository, ReservaRepository reservaRepository) {
        this.quartoRepository = quartoRepository;
        this.reservaRepository = reservaRepository;
    }

    public Quarto save(QuartoRecordDTO quartoRecordDTO) {
        Quarto quarto = new Quarto();
        quarto.setTipoQuarto(quartoRecordDTO.getTipoQuarto());
        quarto.setStatus(quartoRecordDTO.getStatus());
        quarto.setCapacidade(quartoRecordDTO.getCapacidade());
        quarto.setPreco(quartoRecordDTO.getPreco());
        if (quartoRecordDTO.getIdReservas() != null && !quartoRecordDTO.getIdReservas().isEmpty()) {
            quarto.setReservas(reservaRepository.findAllById(quartoRecordDTO.getIdReservas()).stream().collect(Collectors.toList()));
        } else {
            quarto.setReservas(List.of()); // Define como uma lista vazia se não houver reservas
        }

        return quartoRepository.save(quarto);
    }


    public List<Quarto> getAll() {
        return quartoRepository.findAll();
    }

    public Optional<Quarto> findById(Integer id) {
        return quartoRepository.findById(id);
    }

    public void delete(Integer id) {
        quartoRepository.deleteById(id);
    }
}
