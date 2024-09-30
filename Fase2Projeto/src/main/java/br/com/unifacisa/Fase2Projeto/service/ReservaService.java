package br.com.unifacisa.Fase2Projeto.service;

import br.com.unifacisa.Fase2Projeto.DTO.ReservaRecordDTO;
import br.com.unifacisa.Fase2Projeto.entities.Hospede;
import br.com.unifacisa.Fase2Projeto.entities.Quarto;
import br.com.unifacisa.Fase2Projeto.entities.Reserva;
import br.com.unifacisa.Fase2Projeto.repository.ReservaRepository;
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
}


