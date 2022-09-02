package com.example.demo.Poligono;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "api/poligonos/CRUD")
public class PoligonoController {
    
    private final PoligonoService poligonoService;

    @Autowired
	public PoligonoController(PoligonoService poligonoService) { 
        this.poligonoService = poligonoService;
    }

    @GetMapping
	public List<Poligono> getPoligonos(){
        return poligonoService.getPoligonos();
    }
}