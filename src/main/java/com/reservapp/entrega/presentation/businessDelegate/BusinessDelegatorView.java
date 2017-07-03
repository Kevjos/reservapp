package com.reservapp.entrega.presentation.businessDelegate;

import com.reservapp.entrega.modelo.Establecimiento;
import com.reservapp.entrega.modelo.Factura;
import com.reservapp.entrega.modelo.Menu;
import com.reservapp.entrega.modelo.Mesa;
import com.reservapp.entrega.modelo.Reserva;
import com.reservapp.entrega.modelo.Rol;
import com.reservapp.entrega.modelo.Usuario;
import com.reservapp.entrega.modelo.control.EstablecimientoLogic;
import com.reservapp.entrega.modelo.control.FacturaLogic;
import com.reservapp.entrega.modelo.control.IEstablecimientoLogic;
import com.reservapp.entrega.modelo.control.IFacturaLogic;
import com.reservapp.entrega.modelo.control.IMenuLogic;
import com.reservapp.entrega.modelo.control.IMesaLogic;
import com.reservapp.entrega.modelo.control.IReservaLogic;
import com.reservapp.entrega.modelo.control.IRolLogic;
import com.reservapp.entrega.modelo.control.IUsuarioLogic;
import com.reservapp.entrega.modelo.control.MenuLogic;
import com.reservapp.entrega.modelo.control.MesaLogic;
import com.reservapp.entrega.modelo.control.ReservaLogic;
import com.reservapp.entrega.modelo.control.RolLogic;
import com.reservapp.entrega.modelo.control.UsuarioLogic;
import com.reservapp.entrega.modelo.dto.EstablecimientoDTO;
import com.reservapp.entrega.modelo.dto.FacturaDTO;
import com.reservapp.entrega.modelo.dto.MenuDTO;
import com.reservapp.entrega.modelo.dto.MesaDTO;
import com.reservapp.entrega.modelo.dto.ReservaDTO;
import com.reservapp.entrega.modelo.dto.RolDTO;
import com.reservapp.entrega.modelo.dto.UsuarioDTO;
import com.reservapp.entrega.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private IEstablecimientoLogic establecimientoLogic;
    @Autowired
    private IFacturaLogic facturaLogic;
    @Autowired
    private IMenuLogic menuLogic;
    @Autowired
    private IMesaLogic mesaLogic;
    @Autowired
    private IReservaLogic reservaLogic;
    @Autowired
    private IRolLogic rolLogic;
    @Autowired
    private IUsuarioLogic usuarioLogic;

    public List<Establecimiento> getEstablecimiento() throws Exception {
        return establecimientoLogic.getEstablecimiento();
    }

    public void saveEstablecimiento(Establecimiento entity)
        throws Exception {
        establecimientoLogic.saveEstablecimiento(entity);
    }

    public void deleteEstablecimiento(Establecimiento entity)
        throws Exception {
        establecimientoLogic.deleteEstablecimiento(entity);
    }

    public void updateEstablecimiento(Establecimiento entity)
        throws Exception {
        establecimientoLogic.updateEstablecimiento(entity);
    }

    public Establecimiento getEstablecimiento(Integer idEstablecimiento)
        throws Exception {
        Establecimiento establecimiento = null;

        try {
            establecimiento = establecimientoLogic.getEstablecimiento(idEstablecimiento);
        } catch (Exception e) {
            throw e;
        }

        return establecimiento;
    }

    public List<Establecimiento> findByCriteriaInEstablecimiento(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return establecimientoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Establecimiento> findPageEstablecimiento(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return establecimientoLogic.findPageEstablecimiento(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberEstablecimiento() throws Exception {
        return establecimientoLogic.findTotalNumberEstablecimiento();
    }

    public List<EstablecimientoDTO> getDataEstablecimiento()
        throws Exception {
        return establecimientoLogic.getDataEstablecimiento();
    }

    public List<Factura> getFactura() throws Exception {
        return facturaLogic.getFactura();
    }

    public void saveFactura(Factura entity) throws Exception {
        facturaLogic.saveFactura(entity);
    }

    public void deleteFactura(Factura entity) throws Exception {
        facturaLogic.deleteFactura(entity);
    }

    public void updateFactura(Factura entity) throws Exception {
        facturaLogic.updateFactura(entity);
    }

    public Factura getFactura(Integer idFactura) throws Exception {
        Factura factura = null;

        try {
            factura = facturaLogic.getFactura(idFactura);
        } catch (Exception e) {
            throw e;
        }

        return factura;
    }

    public List<Factura> findByCriteriaInFactura(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return facturaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Factura> findPageFactura(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return facturaLogic.findPageFactura(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberFactura() throws Exception {
        return facturaLogic.findTotalNumberFactura();
    }

    public List<FacturaDTO> getDataFactura() throws Exception {
        return facturaLogic.getDataFactura();
    }

    public List<Menu> getMenu() throws Exception {
        return menuLogic.getMenu();
    }

    public void saveMenu(Menu entity) throws Exception {
        menuLogic.saveMenu(entity);
    }

    public void deleteMenu(Menu entity) throws Exception {
        menuLogic.deleteMenu(entity);
    }

    public void updateMenu(Menu entity) throws Exception {
        menuLogic.updateMenu(entity);
    }

    public Menu getMenu(Integer idMenu) throws Exception {
        Menu menu = null;

        try {
            menu = menuLogic.getMenu(idMenu);
        } catch (Exception e) {
            throw e;
        }

        return menu;
    }

    public List<Menu> findByCriteriaInMenu(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return menuLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Menu> findPageMenu(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return menuLogic.findPageMenu(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberMenu() throws Exception {
        return menuLogic.findTotalNumberMenu();
    }

    public List<MenuDTO> getDataMenu() throws Exception {
        return menuLogic.getDataMenu();
    }

    public List<Mesa> getMesa() throws Exception {
        return mesaLogic.getMesa();
    }

    public void saveMesa(Mesa entity) throws Exception {
        mesaLogic.saveMesa(entity);
    }

    public void deleteMesa(Mesa entity) throws Exception {
        mesaLogic.deleteMesa(entity);
    }

    public void updateMesa(Mesa entity) throws Exception {
        mesaLogic.updateMesa(entity);
    }

    public Mesa getMesa(Integer idMesa) throws Exception {
        Mesa mesa = null;

        try {
            mesa = mesaLogic.getMesa(idMesa);
        } catch (Exception e) {
            throw e;
        }

        return mesa;
    }

    public List<Mesa> findByCriteriaInMesa(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return mesaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Mesa> findPageMesa(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return mesaLogic.findPageMesa(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberMesa() throws Exception {
        return mesaLogic.findTotalNumberMesa();
    }

    public List<MesaDTO> getDataMesa() throws Exception {
        return mesaLogic.getDataMesa();
    }

    public List<Reserva> getReserva() throws Exception {
        return reservaLogic.getReserva();
    }

    public void saveReserva(Reserva entity) throws Exception {
        reservaLogic.saveReserva(entity);
    }

    public void deleteReserva(Reserva entity) throws Exception {
        reservaLogic.deleteReserva(entity);
    }

    public void updateReserva(Reserva entity) throws Exception {
        reservaLogic.updateReserva(entity);
    }

    public Reserva getReserva(Integer idReserva) throws Exception {
        Reserva reserva = null;

        try {
            reserva = reservaLogic.getReserva(idReserva);
        } catch (Exception e) {
            throw e;
        }

        return reserva;
    }

    public List<Reserva> findByCriteriaInReserva(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return reservaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Reserva> findPageReserva(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return reservaLogic.findPageReserva(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberReserva() throws Exception {
        return reservaLogic.findTotalNumberReserva();
    }

    public List<ReservaDTO> getDataReserva() throws Exception {
        return reservaLogic.getDataReserva();
    }

    public List<Rol> getRol() throws Exception {
        return rolLogic.getRol();
    }

    public void saveRol(Rol entity) throws Exception {
        rolLogic.saveRol(entity);
    }

    public void deleteRol(Rol entity) throws Exception {
        rolLogic.deleteRol(entity);
    }

    public void updateRol(Rol entity) throws Exception {
        rolLogic.updateRol(entity);
    }

    public Rol getRol(Integer idRol) throws Exception {
        Rol rol = null;

        try {
            rol = rolLogic.getRol(idRol);
        } catch (Exception e) {
            throw e;
        }

        return rol;
    }

    public List<Rol> findByCriteriaInRol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return rolLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Rol> findPageRol(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) throws Exception {
        return rolLogic.findPageRol(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberRol() throws Exception {
        return rolLogic.findTotalNumberRol();
    }

    public List<RolDTO> getDataRol() throws Exception {
        return rolLogic.getDataRol();
    }

    public List<Usuario> getUsuario() throws Exception {
        return usuarioLogic.getUsuario();
    }

    public void saveUsuario(Usuario entity) throws Exception {
        usuarioLogic.saveUsuario(entity);
    }

    public void deleteUsuario(Usuario entity) throws Exception {
        usuarioLogic.deleteUsuario(entity);
    }

    public void updateUsuario(Usuario entity) throws Exception {
        usuarioLogic.updateUsuario(entity);
    }

    public Usuario getUsuario(Integer idUsuario) throws Exception {
        Usuario usuario = null;

        try {
            usuario = usuarioLogic.getUsuario(idUsuario);
        } catch (Exception e) {
            throw e;
        }

        return usuario;
    }

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return usuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return usuarioLogic.findPageUsuario(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUsuario() throws Exception {
        return usuarioLogic.findTotalNumberUsuario();
    }

    public List<UsuarioDTO> getDataUsuario() throws Exception {
        return usuarioLogic.getDataUsuario();
    }
}
