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
        Optional<Hospede> hospedeOpt = hospedeService.findById(reservaRecordDTO.getHospede().getId());
        if (hospedeOpt.isEmpty()) {
            throw new IllegalArgumentException("Hóspede não encontrado");
        }
        reserva.setHospede(hospedeOpt.get());

        // Buscando o quarto pelo ID
        Optional<Quarto> quartoOpt = quartoService.findById(reservaRecordDTO.getQuarto().getId());
        if (quartoOpt.isEmpty()) {
            throw new IllegalArgumentException("Quarto não encontrado");
        }
        reserva.setQuarto(quartoOpt.get());

        return reservaRepository.save(reserva);
    }

    public List<Reserva> getAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Integer id) {
        return reservaRepository.findById(id);
    }

    public void delete(Integer id) {
        reservaRepository.deleteById(id);
    }

    public Reserva update(Integer id, ReservaRecordDTO reservaRecordDTO) {
        // Procura a reserva existente pelo ID
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);

        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();

            // Atualiza os campos da reserva com os dados do DTO
            reserva.setDataCheckin(reservaRecordDTO.getDataCheckin());
            reserva.setDataCheckout(reservaRecordDTO.getDataCheckout());

            // Verificando e atualizando o hóspede
            if (reservaRecordDTO.getHospede() != null && reservaRecordDTO.getHospede().getId() != null) {
                Optional<Hospede> hospedeOpt = hospedeService.findById(reservaRecordDTO.getHospede().getId());
                if (hospedeOpt.isEmpty()) {
                    throw new IllegalArgumentException("Hóspede não encontrado");
                }
                reserva.setHospede(hospedeOpt.get());
            }

            // Verificando e atualizando o quarto
            if (reservaRecordDTO.getQuarto() != null && reservaRecordDTO.getQuarto().getId() != null) {
                Optional<Quarto> quartoOpt = quartoService.findById(reservaRecordDTO.getQuarto().getId());
                if (quartoOpt.isEmpty()) {
                    throw new IllegalArgumentException("Quarto não encontrado");
                }
                reserva.setQuarto(quartoOpt.get());
            }

            // Salva e retorna a reserva atualizada
            return reservaRepository.save(reserva);
        } else {
            throw new EntityNotFoundException("Reserva não encontrada com o ID: " + id);
        }
    }

}


