package br.com.unifacisa.Fase2Projeto.controller;

import br.com.unifacisa.Fase2Projeto.DTO.QuartoRecordDTO;
import br.com.unifacisa.Fase2Projeto.DTO.ReservaRecordDTO;
import br.com.unifacisa.Fase2Projeto.entities.Quarto;
import br.com.unifacisa.Fase2Projeto.entities.Reserva;
import br.com.unifacisa.Fase2Projeto.service.ReservaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Reserva> saveReserva(@RequestBody ReservaRecordDTO reservaRecordDTO) {
        try {
            Reserva savedReserva = reservaService.save(reservaRecordDTO);
            return new ResponseEntity<>(savedReserva, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Integer id) {
        return reservaService.findById(id)
                .map(reserva -> new ResponseEntity<>(reserva, HttpStatus.OK))
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Integer id) {
        try {
            reservaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Integer id, @RequestBody ReservaRecordDTO reservaRecordDTO) {
        try {
            Reserva updatedReserva = reservaService.update(id, reservaRecordDTO);
            return new ResponseEntity<>(updatedReserva, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Tratamento de exceção para EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Tratamento de exceção para IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
