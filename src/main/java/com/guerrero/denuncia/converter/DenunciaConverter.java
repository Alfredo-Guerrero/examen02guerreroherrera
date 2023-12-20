package com.guerrero.denuncia.converter;

import org.springframework.stereotype.Component;

import com.guerrero.denuncia.dto.DenunciaDTO;
import com.guerrero.denuncia.entity.Denuncia;


@Component
public class DenunciaConverter extends AbstractConverter<Denuncia,DenunciaDTO>{

	//Convertir a un DTo
	@Override
	public DenunciaDTO fromEntity(Denuncia entity) {
		if(entity==null) return null;
		return DenunciaDTO.builder() //construirlo
				.id(entity.getId())
				.dni(entity.getDni())
				.fecha(entity.getFecha())
				.falta(entity.getFalta())
				.infraccion(entity.getInfraccion())
				.descripcion(entity.getDescripcion())
				.build();
	}

	@Override
	public Denuncia fromDTO(DenunciaDTO dto) {
		if(dto==null) return null;
		return Denuncia.builder() //construirlo
				.id(dto.getId())
				.dni(dto.getDni())
				.dni(dto.getDni())
				.fecha(dto.getFecha())
				.falta(dto.getFalta())
				.infraccion(dto.getInfraccion())
				.descripcion(dto.getDescripcion())
				.build();
	}

}
