package br.com.unifacisa.Fase2Projeto.controller;

import br.com.unifacisa.Fase2Projeto.DTO.QuartoRecordDTO;
import br.com.unifacisa.Fase2Projeto.entities.Quarto;
import br.com.unifacisa.Fase2Projeto.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartoC")
public class QuartoController {

    private final QuartoService quartoService;

    @Autowired
    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @GetMapping
    public List<Quarto> getAllQuartos() {
        return quartoService.getAll();
    }

    @PostMapping
    public ResponseEntity<Quarto> saveQuarto(@RequestBody QuartoRecordDTO quartoRecordDTO) {
        try {
            Quarto quarto = quartoService.save(quartoRecordDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(quarto); // 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 400 Bad Request
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quarto> getQuartoById(@PathVariable Integer id) {
        return quartoService.findById(id)
                .map(quarto -> ResponseEntity.ok(quarto))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuarto(@PathVariable Integer id) {
        try {
            quartoService.delete(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

}
