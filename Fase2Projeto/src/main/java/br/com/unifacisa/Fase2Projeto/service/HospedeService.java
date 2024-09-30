package br.com.unifacisa.Fase2Projeto.service;
import br.com.unifacisa.Fase2Projeto.DTO.HospedeRecordDTO;
import br.com.unifacisa.Fase2Projeto.DTO.HotelRecordDTO;
import br.com.unifacisa.Fase2Projeto.entities.Hospede;
import br.com.unifacisa.Fase2Projeto.entities.Hotel;
import br.com.unifacisa.Fase2Projeto.repository.HospedeRepository;
import br.com.unifacisa.Fase2Projeto.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospedeService {
    private final HospedeRepository hospedeRepository;
    private final ReservaRepository reservaRepository;
    @Autowired
    public HospedeService(HospedeRepository hospedeRepository, ReservaRepository reservaRepository) {
        this.hospedeRepository = hospedeRepository;
        this.reservaRepository = reservaRepository;
    }


    public Hospede save(HospedeRecordDTO hospedeRecordDTO){
        Hospede hospede = new Hospede();
        hospede.setNome(hospedeRecordDTO.getNome());
        hospede.setCpf(hospedeRecordDTO.getCpf());
        hospede.setContato(hospedeRecordDTO.getContato());
        hospede.setEndereco(hospedeRecordDTO.getEndereco());
        hospede.setDataNascimento(hospedeRecordDTO.getDataNascimento());
        hospede.setReservas(reservaRepository.findAllById(hospedeRecordDTO.getIdReservas()).stream().collect(Collectors.toList()));
        return hospedeRepository.save(hospede);
    }

public List<Hospede> getAll(){
        return hospedeRepository.findAll();
}
    public Optional<Hospede> findById(Integer id) {
        return hospedeRepository.findById(id);
    }

    public void delete(Integer id) {
        hospedeRepository.deleteById(id);
    }


    public Hospede update(Integer id, HospedeRecordDTO hospedeRecordDTO) {
        Hospede hospede = hospedeRepository.findById(id).get();
        hospede.setNome(hospedeRecordDTO.getNome());
        hospede.setCpf(hospedeRecordDTO.getCpf());
        hospede.setContato(hospedeRecordDTO.getContato());
        hospede.setEndereco(hospedeRecordDTO.getEndereco());
        hospede.setDataNascimento(hospedeRecordDTO.getDataNascimento());
        hospede.setReservas(reservaRepository.findAllById(hospedeRecordDTO.getIdReservas()).stream().collect(Collectors.toList()));
        return hospedeRepository.save(hospede);
    }

}
