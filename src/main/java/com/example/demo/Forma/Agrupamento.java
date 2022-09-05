package com.example.demo.Forma;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Agrupamento")
@Table(name = "agrupamento")
public class Agrupamento {   
    @Id
    @SequenceGenerator(
        name = "sequencia_agrupamento",
        sequenceName = "sequencia_agrupamento",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "sequencia_agrupamento"
    )        
    @Column(name = "id",updatable = false)
    private int id;    


    public Agrupamento() {
    }


    public Agrupamento(int id) {
        this.id = id;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            "}";
    }

}


