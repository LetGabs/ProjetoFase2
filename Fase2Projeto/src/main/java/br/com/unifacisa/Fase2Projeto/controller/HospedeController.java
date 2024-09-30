package br.com.unifacisa.Fase2Projeto.controller;

import br.com.unifacisa.Fase2Projeto.DTO.HospedeRecordDTO;
import br.com.unifacisa.Fase2Projeto.DTO.HotelRecordDTO;
import br.com.unifacisa.Fase2Projeto.entities.Hospede;
import br.com.unifacisa.Fase2Projeto.entities.Hotel;
import br.com.unifacisa.Fase2Projeto.service.HospedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedeC")
public class HospedeController {

    private final HospedeService hospedeService;

    @Autowired // injeção de dependência
    public HospedeController(HospedeService hospedeService) {
        super();
        this.hospedeService = hospedeService;
    }
    @GetMapping // obter todos os hospedes
    public List<Hospede> getAllHospede(){
        return hospedeService.getAll();
    }
    @PostMapping// salvar um novo hospede
    public Hospede saveHospede(@RequestBody HospedeRecordDTO hospedeRecordDTO){
        return hospedeService.save(hospedeRecordDTO);
    }
    @GetMapping("/{id}") // buscar por id
    public Hospede getHospedeById(@PathVariable Integer id) {
        return hospedeService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}") // deletar por id
    public void deleteHospede(@PathVariable Integer id) {
        hospedeService.delete(id);
    }
    @PutMapping("/{id}")
    public Hospede updateHospede(@PathVariable Integer id, @RequestBody HospedeRecordDTO hospedeRecordDTO) {
        return hospedeService.update(id, hospedeRecordDTO);
    }
}




