package com.reservapp.entrega.modelo.control;

import com.reservapp.entrega.dataaccess.dao.*;
import com.reservapp.entrega.exceptions.*;
import com.reservapp.entrega.modelo.*;
import com.reservapp.entrega.modelo.dto.EstablecimientoDTO;
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
@Service("EstablecimientoLogic")
public class EstablecimientoLogic implements IEstablecimientoLogic {
    private static final Logger log = LoggerFactory.getLogger(EstablecimientoLogic.class);

    /**
     * DAO injected by Spring that manages Establecimiento entities
     *
     */
    @Autowired
    private IEstablecimientoDAO establecimientoDAO;

    /**
    * DAO injected by Spring that manages Menu entities
    *
    */
    @Autowired
    private IMenuDAO menuDAO;

    /**
    * DAO injected by Spring that manages Mesa entities
    *
    */
    @Autowired
    private IMesaDAO mesaDAO;

    @Transactional(readOnly = true)
    public List<Establecimiento> getEstablecimiento() throws Exception {
        log.debug("finding all Establecimiento instances");

        List<Establecimiento> list = new ArrayList<Establecimiento>();

        try {
            list = establecimientoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Establecimiento failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Establecimiento");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveEstablecimiento(Establecimiento entity)
        throws Exception {
        log.debug("saving Establecimiento instance");

        try {
            if ((entity.getCorreo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getCorreo(),
                        45) == false)) {
                throw new ZMessManager().new NotValidFormatException("correo");
            }

            if ((entity.getDireccion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDireccion(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "direccion");
            }

            if (entity.getIdEstablecimiento() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idEstablecimiento");
            }

            if ((entity.getLatitud() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getLatitud(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException("latitud");
            }

            if ((entity.getLongitud() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getLongitud(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException("longitud");
            }

            if (entity.getMesas() == null) {
                throw new ZMessManager().new EmptyFieldException("mesas");
            }

            if (entity.getNit() == null) {
                throw new ZMessManager().new EmptyFieldException("nit");
            }

            if ((entity.getNit() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNit(), 9) == false)) {
                throw new ZMessManager().new NotValidFormatException("nit");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        45) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if ((entity.getTelefono() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTelefono(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException("telefono");
            }

            if (getEstablecimiento(entity.getIdEstablecimiento()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            establecimientoDAO.save(entity);

            log.debug("save Establecimiento successful");
        } catch (Exception e) {
            log.error("save Establecimiento failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteEstablecimiento(Establecimiento entity)
        throws Exception {
        log.debug("deleting Establecimiento instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Establecimiento");
        }

        if (entity.getIdEstablecimiento() == null) {
            throw new ZMessManager().new EmptyFieldException(
                "idEstablecimiento");
        }

        List<Menu> menus = null;
        List<Mesa> mesas_1 = null;

        try {
            menus = menuDAO.findByProperty("establecimiento.idEstablecimiento",
                    entity.getIdEstablecimiento());

            if (Utilities.validationsList(menus) == true) {
                throw new ZMessManager().new DeletingException("menus");
            }

            mesas_1 = mesaDAO.findByProperty("establecimiento.idEstablecimiento",
                    entity.getIdEstablecimiento());

            if (Utilities.validationsList(mesas_1) == true) {
                throw new ZMessManager().new DeletingException("mesas_1");
            }

            establecimientoDAO.delete(entity);

            log.debug("delete Establecimiento successful");
        } catch (Exception e) {
            log.error("delete Establecimiento failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateEstablecimiento(Establecimiento entity)
        throws Exception {
        log.debug("updating Establecimiento instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "Establecimiento");
            }

            if ((entity.getCorreo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getCorreo(),
                        45) == false)) {
                throw new ZMessManager().new NotValidFormatException("correo");
            }

            if ((entity.getDireccion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDireccion(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "direccion");
            }

            if (entity.getIdEstablecimiento() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idEstablecimiento");
            }

            if ((entity.getLatitud() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getLatitud(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException("latitud");
            }

            if ((entity.getLongitud() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getLongitud(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException("longitud");
            }

            if (entity.getMesas() == null) {
                throw new ZMessManager().new EmptyFieldException("mesas");
            }

            if (entity.getNit() == null) {
                throw new ZMessManager().new EmptyFieldException("nit");
            }

            if ((entity.getNit() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNit(), 9) == false)) {
                throw new ZMessManager().new NotValidFormatException("nit");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        45) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if ((entity.getTelefono() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTelefono(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException("telefono");
            }

            establecimientoDAO.update(entity);

            log.debug("update Establecimiento successful");
        } catch (Exception e) {
            log.error("update Establecimiento failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<EstablecimientoDTO> getDataEstablecimiento()
        throws Exception {
        try {
            List<Establecimiento> establecimiento = establecimientoDAO.findAll();

            List<EstablecimientoDTO> establecimientoDTO = new ArrayList<EstablecimientoDTO>();

            for (Establecimiento establecimientoTmp : establecimiento) {
                EstablecimientoDTO establecimientoDTO2 = new EstablecimientoDTO();

                establecimientoDTO2.setIdEstablecimiento(establecimientoTmp.getIdEstablecimiento());
                establecimientoDTO2.setCorreo((establecimientoTmp.getCorreo() != null)
                    ? establecimientoTmp.getCorreo() : null);
                establecimientoDTO2.setDireccion((establecimientoTmp.getDireccion() != null)
                    ? establecimientoTmp.getDireccion() : null);
                establecimientoDTO2.setLatitud((establecimientoTmp.getLatitud() != null)
                    ? establecimientoTmp.getLatitud() : null);
                establecimientoDTO2.setLongitud((establecimientoTmp.getLongitud() != null)
                    ? establecimientoTmp.getLongitud() : null);
                establecimientoDTO2.setMesas((establecimientoTmp.getMesas() != null)
                    ? establecimientoTmp.getMesas() : null);
                establecimientoDTO2.setNit((establecimientoTmp.getNit() != null)
                    ? establecimientoTmp.getNit() : null);
                establecimientoDTO2.setNombre((establecimientoTmp.getNombre() != null)
                    ? establecimientoTmp.getNombre() : null);
                establecimientoDTO2.setTelefono((establecimientoTmp.getTelefono() != null)
                    ? establecimientoTmp.getTelefono() : null);
                establecimientoDTO.add(establecimientoDTO2);
            }

            return establecimientoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Establecimiento getEstablecimiento(Integer idEstablecimiento)
        throws Exception {
        log.debug("getting Establecimiento instance");

        Establecimiento entity = null;

        try {
            entity = establecimientoDAO.findById(idEstablecimiento);
        } catch (Exception e) {
            log.error("get Establecimiento failed", e);
            throw new ZMessManager().new FindingException("Establecimiento");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Establecimiento> findPageEstablecimiento(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<Establecimiento> entity = null;

        try {
            entity = establecimientoDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "Establecimiento Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberEstablecimiento() throws Exception {
        Long entity = null;

        try {
            entity = establecimientoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "Establecimiento Count");
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
    public List<Establecimiento> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Establecimiento> list = new ArrayList<Establecimiento>();
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
            list = establecimientoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
