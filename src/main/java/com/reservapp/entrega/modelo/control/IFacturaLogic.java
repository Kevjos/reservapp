package com.reservapp.entrega.modelo.control;

import com.reservapp.entrega.modelo.Factura;
import com.reservapp.entrega.modelo.dto.FacturaDTO;

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
public interface IFacturaLogic {
    public List<Factura> getFactura() throws Exception;

    /**
         * Save an new Factura entity
         */
    public void saveFactura(Factura entity) throws Exception;

    /**
         * Delete an existing Factura entity
         *
         */
    public void deleteFactura(Factura entity) throws Exception;

    /**
        * Update an existing Factura entity
        *
        */
    public void updateFactura(Factura entity) throws Exception;

    /**
         * Load an existing Factura entity
         *
         */
    public Factura getFactura(Integer idFactura) throws Exception;

    public List<Factura> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Factura> findPageFactura(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberFactura() throws Exception;

    public List<FacturaDTO> getDataFactura() throws Exception;
}
