package com.app.wallabot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.wallabot.entities.Busquedas;

@Repository
public interface BusquedasRepository extends JpaRepository<Busquedas, Long> {

}
