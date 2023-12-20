package com.guerrero.denuncia.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="denuncia")
public class Denuncia {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //campo autogenerado -autoingremental
	private Integer id;
	
	@Column(unique = true,nullable = false, length = 8)
	private String dni;
	
	@Column(name="fecha",nullable = false, length = 10)	
	private Date fecha;
	
	@Column(name="falta",nullable = false,length = 3)
	private String falta;
	
	@Column(name="infraccion",nullable = false,length = 200)
	private String infraccion;
	
	@Column(name="descripcion",nullable = false,length = 255)
	private String descripcion;
	
	@Column(name="created_at",nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt; //Agregar
	
	@Column(name="updated_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;//Modificar
	
	@Column(name="activo",nullable=false)
	private Boolean activo;
}

