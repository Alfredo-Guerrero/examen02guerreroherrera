package com.guerrero.denuncia.validator;

import com.guerrero.denuncia.entity.Denuncia;
import com.guerrero.denuncia.exceptions.ValidateServiceException;

public class DenunciaValidator {
	public static void save(Denuncia denuncia) {
        if (denuncia.getDni() == null || denuncia.getDni().isEmpty()) {
            throw new ValidateServiceException("El dni es requerido");
        }
        if (denuncia.getDni().length()>100) {
        	throw new ValidateServiceException("El dni es muy largo");
        }/*
        if (alumno.getPrecio()==null) {
        	throw new ValidateServiceException("El precio es requerido");
        }
        if (alumno.getPrecio()<0) {
        	throw new ValidateServiceException("El precio es incorrecto");
        }*/
    }
}

