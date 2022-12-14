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

public List<FormaFormatador> getFormas() {
        List<Forma> formas = formaRepository.findAll();
        List<FormaFormatador> JSON = new ArrayList<FormaFormatador>();
        for(Forma f: formas){
            List<Integer> idsPoligonos;
            List<String> resumoPoligonos;
            List<Poligono> poligonos = poligonoController.findByForma(f);
            if(poligonos.size()>0){
                idsPoligonos = new ArrayList<Integer>();
                resumoPoligonos = new ArrayList<String>();
                for(Poligono p: poligonos){
                    idsPoligonos.add(p.getId());
                    resumoPoligonos.add(p.getLados()+" lados de tamanho "+p.getTamanho());
                }
            }
            else{
                idsPoligonos = null;
                resumoPoligonos = null;
            }
            List<Integer> idsFormas;
            List<String> resumoFormas;
            List<Forma> agrupamento = formaRepository.findByAgrupamento(f);
            if(agrupamento.size()>0){
                idsFormas = new ArrayList<Integer>();
                resumoFormas = new ArrayList<String>();
                for(Forma a: agrupamento){
                    idsFormas.add(a.getId());
                    resumoFormas.add(poligonoController.contaPoligonosNaForma(a.getId())+
                    " poligono(s) e "+formaRepository.contaFormasNoAgrupamentos(a.getId())+" forma(s)");
                }
            }
            else{
                idsFormas = null;
                resumoFormas = null;
            }
            JSON.add(new FormaFormatador(f.getId(),idsPoligonos,resumoPoligonos,idsFormas,resumoFormas));
        }
        return JSON;
    }

    public void verificarEstoque(List<Forma> formas){
        for(Forma f: formas)//Verifica se ha estoque das formas selecionadas
            if(f.getAgrupamento()!=null)
                throw new IllegalStateException("N??o h?? estoque da forma de id "+f.getId());   
    }

    public void verificarEstoque(List<Forma> formas, Forma forma){
        for(Forma f: formas)//Verifica se ha estoque das formas selecionadas
            if(f.getAgrupamento()!=null)
                if(f.getAgrupamento()!=forma)
                    throw new IllegalStateException("N??o h?? estoque da forma de id "+f.getId());   
    }

    public void verificarEstoqueRemover(List<Forma> formas, Forma forma){
        for(Forma f: formas)
            if(f.getAgrupamento()!=forma)//Verifica se ha estoque das formas selecionadas
                throw new IllegalStateException("A forma de id "+f.getId()+" nao compoe a selecionada");   
    }

    @Transactional
    public void inserirFormasEmForma(List<Forma> formas, Forma forma){
        for(Forma f: formas)
            f.setAgrupamento(forma);
    }

    @Transactional
    public void adicionarForma(FormaConstrutor formaConstrutor) {
        if(formaConstrutor.getPoligonos() == null && formaConstrutor.getFormas() == null)//Ambos vazios = erro
            throw new IllegalStateException("Erro! Selecione poligonos ou formas existentes!");
        if(formaConstrutor.getPoligonos() != null)//Se poligonos nao estiver vazio
            poligonoController.verificarEstoque(formaConstrutor.getPoligonos());//verifica se poligonos existem e ha estoque
        List<Forma> formas = null;
        if(formaConstrutor.getFormas() != null){//se formas nao estiver vazio
            formas = formaRepository.findAllById(formaConstrutor.getFormas());
            if(formas.size() < formaConstrutor.getFormas().size())//verifica se todas existem
                throw new IllegalStateException("No minimo um dos ids enviados n??o corresponde a uma forma cadastrado");
            verificarEstoque(formas);
        }
        Forma forma = formaRepository.save(new Forma());
        if(formaConstrutor.getFormas() != null){
            inserirFormasEmForma(formas,forma);
        }            

        if(formaConstrutor.getPoligonos() != null)
            poligonoController.insereNaForma(formaConstrutor.getPoligonos(), forma);  
    }
 
    @Transactional
    public void deletarForma(int idForma) {
        //Deixar a chave estrangeira de outras formas como null e apagar a forma
        Optional<Forma> existe = formaRepository.findById(idForma);
        if(!existe.isPresent())
            throw new IllegalStateException("Nenhuma forma cadastrada com o id "+idForma);
        if(existe.get().getAgrupamento()!=null)
            throw new IllegalStateException("Essa forma comp??e outra forma e n??o pode ser deletada!");
        List<Forma> agrupamentos = formaRepository.findByAgrupamento(existe.get());
        for(Forma a: agrupamentos)
            a.setAgrupamento(null);
        //Deixar a chave estrangeira de poligono como null e apagar a forma
        poligonoController.limpaPoligonosDaForma(existe.get());
        formaRepository.deleteById(idForma);    
    }

    @Transactional
    public void atualizarForma(int idForma, FormaEditor formasEditor) {
        //Verifica existencia da forma a ser editada
        Optional<Forma> existe = formaRepository.findById(idForma);
        if(!existe.isPresent())
            throw new IllegalStateException("Nenhuma forma cadastrada com o id "+idForma);
        Forma forma = (Forma) existe.get();              
        if(formasEditor.getNovosPoligonos() == null && formasEditor.getNovasFormas() == null && formasEditor.getPoligonosRemover() == null && formasEditor.getFormasRemover() == null)//Todos vazios = erro
            throw new IllegalStateException("Erro! Sem formas ou poligonos selecionados para adicionar/remover!");
        if(formasEditor.getNovosPoligonos() != null)//Verifica se ha disponibilidade dos novos poligonos a serem adicionados
            poligonoController.verificarEstoque(formasEditor.getNovosPoligonos(), forma);
        if(formasEditor.getPoligonosRemover() != null)//Verifica se existem os poligonos a serem removidos
            poligonoController.verificarEstoqueRemover(formasEditor.getNovosPoligonos(), forma);
        List<Forma> formas = null;
        if(formasEditor.getNovasFormas() != null){//Verifica se ha disponibilidade das novas formas a serem adicionadas
            formas = formaRepository.findAllById(formasEditor.getNovasFormas());
            if(formas.size() < formasEditor.getNovasFormas().size())
                throw new IllegalStateException("No minimo um dos ids enviados n??o corresponde a uma forma cadastrado");
            verificarEstoque(formas,forma);
        }
        List<Forma> agrupamentosRemover = null;
        if(formasEditor.getFormasRemover() != null){
            agrupamentosRemover = formaRepository.findAllById(formasEditor.getFormasRemover());
            if(agrupamentosRemover.size() < formasEditor.getFormasRemover().size())
                throw new IllegalStateException("No minimo um dos ids enviados n??o corresponde a uma forma cadastrado");
            verificarEstoqueRemover(agrupamentosRemover,forma);
        }
        if(formasEditor.getNovosPoligonos()!=null){
            //Insere os novos poligonos na forma
            poligonoController.insereNaForma(formasEditor.getNovosPoligonos(), forma);
        }
        if(formasEditor.getPoligonosRemover()!=null){
            //Remove os poligonos a serem removidos
            poligonoController.limpaPoligonosDaForma(formasEditor.getPoligonosRemover());
        }
        if(formasEditor.getNovasFormas()!=null){
            //Agrupa as novas formas
            for(Forma f:formas)
                f.setAgrupamento(forma);
        }
        if(formasEditor.getFormasRemover()!=null){
            //Remove todas as formas a serem removidas
            for(Forma ar: agrupamentosRemover)
                ar.setAgrupamento(null);
        }
    }
}
