package com.guerrero.denuncia.service;

import org.springframework.data.domain.Pageable;

import com.guerrero.denuncia.entity.Denuncia;

import java.util.List;

public interface DenunciaService {
	//Metodos
	public List<Denuncia> findAll(Pageable page);
	public List<Denuncia> finByDni(String dni,Pageable page ); //Busqueda
	public Denuncia findById (int id);
	
	public Denuncia finByDni(String dni);
	
	public Denuncia save (Denuncia denuncia);
	public Denuncia update (Denuncia denuncia);
	public void delete (int id);

}

