package com.reservapp.entrega.modelo.control;

import com.reservapp.entrega.dataaccess.dao.*;
import com.reservapp.entrega.exceptions.*;
import com.reservapp.entrega.modelo.*;
import com.reservapp.entrega.modelo.dto.MenuDTO;
import com.reservapp.entrega.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("MenuLogic")
public class MenuLogic implements IMenuLogic {
    private static final Logger log = LoggerFactory.getLogger(MenuLogic.class);

    /**
     * DAO injected by Spring that manages Menu entities
     *
     */
    @Autowired
    private IMenuDAO menuDAO;

    /**
    * Logic injected by Spring that manages Establecimiento entities
    *
    */
    @Autowired
    IEstablecimientoLogic logicEstablecimiento1;

    @Transactional(readOnly = true)
    public List<Menu> getMenu() throws Exception {
        log.debug("finding all Menu instances");

        List<Menu> list = new ArrayList<Menu>();

        try {
            list = menuDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Menu failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Menu");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveMenu(Menu entity) throws Exception {
        log.debug("saving Menu instance");

        try {
            if (entity.getEstablecimiento() == null) {
                throw new ZMessManager().new ForeignException("establecimiento");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
            }

            if (entity.getIdMenu() == null) {
                throw new ZMessManager().new EmptyFieldException("idMenu");
            }

            if ((entity.getImagen() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getImagen(),
                        45) == false)) {
                throw new ZMessManager().new NotValidFormatException("imagen");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        45) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (entity.getEstablecimiento().getIdEstablecimiento() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idEstablecimiento_Establecimiento");
            }

            if (getMenu(entity.getIdMenu()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            menuDAO.save(entity);

            log.debug("save Menu successful");
        } catch (Exception e) {
            log.error("save Menu failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteMenu(Menu entity) throws Exception {
        log.debug("deleting Menu instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Menu");
        }

        if (entity.getIdMenu() == null) {
            throw new ZMessManager().new EmptyFieldException("idMenu");
        }

        try {
            menuDAO.delete(entity);

            log.debug("delete Menu successful");
        } catch (Exception e) {
            log.error("delete Menu failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateMenu(Menu entity) throws Exception {
        log.debug("updating Menu instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Menu");
            }

            if (entity.getEstablecimiento() == null) {
                throw new ZMessManager().new ForeignException("establecimiento");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
            }

            if (entity.getIdMenu() == null) {
                throw new ZMessManager().new EmptyFieldException("idMenu");
            }

            if ((entity.getImagen() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getImagen(),
                        45) == false)) {
                throw new ZMessManager().new NotValidFormatException("imagen");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        45) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (entity.getEstablecimiento().getIdEstablecimiento() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idEstablecimiento_Establecimiento");
            }

            menuDAO.update(entity);

            log.debug("update Menu successful");
        } catch (Exception e) {
            log.error("update Menu failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<MenuDTO> getDataMenu() throws Exception {
        try {
            List<Menu> menu = menuDAO.findAll();

            List<MenuDTO> menuDTO = new ArrayList<MenuDTO>();

            for (Menu menuTmp : menu) {
                MenuDTO menuDTO2 = new MenuDTO();

                menuDTO2.setIdMenu(menuTmp.getIdMenu());
                menuDTO2.setDescripcion((menuTmp.getDescripcion() != null)
                    ? menuTmp.getDescripcion() : null);
                menuDTO2.setImagen((menuTmp.getImagen() != null)
                    ? menuTmp.getImagen() : null);
                menuDTO2.setNombre((menuTmp.getNombre() != null)
                    ? menuTmp.getNombre() : null);
                menuDTO2.setPrecio((menuTmp.getPrecio() != null)
                    ? menuTmp.getPrecio() : null);
                menuDTO2.setIdEstablecimiento_Establecimiento((menuTmp.getEstablecimiento()
                                                                      .getIdEstablecimiento() != null)
                    ? menuTmp.getEstablecimiento().getIdEstablecimiento() : null);
                menuDTO.add(menuDTO2);
            }

            return menuDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Menu getMenu(Integer idMenu) throws Exception {
        log.debug("getting Menu instance");

        Menu entity = null;

        try {
            entity = menuDAO.findById(idMenu);
        } catch (Exception e) {
            log.error("get Menu failed", e);
            throw new ZMessManager().new FindingException("Menu");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Menu> findPageMenu(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Menu> entity = null;

        try {
            entity = menuDAO.findPage(sortColumnName, sortAscending, startRow,
                    maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Menu Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberMenu() throws Exception {
        Long entity = null;

        try {
            entity = menuDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Menu Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<Menu> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Menu> list = new ArrayList<Menu>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = menuDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
