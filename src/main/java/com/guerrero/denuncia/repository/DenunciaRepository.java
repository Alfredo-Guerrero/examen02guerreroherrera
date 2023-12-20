package com.guerrero.denuncia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guerrero.denuncia.entity.Denuncia;

import java.util.List;
import java.util.Optional;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Integer>{
	List<Denuncia> findByDniContaining(String dni, org.springframework.data.domain.Pageable page); // Metodo para buscar por el mombre
	Denuncia findByDni(String dni);
	Optional<Denuncia> findByDniContaining(String dni);
}