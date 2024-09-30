package br.com.unifacisa.Fase2Projeto.service;

import br.com.unifacisa.Fase2Projeto.DTO.ReservaRecordDTO;
import br.com.unifacisa.Fase2Projeto.entities.Hospede;
import br.com.unifacisa.Fase2Projeto.entities.Quarto;
import br.com.unifacisa.Fase2Projeto.entities.Reserva;
import br.com.unifacisa.Fase2Projeto.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final HospedeService hospedeService;
    private final QuartoService quartoService;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, HospedeService hospedeService, QuartoService quartoService) {
        this.reservaRepository = reservaRepository;
        this.hospedeService = hospedeService;
        this.quartoService = quartoService;
    }

    public Reserva save(ReservaRecordDTO reservaRecordDTO) {
        Reserva reserva = new Reserva();
        reserva.setDataCheckin(reservaRecordDTO.getDataCheckin());
        reserva.setDataCheckout(reservaRecordDTO.getDataCheckout());

        // Buscando o hóspede pelo ID
        Hospede hospede = hospedeService.findById(reservaRecordDTO.getHospedeId())
                .orElseThrow(() -> new EntityNotFoundException("Hóspede não encontrado com o ID: "
                        + reservaRecordDTO.getHospedeId()));
        reserva.setHospede(hospede);

        // Buscando o quarto pelo ID
        Quarto quarto = quartoService.findById(reservaRecordDTO.getQuartoId())
                .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado com o ID: "
                        + reservaRecordDTO.getQuartoId()));
        reserva.setQuarto(quarto);

        return reservaRepository.save(reserva);
    }

    public List<Reserva> getAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Integer id) {
        return reservaRepository.findById(id);
    }

    public void delete(Integer id) {
        if (!reservaRepository.existsById(id)) {
            throw new EntityNotFoundException("Reserva não encontrada com o ID: " + id);
        }
        reservaRepository.deleteById(id);
    }

    public Reserva update(Integer id, ReservaRecordDTO reservaRecordDTO) {
        // Procura a reserva existente pelo ID
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o ID: " + id));

        // Atualiza os campos da reserva com os dados do DTO
        reserva.setDataCheckin(reservaRecordDTO.getDataCheckin());
        reserva.setDataCheckout(reservaRecordDTO.getDataCheckout());

        // Verificando e atualizando o hóspede
        if (reservaRecordDTO.getHospedeId() != null) {
            Hospede hospede = hospedeService.findById(reservaRecordDTO.getHospedeId())
                    .orElseThrow(() -> new EntityNotFoundException("Hóspede não encontrado com o ID: "
                            + reservaRecordDTO.getHospedeId()));
            reserva.setHospede(hospede);
        }

        // Verificando e atualizando o quarto
        if (reservaRecordDTO.getQuartoId() != null) {
            Quarto quarto = quartoService.findById(reservaRecordDTO.getQuartoId())
                    .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado com o ID: "
                            + reservaRecordDTO.getQuartoId()));
            reserva.setQuarto(quarto);
        }

        // Salva e retorna a reserva atualizada
        return reservaRepository.save(reserva);
    }
}
