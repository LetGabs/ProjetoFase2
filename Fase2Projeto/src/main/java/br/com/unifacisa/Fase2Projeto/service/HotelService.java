package br.com.unifacisa.Fase2Projeto.service;

import br.com.unifacisa.Fase2Projeto.DTO.HotelRecordDTO;
import br.com.unifacisa.Fase2Projeto.entities.Hotel;
import br.com.unifacisa.Fase2Projeto.repository.HotelRepository;
import br.com.unifacisa.Fase2Projeto.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final QuartoRepository quartoRepository;
    @Autowired
    public HotelService(HotelRepository hotelRepository, QuartoRepository quartoRepository) {
        this.hotelRepository = hotelRepository;
        this.quartoRepository = quartoRepository;
    }

    public Hotel save(HotelRecordDTO hotelRecordDTO) {
        Hotel hotel = new Hotel();
        hotel.setEndereco(hotelRecordDTO.getEndereco());
        hotel.setNome(hotelRecordDTO.getNome());
        hotel.setQuartos(quartoRepository.findAllById(hotelRecordDTO.getIdQuartos()).stream().collect(Collectors.toList()));
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> findById(Integer id) {
        return hotelRepository.findById(id);
    }

    public void delete(Integer id) {
        hotelRepository.deleteById(id);
    }
}
