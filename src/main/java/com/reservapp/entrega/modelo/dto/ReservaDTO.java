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
public class ReservaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ReservaDTO.class);
    private Date fechaReserva;
    private Integer idMesa;
    private Integer idReserva;
    private Integer idMesa_Mesa;
    private Integer idUsuario_Usuario;

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Integer getIdMesa_Mesa() {
        return idMesa_Mesa;
    }

    public void setIdMesa_Mesa(Integer idMesa_Mesa) {
        this.idMesa_Mesa = idMesa_Mesa;
    }

    public Integer getIdUsuario_Usuario() {
        return idUsuario_Usuario;
    }

    public void setIdUsuario_Usuario(Integer idUsuario_Usuario) {
        this.idUsuario_Usuario = idUsuario_Usuario;
    }
}
