package com.reservapp.entrega.presentation.backingBeans;

import com.reservapp.entrega.exceptions.*;
import com.reservapp.entrega.modelo.*;
import com.reservapp.entrega.modelo.dto.EstablecimientoDTO;
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
public class EstablecimientoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EstablecimientoView.class);
    private InputText txtCorreo;
    private InputText txtDireccion;
    private InputText txtLatitud;
    private InputText txtLongitud;
    private InputText txtMesas;
    private InputText txtNit;
    private InputText txtNombre;
    private InputText txtTelefono;
    private InputText txtIdEstablecimiento;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<EstablecimientoDTO> data;
    private EstablecimientoDTO selectedEstablecimiento;
    private Establecimiento entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public EstablecimientoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            EstablecimientoDTO establecimientoDTO = (EstablecimientoDTO) e.getObject();

            if (txtCorreo == null) {
                txtCorreo = new InputText();
            }

            txtCorreo.setValue(establecimientoDTO.getCorreo());

            if (txtDireccion == null) {
                txtDireccion = new InputText();
            }

            txtDireccion.setValue(establecimientoDTO.getDireccion());

            if (txtLatitud == null) {
                txtLatitud = new InputText();
            }

            txtLatitud.setValue(establecimientoDTO.getLatitud());

            if (txtLongitud == null) {
                txtLongitud = new InputText();
            }

            txtLongitud.setValue(establecimientoDTO.getLongitud());

            if (txtMesas == null) {
                txtMesas = new InputText();
            }

            txtMesas.setValue(establecimientoDTO.getMesas());

            if (txtNit == null) {
                txtNit = new InputText();
            }

            txtNit.setValue(establecimientoDTO.getNit());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(establecimientoDTO.getNombre());

            if (txtTelefono == null) {
                txtTelefono = new InputText();
            }

            txtTelefono.setValue(establecimientoDTO.getTelefono());

            if (txtIdEstablecimiento == null) {
                txtIdEstablecimiento = new InputText();
            }

            txtIdEstablecimiento.setValue(establecimientoDTO.getIdEstablecimiento());

            Integer idEstablecimiento = FacesUtils.checkInteger(txtIdEstablecimiento);
            entity = businessDelegatorView.getEstablecimiento(idEstablecimiento);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedEstablecimiento = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedEstablecimiento = null;

        if (txtCorreo != null) {
            txtCorreo.setValue(null);
            txtCorreo.setDisabled(true);
        }

        if (txtDireccion != null) {
            txtDireccion.setValue(null);
            txtDireccion.setDisabled(true);
        }

        if (txtLatitud != null) {
            txtLatitud.setValue(null);
            txtLatitud.setDisabled(true);
        }

        if (txtLongitud != null) {
            txtLongitud.setValue(null);
            txtLongitud.setDisabled(true);
        }

        if (txtMesas != null) {
            txtMesas.setValue(null);
            txtMesas.setDisabled(true);
        }

        if (txtNit != null) {
            txtNit.setValue(null);
            txtNit.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtTelefono != null) {
            txtTelefono.setValue(null);
            txtTelefono.setDisabled(true);
        }

        if (txtIdEstablecimiento != null) {
            txtIdEstablecimiento.setValue(null);
            txtIdEstablecimiento.setDisabled(false);
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
            Integer idEstablecimiento = FacesUtils.checkInteger(txtIdEstablecimiento);
            entity = (idEstablecimiento != null)
                ? businessDelegatorView.getEstablecimiento(idEstablecimiento)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCorreo.setDisabled(false);
            txtDireccion.setDisabled(false);
            txtLatitud.setDisabled(false);
            txtLongitud.setDisabled(false);
            txtMesas.setDisabled(false);
            txtNit.setDisabled(false);
            txtNombre.setDisabled(false);
            txtTelefono.setDisabled(false);
            txtIdEstablecimiento.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCorreo.setValue(entity.getCorreo());
            txtCorreo.setDisabled(false);
            txtDireccion.setValue(entity.getDireccion());
            txtDireccion.setDisabled(false);
            txtLatitud.setValue(entity.getLatitud());
            txtLatitud.setDisabled(false);
            txtLongitud.setValue(entity.getLongitud());
            txtLongitud.setDisabled(false);
            txtMesas.setValue(entity.getMesas());
            txtMesas.setDisabled(false);
            txtNit.setValue(entity.getNit());
            txtNit.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtTelefono.setValue(entity.getTelefono());
            txtTelefono.setDisabled(false);
            txtIdEstablecimiento.setValue(entity.getIdEstablecimiento());
            txtIdEstablecimiento.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedEstablecimiento = (EstablecimientoDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedEstablecimiento"));
        txtCorreo.setValue(selectedEstablecimiento.getCorreo());
        txtCorreo.setDisabled(false);
        txtDireccion.setValue(selectedEstablecimiento.getDireccion());
        txtDireccion.setDisabled(false);
        txtLatitud.setValue(selectedEstablecimiento.getLatitud());
        txtLatitud.setDisabled(false);
        txtLongitud.setValue(selectedEstablecimiento.getLongitud());
        txtLongitud.setDisabled(false);
        txtMesas.setValue(selectedEstablecimiento.getMesas());
        txtMesas.setDisabled(false);
        txtNit.setValue(selectedEstablecimiento.getNit());
        txtNit.setDisabled(false);
        txtNombre.setValue(selectedEstablecimiento.getNombre());
        txtNombre.setDisabled(false);
        txtTelefono.setValue(selectedEstablecimiento.getTelefono());
        txtTelefono.setDisabled(false);
        txtIdEstablecimiento.setValue(selectedEstablecimiento.getIdEstablecimiento());
        txtIdEstablecimiento.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedEstablecimiento == null) && (entity == null)) {
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
            entity = new Establecimiento();

            Integer idEstablecimiento = FacesUtils.checkInteger(txtIdEstablecimiento);

            entity.setCorreo(FacesUtils.checkString(txtCorreo));
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            entity.setIdEstablecimiento(idEstablecimiento);
            entity.setLatitud(FacesUtils.checkString(txtLatitud));
            entity.setLongitud(FacesUtils.checkString(txtLongitud));
            entity.setMesas(FacesUtils.checkInteger(txtMesas));
            entity.setNit(FacesUtils.checkString(txtNit));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setTelefono(FacesUtils.checkString(txtTelefono));
            businessDelegatorView.saveEstablecimiento(entity);
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
                Integer idEstablecimiento = new Integer(selectedEstablecimiento.getIdEstablecimiento());
                entity = businessDelegatorView.getEstablecimiento(idEstablecimiento);
            }

            entity.setCorreo(FacesUtils.checkString(txtCorreo));
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            entity.setLatitud(FacesUtils.checkString(txtLatitud));
            entity.setLongitud(FacesUtils.checkString(txtLongitud));
            entity.setMesas(FacesUtils.checkInteger(txtMesas));
            entity.setNit(FacesUtils.checkString(txtNit));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setTelefono(FacesUtils.checkString(txtTelefono));
            businessDelegatorView.updateEstablecimiento(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedEstablecimiento = (EstablecimientoDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedEstablecimiento"));

            Integer idEstablecimiento = new Integer(selectedEstablecimiento.getIdEstablecimiento());
            entity = businessDelegatorView.getEstablecimiento(idEstablecimiento);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idEstablecimiento = FacesUtils.checkInteger(txtIdEstablecimiento);
            entity = businessDelegatorView.getEstablecimiento(idEstablecimiento);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteEstablecimiento(entity);
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
            selectedEstablecimiento = (EstablecimientoDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedEstablecimiento"));

            Integer idEstablecimiento = new Integer(selectedEstablecimiento.getIdEstablecimiento());
            entity = businessDelegatorView.getEstablecimiento(idEstablecimiento);
            businessDelegatorView.deleteEstablecimiento(entity);
            data.remove(selectedEstablecimiento);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String correo, String direccion,
        Integer idEstablecimiento, String latitud, String longitud,
        Integer mesas, String nit, String nombre, String telefono)
        throws Exception {
        try {
            entity.setCorreo(FacesUtils.checkString(correo));
            entity.setDireccion(FacesUtils.checkString(direccion));
            entity.setLatitud(FacesUtils.checkString(latitud));
            entity.setLongitud(FacesUtils.checkString(longitud));
            entity.setMesas(FacesUtils.checkInteger(mesas));
            entity.setNit(FacesUtils.checkString(nit));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setTelefono(FacesUtils.checkString(telefono));
            businessDelegatorView.updateEstablecimiento(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("EstablecimientoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtCorreo(InputText txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public InputText getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(InputText txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public InputText getTxtLatitud() {
        return txtLatitud;
    }

    public void setTxtLatitud(InputText txtLatitud) {
        this.txtLatitud = txtLatitud;
    }

    public InputText getTxtLongitud() {
        return txtLongitud;
    }

    public void setTxtLongitud(InputText txtLongitud) {
        this.txtLongitud = txtLongitud;
    }

    public InputText getTxtMesas() {
        return txtMesas;
    }

    public void setTxtMesas(InputText txtMesas) {
        this.txtMesas = txtMesas;
    }

    public InputText getTxtNit() {
        return txtNit;
    }

    public void setTxtNit(InputText txtNit) {
        this.txtNit = txtNit;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(InputText txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public InputText getTxtIdEstablecimiento() {
        return txtIdEstablecimiento;
    }

    public void setTxtIdEstablecimiento(InputText txtIdEstablecimiento) {
        this.txtIdEstablecimiento = txtIdEstablecimiento;
    }

    public List<EstablecimientoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataEstablecimiento();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<EstablecimientoDTO> establecimientoDTO) {
        this.data = establecimientoDTO;
    }

    public EstablecimientoDTO getSelectedEstablecimiento() {
        return selectedEstablecimiento;
    }

    public void setSelectedEstablecimiento(EstablecimientoDTO establecimiento) {
        this.selectedEstablecimiento = establecimiento;
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
