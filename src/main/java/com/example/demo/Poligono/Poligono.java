package com.example.demo.Poligono;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Poligono")
@Table(name = "poligono")
public class Poligono {   
    @Id
    @SequenceGenerator(
        name = "sequencia_estudante",
        sequenceName = "sequencia_estudante",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "sequencia_estudante"
    )        
    @Column(name = "id",updatable = false)
    private int id;
    @Column(name = "lados",updatable = true)
    private int lados;
    @Column(name = "tamanho",updatable = true)
    private float tamanho;
    @Column(name = "dataCriacao",updatable = false, columnDefinition = "DATE")
    private LocalDate dataCriacao;
    @Column(name = "ultimaModificacao",updatable = true, columnDefinition = "DATE")
    private LocalDate ultimaModificacao;


    public Poligono() {
    }


    public Poligono(int lados, float tamanho, LocalDate dataCriacao, LocalDate ultimaModificacao) {
        this.lados = lados;
        this.tamanho = tamanho;
        this.dataCriacao = dataCriacao;
        this.ultimaModificacao = ultimaModificacao;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLados() {
        return this.lados;
    }

    public void setLados(int lados) {
        this.lados = lados;
    }

    public float getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getUltimaModificacao() {
        return this.ultimaModificacao;
    }

    public void setUltimaModificacao(LocalDate ultimaModificacao) {
        this.ultimaModificacao = ultimaModificacao;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", lados='" + getLados() + "'" +
            ", tamanho='" + getTamanho() + "'" +
            ", dataCriacao='" + getDataCriacao() + "'" +
            ", ultimaModificacao='" + getUltimaModificacao() + "'" +
            "}";
    }
    
}


