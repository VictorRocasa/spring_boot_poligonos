package com.example.demo.Forma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Poligono.PoligonoController;

@Service
public class FormaService {

    private final FormaRepository formaRepository;
    private final AgrupamentoRepository agrupamentoRepository;
    private final PoligonoController poligonoController;

    @Autowired
    public FormaService(FormaRepository formaRepository, AgrupamentoRepository agrupamentoRepository, PoligonoController poligonoController) {
        this.formaRepository = formaRepository;
        this.agrupamentoRepository = agrupamentoRepository;
        this.poligonoController = poligonoController;
    }


    public List<Forma> getFormas() {
        return null;
    }

    public void adicionarForma(FormaConstrutor formaConstrutor) {
        if(formaConstrutor.getPoligonos() == null && formaConstrutor.getFormas() == null)//Ambos vazios = erro
            throw new IllegalStateException("Erro! Selecione poligonos ou formas existentes!");
        if(formaConstrutor.getPoligonos() != null)//Se poligonos nao estiver vazio
            poligonoController.verificarEstoque(formaConstrutor.getPoligonos());//verifica se poligono existe e esta disponivel
        if(formaConstrutor.getFormas() != null){//se formas nao estiver vazio
            List<Forma> formas = formaRepository.findAllById(formaConstrutor.getFormas());
            if(formas.size() < formaConstrutor.getFormas().size())//verifica se existe
                throw new IllegalStateException("No minimo um dos ids enviados não corresponde a uma forma cadastrado");
            for(Forma f: formas)//verifica se ha estoque das formas selecionadas
                if(f.getAgrupamento()!=null)
                    throw new IllegalStateException("Não há estoque da forma de id "+f.getId());
        }
        Forma forma;
        if(formaConstrutor.getFormas() != null){
            Agrupamento agrupamento = agrupamentoRepository.save(new Agrupamento());
            forma = formaRepository.save(new Forma(agrupamento));
        }
        else
            forma = formaRepository.save(new Forma());
        if(formaConstrutor.getPoligonos() != null)
            poligonoController.insereNaForma(formaConstrutor.getPoligonos(), forma);  
    }

    public void deletarForma(int idForma) {
    }

    public void atualizarForma(int idForma, String lados, String tamanho) {
    }

}
