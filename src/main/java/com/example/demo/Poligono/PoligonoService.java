package com.example.demo.Poligono;

import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Collections;
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
        return poligonoRepository.findByOrderByLadosAsc();
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
        List<Poligono> poligonos = poligonoRepository.findAll();
        if(poligonos.size() == 0)
            return null;
        LadosPoligono[] contador = new LadosPoligono[5];
        for(int i = 0; i < 5; i++)
            contador[i] = new LadosPoligono();
        for(Poligono p: poligonos){
            if(p.getForma()==null)
                contador[p.getLados()-3].adicionarTamanho(p.getTamanho());
        }
        List<ContadorPoligono> poligonosIguais = new ArrayList<ContadorPoligono>();
        int k = 0;//iterador poligonosIguais
        for(int i = 0; i < 5; i++){
            List<Float> tamanhos = contador[i].getTamanhos();
            if(tamanhos.size() > 0){//Se existem poligonos de determinada quantidade de lados
                //Collections.sort(tamanhos);
                int j = 0;
                while(j < tamanhos.size()){
                    float tamanhoAtual = tamanhos.get(j);
                    poligonosIguais.add(new ContadorPoligono(i+3, tamanhoAtual));
                    int valor = 0;//conta a quantidade de vezes que cada tamanho aparece
                    do{//garante que tanto o j quanto o valor incrementem no minimo 1 vez
                        j++;
                        valor++;
                        if(j >= tamanhos.size())
                            break;
                    }while(tamanhos.get(j) == tamanhoAtual);
                    poligonosIguais.get(k).setOcorrencias(valor);
                    k++;
                }
            }
        }
        return poligonosIguais;
    }

    public boolean verificarEstoque(List<Integer> ids){
        List<Poligono> poligonos = poligonoRepository.findAllById(ids);
        if(poligonos.size() < ids.size())//verifica se poligonos existem
            throw new IllegalStateException("No minimo um dos ids enviados não corresponde a um poligono cadastrado");
        for(Poligono p: poligonos)
            if(p.getForma()!=null)//verifica se ha estoque dos poligonos selecionados
                throw new IllegalStateException("Não há estoque do poligono de id "+p.getId());
        return true;
    }

    @Transactional
    public void insereNaForma(List<Integer> ids, Forma forma) {
        List<Poligono> poligonos = poligonoRepository.findAllById(ids);
        for(Poligono p: poligonos)
            p.setForma(forma);
    }

    @Transactional
    public void findPoligonosByForma(Forma forma) {
        List<Poligono> poligonos = poligonoRepository.findPoligonosByForma(forma);
        for(Poligono p: poligonos)
            p.setForma(null);
    }

}
