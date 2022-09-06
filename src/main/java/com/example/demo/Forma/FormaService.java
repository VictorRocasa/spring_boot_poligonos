package com.example.demo.Forma;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Poligono.Poligono;
import com.example.demo.Poligono.PoligonoController;


@Service
public class FormaService {

    private final FormaRepository formaRepository;
    private final PoligonoController poligonoController;

    @Autowired
    public FormaService(FormaRepository formaRepository, PoligonoController poligonoController) {
        this.formaRepository = formaRepository;
        this.poligonoController = poligonoController;
    }

public List<FormaComposta> getFormas() {
        List<Forma> formas = formaRepository.findAll();
        List<FormaComposta> formasCompostas = new ArrayList<FormaComposta>();
        for(Forma f: formas){
            FormaComposta forma = new FormaComposta();
            forma.setId(f.getId());
            List<Forma> formasCompositoras = formaRepository.findByAgrupamento(f);
            if(formasCompositoras.size()>0){
                List<Integer> ids = new ArrayList<Integer>();
                for(Forma fj: formasCompositoras)
                    ids.add(fj.getId());
                forma.setFormas(ids);
            }
            List<Poligono> poligonosCompositores = poligonoController.findByForma(f);
            if(poligonosCompositores.size()>0){
                List<Integer> ids = new ArrayList<Integer>();
                for(Poligono p: poligonosCompositores)
                    ids.add(p.getId());
                forma.setPoligonos(ids);
            }
            formasCompostas.add(forma);
        }
        return formasCompostas;
    }

    @Transactional
    public void adicionarForma(FormaComposta formaConstrutor) {
        if(formaConstrutor.getPoligonos() == null && formaConstrutor.getFormas() == null)//Ambos vazios = erro
            throw new IllegalStateException("Erro! Selecione poligonos ou formas existentes!");
        if(formaConstrutor.getPoligonos() != null)//Se poligonos nao estiver vazio
            poligonoController.verificarEstoque(formaConstrutor.getPoligonos());//verifica se poligonos existem e ha estoque
        List<Forma> formas = null;
        if(formaConstrutor.getFormas() != null){//se formas nao estiver vazio
            formas = formaRepository.findAllById(formaConstrutor.getFormas());
            if(formas.size() < formaConstrutor.getFormas().size())//verifica se todas existem
                throw new IllegalStateException("No minimo um dos ids enviados não corresponde a uma forma cadastrado");
            for(Forma f: formas)//verifica se ha estoque das formas selecionadas
                if(f.getAgrupamento()!=null)
                    throw new IllegalStateException("Não há estoque da forma de id "+f.getId());
        }
        Forma forma = formaRepository.save(new Forma());
        if(formaConstrutor.getFormas() != null){
            for(Forma f: formas)
                f.setAgrupamento(forma);
        }            

        if(formaConstrutor.getPoligonos() != null)
            poligonoController.insereNaForma(formaConstrutor.getPoligonos(), forma);  
    }
 
    @Transactional
    public void deletarForma(int idForma) {
        //Se a forma for parte de outra forma o proprio banco barra
        //Deixar a chave estrangeira de outras formas como null e apagar a forma
        Optional<Forma> existe = formaRepository.findById(idForma);
        if(!existe.isPresent())
            throw new IllegalStateException("Nenhuma forma cadastrada com o id "+idForma);
        if(existe.get().getAgrupamento()!=null)
            throw new IllegalStateException("Essa forma compõe outra forma e não pode ser deletada!");
        List<Forma> agrupamentos = formaRepository.findByAgrupamento(existe.get());
        poligonoController.findPoligonosByForma(existe.get());
        for(Forma a: agrupamentos)
            a.setAgrupamento(null);
        formaRepository.deleteById(idForma);    
        //Deixar a chave estrangeira de poligono como null e apagar a forma
    }

    public void atualizarForma(int idForma, String lados, String tamanho) {
    }

}
