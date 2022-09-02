package com.example.demo.Poligono;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/poligonos/CRUD")
public class PoligonoController {
    
    private final PoligonoService poligonoService;

    @Autowired
	public PoligonoController(PoligonoService poligonoService) { 
        this.poligonoService = poligonoService;
    }

    @GetMapping//Listar
	public List<Poligono> getPoligonos(){
        return poligonoService.getPoligonos();
    }

    @DeleteMapping(path = "{idPoligono}")
    public void deletarPoligono(@PathVariable int idPoligono){
        poligonoService.deletarPoligono(idPoligono);
    }

    @PostMapping//Criar
    public void adicionarPoligono(@RequestBody Poligono poligono){
        poligonoService.adicionarPoligono(poligono);
    }
}