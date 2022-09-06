package com.example.demo.Forma;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping(path = "api/formas/CRUD")
public class FormaController {
    
    private final FormaService formaService;

    @Autowired
	public FormaController(FormaService formaService) { 
        this.formaService = formaService;
    }

    @GetMapping//Listar
	public  List<FormaComposta> getFormas(){
        return formaService.getFormas();
    }

    @PostMapping//Criar
    public void adicionarForma(@RequestBody FormaComposta formaConstrutor){
        formaService.adicionarForma(formaConstrutor);
    }

    @DeleteMapping(path = "apagar/{idForma}")//Apagar
    public void deletarForma(@PathVariable int idForma){
        formaService.deletarForma(idForma);
    }

    @PutMapping(path = "editar/{idForma}")//Editar
    public void atualizarForma(@PathVariable int idForma, @RequestBody FormaComposta formaConstrutor){
        formaService.atualizarForma(idForma, formaConstrutor); 
    }
}