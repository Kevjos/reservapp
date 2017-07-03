package com.reservapp.entrega.presentation.backingBeans;

import com.reservapp.entrega.exceptions.*;
import com.reservapp.entrega.modelo.*;
import com.reservapp.entrega.modelo.dto.RolDTO;
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
public class RolView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RolView.class);
    private InputText txtNombre;
    private InputText txtIdRol;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RolDTO> data;
    private RolDTO selectedRol;
    private Rol entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RolView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            RolDTO rolDTO = (RolDTO) e.getObject();

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(rolDTO.getNombre());

            if (txtIdRol == null) {
                txtIdRol = new InputText();
            }

            txtIdRol.setValue(rolDTO.getIdRol());

            Integer idRol = FacesUtils.checkInteger(txtIdRol);
            entity = businessDelegatorView.getRol(idRol);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedRol = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRol = null;

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtIdRol != null) {
            txtIdRol.setValue(null);
            txtIdRol.setDisabled(false);
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
            Integer idRol = FacesUtils.checkInteger(txtIdRol);
            entity = (idRol != null) ? businessDelegatorView.getRol(idRol) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtNombre.setDisabled(false);
            txtIdRol.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtIdRol.setValue(entity.getIdRol());
            txtIdRol.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRol = (RolDTO) (evt.getComponent().getAttributes()
                                   .get("selectedRol"));
        txtNombre.setValue(selectedRol.getNombre());
        txtNombre.setDisabled(false);
        txtIdRol.setValue(selectedRol.getIdRol());
        txtIdRol.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRol == null) && (entity == null)) {
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
            entity = new Rol();

            Integer idRol = FacesUtils.checkInteger(txtIdRol);

            entity.setIdRol(idRol);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.saveRol(entity);
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
                Integer idRol = new Integer(selectedRol.getIdRol());
                entity = businessDelegatorView.getRol(idRol);
            }

            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updateRol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRol = (RolDTO) (evt.getComponent().getAttributes()
                                       .get("selectedRol"));

            Integer idRol = new Integer(selectedRol.getIdRol());
            entity = businessDelegatorView.getRol(idRol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idRol = FacesUtils.checkInteger(txtIdRol);
            entity = businessDelegatorView.getRol(idRol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRol(entity);
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
            selectedRol = (RolDTO) (evt.getComponent().getAttributes()
                                       .get("selectedRol"));

            Integer idRol = new Integer(selectedRol.getIdRol());
            entity = businessDelegatorView.getRol(idRol);
            businessDelegatorView.deleteRol(entity);
            data.remove(selectedRol);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer idRol, String nombre)
        throws Exception {
        try {
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updateRol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RolView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtIdRol() {
        return txtIdRol;
    }

    public void setTxtIdRol(InputText txtIdRol) {
        this.txtIdRol = txtIdRol;
    }

    public List<RolDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRol();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<RolDTO> rolDTO) {
        this.data = rolDTO;
    }

    public RolDTO getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedRol(RolDTO rol) {
        this.selectedRol = rol;
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
