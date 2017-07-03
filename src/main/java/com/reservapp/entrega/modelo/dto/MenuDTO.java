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
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MenuDTO.class);
    private String descripcion;
    private Integer idMenu;
    private String imagen;
    private String nombre;
    private Integer precio;
    private Integer idEstablecimiento_Establecimiento;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getIdEstablecimiento_Establecimiento() {
        return idEstablecimiento_Establecimiento;
    }

    public void setIdEstablecimiento_Establecimiento(
        Integer idEstablecimiento_Establecimiento) {
        this.idEstablecimiento_Establecimiento = idEstablecimiento_Establecimiento;
    }
}
