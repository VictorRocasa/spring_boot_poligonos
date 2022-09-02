package com.example.demo.Poligono;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoligonoService {

    private final PoligonoRepository poligonoRepository;

    @Autowired
	public PoligonoService(PoligonoRepository poligonoRepository) {
        this.poligonoRepository = poligonoRepository;
    }

    public List<Poligono> getPoligonos() {
        return poligonoRepository.findAll();
    }

    public void adicionarPoligono(Poligono poligono) {
        poligono.setDataCriacao(LocalDate.now());
        poligono.setUltimaModificacao(LocalDate.now());
        poligonoRepository.save(poligono);
    }
}
