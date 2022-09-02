package com.example.demo.Poligono;

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
}
