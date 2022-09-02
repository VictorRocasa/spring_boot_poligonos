package com.example.Poligono;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class PoligonoService {

    private final PoligonoRepository poligonoRepository;

    @Autowired
	public PoligonoService(PoligonoRepository poligonoRepository) {
        this.poligonoRepository = poligonoRepository;
    }

    public List<Poligono> getPoligonos() {
        return null;
    }
}
