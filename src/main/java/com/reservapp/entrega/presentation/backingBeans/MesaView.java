package com.reservapp.entrega.presentation.backingBeans;

import com.reservapp.entrega.exceptions.*;
import com.reservapp.entrega.modelo.*;
import com.reservapp.entrega.modelo.dto.MesaDTO;
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
public class MesaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MesaView.class);
    private InputText txtEstado;
    private InputText txtPuestos;
    private InputText txtUbicacion;
    private InputText txtIdEstablecimiento_Establecimiento;
    private InputText txtIdMesa;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MesaDTO> data;
    private MesaDTO selectedMesa;
    private Mesa entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MesaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MesaDTO mesaDTO = (MesaDTO) e.getObject();

            if (txtEstado == null) {
                txtEstado = new InputText();
            }

            txtEstado.setValue(mesaDTO.getEstado());

            if (txtPuestos == null) {
                txtPuestos = new InputText();
            }

            txtPuestos.setValue(mesaDTO.getPuestos());

            if (txtUbicacion == null) {
                txtUbicacion = new InputText();
            }

            txtUbicacion.setValue(mesaDTO.getUbicacion());

            if (txtIdEstablecimiento_Establecimiento == null) {
                txtIdEstablecimiento_Establecimiento = new InputText();
            }

            txtIdEstablecimiento_Establecimiento.setValue(mesaDTO.getIdEstablecimiento_Establecimiento());

            if (txtIdMesa == null) {
                txtIdMesa = new InputText();
            }

            txtIdMesa.setValue(mesaDTO.getIdMesa());

            Integer idMesa = FacesUtils.checkInteger(txtIdMesa);
            entity = businessDelegatorView.getMesa(idMesa);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMesa = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMesa = null;

        if (txtEstado != null) {
            txtEstado.setValue(null);
            txtEstado.setDisabled(true);
        }

        if (txtPuestos != null) {
            txtPuestos.setValue(null);
            txtPuestos.setDisabled(true);
        }

        if (txtUbicacion != null) {
            txtUbicacion.setValue(null);
            txtUbicacion.setDisabled(true);
        }

        if (txtIdEstablecimiento_Establecimiento != null) {
            txtIdEstablecimiento_Establecimiento.setValue(null);
            txtIdEstablecimiento_Establecimiento.setDisabled(true);
        }

        if (txtIdMesa != null) {
            txtIdMesa.setValue(null);
            txtIdMesa.setDisabled(false);
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
            Integer idMesa = FacesUtils.checkInteger(txtIdMesa);
            entity = (idMesa != null) ? businessDelegatorView.getMesa(idMesa)
                                      : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstado.setDisabled(false);
            txtPuestos.setDisabled(false);
            txtUbicacion.setDisabled(false);
            txtIdEstablecimiento_Establecimiento.setDisabled(false);
            txtIdMesa.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstado.setValue(entity.getEstado());
            txtEstado.setDisabled(false);
            txtPuestos.setValue(entity.getPuestos());
            txtPuestos.setDisabled(false);
            txtUbicacion.setValue(entity.getUbicacion());
            txtUbicacion.setDisabled(false);
            txtIdEstablecimiento_Establecimiento.setValue(entity.getEstablecimiento()
                                                                .getIdEstablecimiento());
            txtIdEstablecimiento_Establecimiento.setDisabled(false);
            txtIdMesa.setValue(entity.getIdMesa());
            txtIdMesa.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedMesa = (MesaDTO) (evt.getComponent().getAttributes()
                                     .get("selectedMesa"));
        txtEstado.setValue(selectedMesa.getEstado());
        txtEstado.setDisabled(false);
        txtPuestos.setValue(selectedMesa.getPuestos());
        txtPuestos.setDisabled(false);
        txtUbicacion.setValue(selectedMesa.getUbicacion());
        txtUbicacion.setDisabled(false);
        txtIdEstablecimiento_Establecimiento.setValue(selectedMesa.getIdEstablecimiento_Establecimiento());
        txtIdEstablecimiento_Establecimiento.setDisabled(false);
        txtIdMesa.setValue(selectedMesa.getIdMesa());
        txtIdMesa.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMesa == null) && (entity == null)) {
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
            entity = new Mesa();

            Integer idMesa = FacesUtils.checkInteger(txtIdMesa);

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setIdMesa(idMesa);
            entity.setPuestos(FacesUtils.checkInteger(txtPuestos));
            entity.setUbicacion(FacesUtils.checkString(txtUbicacion));
            entity.setEstablecimiento((FacesUtils.checkInteger(
                    txtIdEstablecimiento_Establecimiento) != null)
                ? businessDelegatorView.getEstablecimiento(
                    FacesUtils.checkInteger(
                        txtIdEstablecimiento_Establecimiento)) : null);
            businessDelegatorView.saveMesa(entity);
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
                Integer idMesa = new Integer(selectedMesa.getIdMesa());
                entity = businessDelegatorView.getMesa(idMesa);
            }

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setPuestos(FacesUtils.checkInteger(txtPuestos));
            entity.setUbicacion(FacesUtils.checkString(txtUbicacion));
            entity.setEstablecimiento((FacesUtils.checkInteger(
                    txtIdEstablecimiento_Establecimiento) != null)
                ? businessDelegatorView.getEstablecimiento(
                    FacesUtils.checkInteger(
                        txtIdEstablecimiento_Establecimiento)) : null);
            businessDelegatorView.updateMesa(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMesa = (MesaDTO) (evt.getComponent().getAttributes()
                                         .get("selectedMesa"));

            Integer idMesa = new Integer(selectedMesa.getIdMesa());
            entity = businessDelegatorView.getMesa(idMesa);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idMesa = FacesUtils.checkInteger(txtIdMesa);
            entity = businessDelegatorView.getMesa(idMesa);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMesa(entity);
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
            selectedMesa = (MesaDTO) (evt.getComponent().getAttributes()
                                         .get("selectedMesa"));

            Integer idMesa = new Integer(selectedMesa.getIdMesa());
            entity = businessDelegatorView.getMesa(idMesa);
            businessDelegatorView.deleteMesa(entity);
            data.remove(selectedMesa);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estado, Integer idMesa,
        Integer puestos, String ubicacion,
        Integer idEstablecimiento_Establecimiento) throws Exception {
        try {
            entity.setEstado(FacesUtils.checkString(estado));
            entity.setPuestos(FacesUtils.checkInteger(puestos));
            entity.setUbicacion(FacesUtils.checkString(ubicacion));
            businessDelegatorView.updateMesa(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MesaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstado() {
        return txtEstado;
    }

    public void setTxtEstado(InputText txtEstado) {
        this.txtEstado = txtEstado;
    }

    public InputText getTxtPuestos() {
        return txtPuestos;
    }

    public void setTxtPuestos(InputText txtPuestos) {
        this.txtPuestos = txtPuestos;
    }

    public InputText getTxtUbicacion() {
        return txtUbicacion;
    }

    public void setTxtUbicacion(InputText txtUbicacion) {
        this.txtUbicacion = txtUbicacion;
    }

    public InputText getTxtIdEstablecimiento_Establecimiento() {
        return txtIdEstablecimiento_Establecimiento;
    }

    public void setTxtIdEstablecimiento_Establecimiento(
        InputText txtIdEstablecimiento_Establecimiento) {
        this.txtIdEstablecimiento_Establecimiento = txtIdEstablecimiento_Establecimiento;
    }

    public InputText getTxtIdMesa() {
        return txtIdMesa;
    }

    public void setTxtIdMesa(InputText txtIdMesa) {
        this.txtIdMesa = txtIdMesa;
    }

    public List<MesaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMesa();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MesaDTO> mesaDTO) {
        this.data = mesaDTO;
    }

    public MesaDTO getSelectedMesa() {
        return selectedMesa;
    }

    public void setSelectedMesa(MesaDTO mesa) {
        this.selectedMesa = mesa;
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
