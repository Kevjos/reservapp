package com.reservapp.entrega.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public class MesaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MesaDTO.class);
    private String estado;
    private Integer idMesa;
    private Integer puestos;
    private String ubicacion;
    private Integer idEstablecimiento_Establecimiento;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Integer getPuestos() {
        return puestos;
    }

    public void setPuestos(Integer puestos) {
        this.puestos = puestos;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getIdEstablecimiento_Establecimiento() {
        return idEstablecimiento_Establecimiento;
    }

    public void setIdEstablecimiento_Establecimiento(
        Integer idEstablecimiento_Establecimiento) {
        this.idEstablecimiento_Establecimiento = idEstablecimiento_Establecimiento;
    }
}
