package com.example.demo.Poligono;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Forma.Forma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping//Criar
    public void adicionarPoligono(@RequestBody Poligono poligono){
        poligonoService.adicionarPoligono(poligono);
    }

    @DeleteMapping(path = "apagar/{idPoligono}")//Apagar
    public void deletarPoligono(@PathVariable int idPoligono){
        poligonoService.deletarPoligono(idPoligono);
    }

    @PutMapping(path = "editar/{idPoligono}")//Editar
    public void atualizarPoligono(@PathVariable int idPoligono, @RequestParam(required = false) String lados, @RequestParam(required = false) String tamanho){
        poligonoService.atualizarPoligono(idPoligono, lados, tamanho); 
    }

    @GetMapping(path = "agrupamento")//Listar agrupando poligonos iguais
	public List<ContadorPoligono> getPoligonosIguais(){
        return poligonoService.getPoligonosIguais();
    }

    public void verificarEstoque(List<Integer> ids){
        poligonoService.verificarEstoque(ids);
    }

    public void insereNaForma(List<Integer> ids, Forma forma){
        poligonoService.insereNaForma(ids, forma);
    }
}