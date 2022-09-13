package com.example.demo.Forma;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaRepository  extends JpaRepository<Forma, Integer>{

    List<Forma> findByAgrupamento(Forma forma);

    List<Forma> findByOrderByIdAsc();

    @Query(value = "select count(f) from forma f where f.agrupamento_id = ?1", 
    nativeQuery = true)
    int contaFormasNoAgrupamentos(int id);

}
