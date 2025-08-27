package com.microservicio_user.repository;


import com.microservicio_user.entity.Pausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPausa extends JpaRepository<Pausa, Long> {

}
