package com.example.demo.Poligono;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Forma.Forma;

@Service
public class PoligonoService {

    private final PoligonoRepository poligonoRepository;

    @Autowired
	public PoligonoService(PoligonoRepository poligonoRepository) {
        this.poligonoRepository = poligonoRepository;
    }

    public List<Poligono> getPoligonos() {
        return poligonoRepository.findByOrderByIdAsc();
    }

    public void deletarPoligono(int idPoligono) {
        boolean existe = poligonoRepository.existsById(idPoligono);
        if(!existe)
            throw new IllegalStateException("Nenhum poligono cadastrado com o id "+idPoligono);
        poligonoRepository.deleteById(idPoligono);    
    }

    public void adicionarPoligono(Poligono poligono) {
        poligono.setDataCriacao(LocalDate.now());
        poligono.setUltimaModificacao(LocalDate.now());
        poligonoRepository.save(poligono);
    }
    
    @Transactional
    public void atualizarPoligono(int idPoligono, String lados, String tamanho) {
        Poligono poligono = poligonoRepository.findById(idPoligono).orElseThrow(()
         -> new IllegalStateException("Poligono com o id "+idPoligono+" não existe"));

        if(lados!=null && lados.length() > 0 && !lados.equals(""+poligono.getLados()))
            poligono.setLados(Integer.parseInt(lados));

        if(tamanho!=null && tamanho.length() > 0 && !tamanho.equals(""+poligono.getTamanho())){
            poligono.setTamanho(Float.parseFloat(tamanho));
        }
    }

    public List<ContadorPoligono> getPoligonosIguais() {
        List<Object[]> retorno = poligonoRepository.estoquePoligonos();
        List<ContadorPoligono> poligonosIguais = new ArrayList<>();
        for(Object[] r: retorno){
            poligonosIguais.add(new ContadorPoligono(Integer.parseInt(""+r[0]), Float.parseFloat(""+r[1]), Integer.parseInt(""+r[2])));
        }
        return poligonosIguais;
    }

    public void verificarEstoque(List<Integer> ids){
        List<Poligono> poligonos = poligonoRepository.findAllById(ids);
        if(poligonos.size() < ids.size())//verifica se poligonos existem
            throw new IllegalStateException("No minimo um dos ids enviados não corresponde a um poligono cadastrado");
        for(Poligono p: poligonos)
            if(p.getForma()!=null)//verifica se ha estoque dos poligonos selecionados
                throw new IllegalStateException("Não há estoque do poligono de id "+p.getId());
    }

    public void verificarEstoque(List<Integer> ids, Forma forma){
        List<Poligono> poligonos = poligonoRepository.findAllById(ids);
        if(poligonos.size() < ids.size())//verifica se poligonos existem
            throw new IllegalStateException("No minimo um dos ids enviados não corresponde a um poligono cadastrado");
        for(Poligono p: poligonos)
            if(p.getForma()!=null)//verifica se ha estoque dos poligonos selecionados
                if(p.getForma()!=forma)
                    throw new IllegalStateException("O poligono de id "+p.getId()+" esta atribuido a outra forma");
    }

    public void verificarEstoqueRemover(List<Integer> ids, Forma forma){
        List<Poligono> poligonos = poligonoRepository.findAllById(ids);
        if(poligonos.size() < ids.size())//verifica se poligonos existem
            throw new IllegalStateException("No minimo um dos ids enviados para a remocao não corresponde a um poligono cadastrado");
        for(Poligono p: poligonos)
            if(p.getForma()!=forma)//verifica se ha estoque dos poligonos selecionados
                throw new IllegalStateException("O poligono de id "+p.getId()+" nao faz parte da forma selecionada");
    }

    @Transactional
    public void insereNaForma(List<Integer> ids, Forma forma) {
        List<Poligono> poligonos = poligonoRepository.findAllById(ids);
        for(Poligono p: poligonos)
            p.setForma(forma);
    }

    @Transactional
    public void limpaPoligonosDaForma(Forma forma) {
        List<Poligono> poligonos = poligonoRepository.findByForma(forma);
        for(Poligono p: poligonos)
            p.setForma(null);
    }

    @Transactional
    public void limpaPoligonosDaForma(List<Integer> ids) {
        List<Poligono> poligonos = poligonoRepository.findAllById(ids);
        for(Poligono p: poligonos)
            p.setForma(null);
    }

    public List<Poligono> findByForma(Forma forma) {
        return poligonoRepository.findByForma(forma);
    }

    public int contaPoligonosNaForma(int id) {
        return poligonoRepository.contaPoligonosNaForma(id);
    }

}
