package br.com.unifacisa.Fase2Projeto.controller;

import br.com.unifacisa.Fase2Projeto.DTO.HotelRecordDTO;
import br.com.unifacisa.Fase2Projeto.entities.Hotel;
import br.com.unifacisa.Fase2Projeto.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelC")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAll();
    }

    @PostMapping
    public Hotel saveHotel(@RequestBody HotelRecordDTO hotelRecordDTO) {
        return hotelService.save(hotelRecordDTO);
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Integer id) {
        return hotelService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable Integer id) {
        hotelService.delete(id);
    }
}
