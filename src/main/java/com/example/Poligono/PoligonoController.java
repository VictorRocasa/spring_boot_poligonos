package com.example.Poligono;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/CRUD")
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
