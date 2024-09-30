package br.com.unifacisa.Fase2Projeto.service;

import br.com.unifacisa.Fase2Projeto.DTO.QuartoRecordDTO;
import br.com.unifacisa.Fase2Projeto.entities.Hotel;
import br.com.unifacisa.Fase2Projeto.entities.Quarto;
import br.com.unifacisa.Fase2Projeto.repository.HotelRepository;
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
    private final HotelRepository hotelRepository; // Adicionado repositório do Hotel

    @Autowired
    public QuartoService(QuartoRepository quartoRepository, ReservaRepository reservaRepository, HotelRepository hotelRepository) {
        this.quartoRepository = quartoRepository;
        this.reservaRepository = reservaRepository;
        this.hotelRepository = hotelRepository; // Inicializado o repositório do Hotel
    }

    public Quarto save(QuartoRecordDTO quartoRecordDTO) {
        Quarto quarto = new Quarto();
        quarto.setTipoQuarto(quartoRecordDTO.getTipoQuarto());
        quarto.setStatus(quartoRecordDTO.getStatus());
        quarto.setCapacidade(quartoRecordDTO.getCapacidade());
        quarto.setPreco(quartoRecordDTO.getPreco());

        // Buscar a entidade Hotel pelo hotelId
        Hotel hotel = hotelRepository.findById(quartoRecordDTO.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel não encontrado com ID: " + quartoRecordDTO.getHotelId()));
        quarto.setHotel(hotel); // Definir o objeto Hotel

        // Se existirem reservas, busca todas pelo ID
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

    public Quarto update(Integer id, QuartoRecordDTO quartoRecordDTO) {
        Quarto quarto = quartoRepository.findById(id).orElseThrow(() -> new RuntimeException("Quarto não encontrado com ID: " + id));

        quarto.setTipoQuarto(quartoRecordDTO.getTipoQuarto());
        quarto.setStatus(quartoRecordDTO.getStatus());
        quarto.setCapacidade(quartoRecordDTO.getCapacidade());
        quarto.setPreco(quartoRecordDTO.getPreco());

        // Atualizar o hotel, se houver alteração
        Hotel hotel = hotelRepository.findById(quartoRecordDTO.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel não encontrado com ID: " + quartoRecordDTO.getHotelId()));
        quarto.setHotel(hotel); // Atualizar a referência ao hotel

        // Atualiza as reservas se existirem
        quarto.setReservas(reservaRepository.findAllById(quartoRecordDTO.getIdReservas()).stream().collect(Collectors.toList()));

        return quartoRepository.save(quarto);
    }
}
