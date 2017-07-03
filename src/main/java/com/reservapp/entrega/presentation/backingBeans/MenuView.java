package com.reservapp.entrega.presentation.backingBeans;

import com.reservapp.entrega.exceptions.*;
import com.reservapp.entrega.modelo.*;
import com.reservapp.entrega.modelo.dto.MenuDTO;
import com.reservapp.entrega.presentation.businessDelegate.*;
import com.reservapp.entrega.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class MenuView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MenuView.class);
    private InputText txtDescripcion;
    private InputText txtImagen;
    private InputText txtNombre;
    private InputText txtPrecio;
    private InputText txtIdEstablecimiento_Establecimiento;
    private InputText txtIdMenu;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MenuDTO> data;
    private MenuDTO selectedMenu;
    private Menu entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MenuView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MenuDTO menuDTO = (MenuDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(menuDTO.getDescripcion());

            if (txtImagen == null) {
                txtImagen = new InputText();
            }

            txtImagen.setValue(menuDTO.getImagen());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(menuDTO.getNombre());

            if (txtPrecio == null) {
                txtPrecio = new InputText();
            }

            txtPrecio.setValue(menuDTO.getPrecio());

            if (txtIdEstablecimiento_Establecimiento == null) {
                txtIdEstablecimiento_Establecimiento = new InputText();
            }

            txtIdEstablecimiento_Establecimiento.setValue(menuDTO.getIdEstablecimiento_Establecimiento());

            if (txtIdMenu == null) {
                txtIdMenu = new InputText();
            }

            txtIdMenu.setValue(menuDTO.getIdMenu());

            Integer idMenu = FacesUtils.checkInteger(txtIdMenu);
            entity = businessDelegatorView.getMenu(idMenu);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMenu = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMenu = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtImagen != null) {
            txtImagen.setValue(null);
            txtImagen.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtPrecio != null) {
            txtPrecio.setValue(null);
            txtPrecio.setDisabled(true);
        }

        if (txtIdEstablecimiento_Establecimiento != null) {
            txtIdEstablecimiento_Establecimiento.setValue(null);
            txtIdEstablecimiento_Establecimiento.setDisabled(true);
        }

        if (txtIdMenu != null) {
            txtIdMenu.setValue(null);
            txtIdMenu.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Integer idMenu = FacesUtils.checkInteger(txtIdMenu);
            entity = (idMenu != null) ? businessDelegatorView.getMenu(idMenu)
                                      : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtImagen.setDisabled(false);
            txtNombre.setDisabled(false);
            txtPrecio.setDisabled(false);
            txtIdEstablecimiento_Establecimiento.setDisabled(false);
            txtIdMenu.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtImagen.setValue(entity.getImagen());
            txtImagen.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPrecio.setValue(entity.getPrecio());
            txtPrecio.setDisabled(false);
            txtIdEstablecimiento_Establecimiento.setValue(entity.getEstablecimiento()
                                                                .getIdEstablecimiento());
            txtIdEstablecimiento_Establecimiento.setDisabled(false);
            txtIdMenu.setValue(entity.getIdMenu());
            txtIdMenu.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedMenu = (MenuDTO) (evt.getComponent().getAttributes()
                                     .get("selectedMenu"));
        txtDescripcion.setValue(selectedMenu.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtImagen.setValue(selectedMenu.getImagen());
        txtImagen.setDisabled(false);
        txtNombre.setValue(selectedMenu.getNombre());
        txtNombre.setDisabled(false);
        txtPrecio.setValue(selectedMenu.getPrecio());
        txtPrecio.setDisabled(false);
        txtIdEstablecimiento_Establecimiento.setValue(selectedMenu.getIdEstablecimiento_Establecimiento());
        txtIdEstablecimiento_Establecimiento.setDisabled(false);
        txtIdMenu.setValue(selectedMenu.getIdMenu());
        txtIdMenu.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMenu == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new Menu();

            Integer idMenu = FacesUtils.checkInteger(txtIdMenu);

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setIdMenu(idMenu);
            entity.setImagen(FacesUtils.checkString(txtImagen));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPrecio(FacesUtils.checkInteger(txtPrecio));
            entity.setEstablecimiento((FacesUtils.checkInteger(
                    txtIdEstablecimiento_Establecimiento) != null)
                ? businessDelegatorView.getEstablecimiento(
                    FacesUtils.checkInteger(
                        txtIdEstablecimiento_Establecimiento)) : null);
            //entity.setReservas(FacesUtils.checkReserva(txtReservas));
            businessDelegatorView.saveMenu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Integer idMenu = new Integer(selectedMenu.getIdMenu());
                entity = businessDelegatorView.getMenu(idMenu);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setImagen(FacesUtils.checkString(txtImagen));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPrecio(FacesUtils.checkInteger(txtPrecio));
            entity.setEstablecimiento((FacesUtils.checkInteger(
                    txtIdEstablecimiento_Establecimiento) != null)
                ? businessDelegatorView.getEstablecimiento(
                    FacesUtils.checkInteger(
                        txtIdEstablecimiento_Establecimiento)) : null);
            //entity.setReservas(FacesUtils.checkReserva(txtReservas));
            businessDelegatorView.updateMenu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMenu = (MenuDTO) (evt.getComponent().getAttributes()
                                         .get("selectedMenu"));

            Integer idMenu = new Integer(selectedMenu.getIdMenu());
            entity = businessDelegatorView.getMenu(idMenu);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idMenu = FacesUtils.checkInteger(txtIdMenu);
            entity = businessDelegatorView.getMenu(idMenu);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMenu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedMenu = (MenuDTO) (evt.getComponent().getAttributes()
                                         .get("selectedMenu"));

            Integer idMenu = new Integer(selectedMenu.getIdMenu());
            entity = businessDelegatorView.getMenu(idMenu);
            businessDelegatorView.deleteMenu(entity);
            data.remove(selectedMenu);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcion, Integer idMenu,
        String imagen, String nombre, Integer precio,
        Integer idEstablecimiento_Establecimiento) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setImagen(FacesUtils.checkString(imagen));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setPrecio(FacesUtils.checkInteger(precio));
            businessDelegatorView.updateMenu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MenuView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtImagen() {
        return txtImagen;
    }

    public void setTxtImagen(InputText txtImagen) {
        this.txtImagen = txtImagen;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(InputText txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public InputText getTxtIdEstablecimiento_Establecimiento() {
        return txtIdEstablecimiento_Establecimiento;
    }

    public void setTxtIdEstablecimiento_Establecimiento(
        InputText txtIdEstablecimiento_Establecimiento) {
        this.txtIdEstablecimiento_Establecimiento = txtIdEstablecimiento_Establecimiento;
    }

    public InputText getTxtIdMenu() {
        return txtIdMenu;
    }

    public void setTxtIdMenu(InputText txtIdMenu) {
        this.txtIdMenu = txtIdMenu;
    }

    public List<MenuDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMenu();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MenuDTO> menuDTO) {
        this.data = menuDTO;
    }

    public MenuDTO getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(MenuDTO menu) {
        this.selectedMenu = menu;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
