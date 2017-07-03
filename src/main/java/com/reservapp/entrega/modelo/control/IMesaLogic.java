package com.reservapp.entrega.modelo.control;

import com.reservapp.entrega.modelo.Mesa;
import com.reservapp.entrega.modelo.dto.MesaDTO;

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
public interface IMesaLogic {
    public List<Mesa> getMesa() throws Exception;

    /**
         * Save an new Mesa entity
         */
    public void saveMesa(Mesa entity) throws Exception;

    /**
         * Delete an existing Mesa entity
         *
         */
    public void deleteMesa(Mesa entity) throws Exception;

    /**
        * Update an existing Mesa entity
        *
        */
    public void updateMesa(Mesa entity) throws Exception;

    /**
         * Load an existing Mesa entity
         *
         */
    public Mesa getMesa(Integer idMesa) throws Exception;

    public List<Mesa> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Mesa> findPageMesa(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMesa() throws Exception;

    public List<MesaDTO> getDataMesa() throws Exception;
}
