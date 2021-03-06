package com.reservapp.entrega.modelo;
// Generated 2/07/2017 09:17:23 PM by Hibernate Tools 5.1.0.Final


import java.util.HashSet;
import java.util.Set;

/**
 * Menu generated by hbm2java
 */
public class Menu  implements java.io.Serializable {


     private Integer idMenu;
     private Establecimiento establecimiento;
     private String nombre;
     private Integer precio;
     private String descripcion;
     private String imagen;
     private Set<Reserva> reservas = new HashSet<Reserva>(0);

    public Menu() {
    }

	
    public Menu(Integer idMenu, Establecimiento establecimiento) {
        this.idMenu = idMenu;
        this.establecimiento = establecimiento;
    }
    public Menu(Integer idMenu, Establecimiento establecimiento, String nombre, Integer precio, String descripcion, String imagen, Set<Reserva> reservas) {
       this.idMenu = idMenu;
       this.establecimiento = establecimiento;
       this.nombre = nombre;
       this.precio = precio;
       this.descripcion = descripcion;
       this.imagen = imagen;
       this.reservas = reservas;
    }
   
    public Integer getIdMenu() {
        return this.idMenu;
    }
    
    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }
    public Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }
    
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getImagen() {
        return this.imagen;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public Set<Reserva> getReservas() {
        return this.reservas;
    }
    
    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }




}


