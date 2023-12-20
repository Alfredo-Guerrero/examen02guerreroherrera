package com.guerrero.denuncia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guerrero.denuncia.entity.Denuncia;
import com.guerrero.denuncia.exceptions.GeneralServiceException;
import com.guerrero.denuncia.exceptions.NoDataFoundException;
import com.guerrero.denuncia.exceptions.ValidateServiceException;
import com.guerrero.denuncia.repository.DenunciaRepository;
import com.guerrero.denuncia.service.DenunciaService;
import com.guerrero.denuncia.validator.DenunciaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DenunciaServiceImpl implements DenunciaService {
	@Autowired
	private DenunciaRepository repository;
	
	@Override
	@Transactional(readOnly=true) // Metodo de solo lectura 
	public List<Denuncia> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Denuncia> finByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni,page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Denuncia findById(int id) {
		try {
			Denuncia registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Denuncia save(Denuncia denuncia) {
		try {
			DenunciaValidator.save(denuncia);
			if(repository.findByDni(denuncia.getDni())!=null) {
				throw new ValidateServiceException("Ya existe un registro con el nombre"+denuncia.getDni());
			}
			denuncia.setActivo(true);
			Denuncia registro=repository.save(denuncia);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Denuncia update(Denuncia denuncia) {
		try {
			DenunciaValidator.save(denuncia);
			Denuncia registro=repository.findById(denuncia.getId()).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			Denuncia registroD = repository.findByDni(denuncia.getDni());
			if(registroD!=null && registroD.getId()!=denuncia.getId()) {
				throw new ValidateServiceException("Ya existe un registro con el nombre"+denuncia.getDni());
			}
			registro.setDni(denuncia.getDni());
			registro.setFecha(denuncia.getFecha());
			registro.setFalta(denuncia.getFalta());
			registro.setInfraccion(denuncia.getInfraccion());
			registro.setDescripcion(denuncia.getDescripcion());
			
			//registro.setPrecio(alumno.getPrecio());
			repository.save(registro);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Denuncia registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			repository.delete(registro);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Denuncia finByDni(String dni) {
	    try {
	        return repository.findByDniContaining(dni).orElseThrow(() -> new NoDataFoundException("No existe el DNI"));
	    } catch (ValidateServiceException | NoDataFoundException e) {
	        log.info(e.getMessage(), e);
	        throw e;
	    } catch (Exception e) {
	        log.error(e.getMessage(), e);
	        throw new GeneralServiceException(e.getMessage(), e);
	    }
	}

}
