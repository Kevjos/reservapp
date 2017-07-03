package com.reservapp.entrega.modelo.control;

import com.reservapp.entrega.modelo.Reserva;
import com.reservapp.entrega.modelo.dto.ReservaDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IReservaLogic {
    public List<Reserva> getReserva() throws Exception;

    /**
         * Save an new Reserva entity
         */
    public void saveReserva(Reserva entity) throws Exception;

    /**
         * Delete an existing Reserva entity
         *
         */
    public void deleteReserva(Reserva entity) throws Exception;

    /**
        * Update an existing Reserva entity
        *
        */
    public void updateReserva(Reserva entity) throws Exception;

    /**
         * Load an existing Reserva entity
         *
         */
    public Reserva getReserva(Integer idReserva) throws Exception;

    public List<Reserva> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Reserva> findPageReserva(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberReserva() throws Exception;

    public List<ReservaDTO> getDataReserva() throws Exception;
}
