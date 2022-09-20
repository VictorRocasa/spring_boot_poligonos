package com.example.demo.Forma;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/formas/CRUD")
@CrossOrigin("*")
public class FormaController {
    
    private final FormaService formaService;

    @Autowired
	public FormaController(FormaService formaService) { 
        this.formaService = formaService;
    }

    @GetMapping(path = "{idForma}")//Retornar forma por id
	public  FormaFormatador getForma(@PathVariable int idForma){
        return formaService.getForma(idForma);
    }

    @GetMapping//Listar
	public  List<FormaFormatador> getFormas(){
        return formaService.getFormas();
    }

    @GetMapping(path = "agrupamento")//Listar
	public  List<FormaFormatador> listarEstoque(){
        return formaService.listarEstoque();
    }

    @PostMapping//Criar
    public void adicionarForma(@RequestBody FormaConstrutor formaConstrutor){
        formaService.adicionarForma(formaConstrutor);
    }

    @DeleteMapping(path = "apagar/{idForma}")//Apagar
    public void deletarForma(@PathVariable int idForma){
        formaService.deletarForma(idForma);
    }

    @PutMapping(path = "editar/{idForma}")//Editar
    public void atualizarForma(@PathVariable int idForma, @RequestBody FormaEditor formaEditor){
        formaService.atualizarForma(idForma, formaEditor); 
    }
}