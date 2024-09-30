package br.com.unifacisa.Fase2Projeto.controller;

import br.com.unifacisa.Fase2Projeto.DTO.QuartoRecordDTO;
import br.com.unifacisa.Fase2Projeto.DTO.ReservaRecordDTO;
import br.com.unifacisa.Fase2Projeto.entities.Quarto;
import br.com.unifacisa.Fase2Projeto.entities.Reserva;
import br.com.unifacisa.Fase2Projeto.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservaC")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaService.getAll();
    }

    @PostMapping
    public Reserva saveReserva(@RequestBody ReservaRecordDTO reservaRecordDTO) {
        return reservaService.save(reservaRecordDTO);
    }

    @GetMapping("/{id}")
    public Reserva getReservaById(@PathVariable Integer id) {
        return reservaService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteReserva(@PathVariable Integer id) {
        reservaService.delete(id);
    }
    @PutMapping("/{id}")
    public Reserva updateReserva(@PathVariable Integer id, @RequestBody ReservaRecordDTO reservaRecordDTO) {
        return reservaService.update(id, reservaRecordDTO);
    }
}
