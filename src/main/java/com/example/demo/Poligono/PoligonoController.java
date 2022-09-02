package com.example.demo.Poligono;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/v1/poligono")
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