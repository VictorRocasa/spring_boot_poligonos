package com.example.demo.Forma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Forma")
@Table(name = "forma")
public class Forma {   
    @Id
    @SequenceGenerator(
        name = "sequencia_forma",
        sequenceName = "sequencia_forma",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "sequencia_forma"
    )        
    @Column(name = "id",updatable = false)
    private int id;    
    @ManyToOne
    private Agrupamento agrupamento;


    public Forma() {
    }


    public Forma(Agrupamento agrupamento) {
        this.agrupamento = agrupamento;
    }


    public Forma(int id, Agrupamento agrupamento) {
        this.id = id;
        this.agrupamento = agrupamento;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agrupamento getAgrupamento() {
        return this.agrupamento;
    }

    public void setAgrupamento(Agrupamento agrupamento) {
        this.agrupamento = agrupamento;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", agrupamento='" + getAgrupamento() + "'" +
            "}";
    }

}


