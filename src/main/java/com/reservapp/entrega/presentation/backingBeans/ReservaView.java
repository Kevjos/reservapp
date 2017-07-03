package com.reservapp.entrega.presentation.backingBeans;

import com.reservapp.entrega.exceptions.*;
import com.reservapp.entrega.modelo.*;
import com.reservapp.entrega.modelo.dto.ReservaDTO;
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
public class ReservaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ReservaView.class);
    private InputText txtIdMesa;
    private InputText txtIdMesa_Mesa;
    private InputText txtIdUsuario_Usuario;
    private InputText txtIdReserva;
    private Calendar txtFechaReserva;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ReservaDTO> data;
    private ReservaDTO selectedReserva;
    private Reserva entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ReservaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ReservaDTO reservaDTO = (ReservaDTO) e.getObject();

            if (txtIdMesa == null) {
                txtIdMesa = new InputText();
            }

            txtIdMesa.setValue(reservaDTO.getIdMesa());

            if (txtIdMesa_Mesa == null) {
                txtIdMesa_Mesa = new InputText();
            }

            txtIdMesa_Mesa.setValue(reservaDTO.getIdMesa_Mesa());

            if (txtIdUsuario_Usuario == null) {
                txtIdUsuario_Usuario = new InputText();
            }

            txtIdUsuario_Usuario.setValue(reservaDTO.getIdUsuario_Usuario());

            if (txtIdReserva == null) {
                txtIdReserva = new InputText();
            }

            txtIdReserva.setValue(reservaDTO.getIdReserva());

            if (txtFechaReserva == null) {
                txtFechaReserva = new Calendar();
            }

            txtFechaReserva.setValue(reservaDTO.getFechaReserva());

            Integer idReserva = FacesUtils.checkInteger(txtIdReserva);
            entity = businessDelegatorView.getReserva(idReserva);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedReserva = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedReserva = null;

        if (txtIdMesa != null) {
            txtIdMesa.setValue(null);
            txtIdMesa.setDisabled(true);
        }

        if (txtIdMesa_Mesa != null) {
            txtIdMesa_Mesa.setValue(null);
            txtIdMesa_Mesa.setDisabled(true);
        }

        if (txtIdUsuario_Usuario != null) {
            txtIdUsuario_Usuario.setValue(null);
            txtIdUsuario_Usuario.setDisabled(true);
        }

        if (txtFechaReserva != null) {
            txtFechaReserva.setValue(null);
            txtFechaReserva.setDisabled(true);
        }

        if (txtIdReserva != null) {
            txtIdReserva.setValue(null);
            txtIdReserva.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaReserva() {
        Date inputDate = (Date) txtFechaReserva.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer idReserva = FacesUtils.checkInteger(txtIdReserva);
            entity = (idReserva != null)
                ? businessDelegatorView.getReserva(idReserva) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIdMesa.setDisabled(false);
            txtIdMesa_Mesa.setDisabled(false);
            txtIdUsuario_Usuario.setDisabled(false);
            txtFechaReserva.setDisabled(false);
            txtIdReserva.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtFechaReserva.setValue(entity.getFechaReserva());
            txtFechaReserva.setDisabled(false);
            txtIdMesa.setValue(entity.getIdMesa());
            txtIdMesa.setDisabled(false);
            txtIdMesa_Mesa.setValue(entity.getMesa().getIdMesa());
            txtIdMesa_Mesa.setDisabled(false);
            txtIdUsuario_Usuario.setValue(entity.getUsuario().getIdUsuario());
            txtIdUsuario_Usuario.setDisabled(false);
            txtIdReserva.setValue(entity.getIdReserva());
            txtIdReserva.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedReserva = (ReservaDTO) (evt.getComponent().getAttributes()
                                           .get("selectedReserva"));
        txtFechaReserva.setValue(selectedReserva.getFechaReserva());
        txtFechaReserva.setDisabled(false);
        txtIdMesa.setValue(selectedReserva.getIdMesa());
        txtIdMesa.setDisabled(false);
        txtIdMesa_Mesa.setValue(selectedReserva.getIdMesa_Mesa());
        txtIdMesa_Mesa.setDisabled(false);
        txtIdUsuario_Usuario.setValue(selectedReserva.getIdUsuario_Usuario());
        txtIdUsuario_Usuario.setDisabled(false);
        txtIdReserva.setValue(selectedReserva.getIdReserva());
        txtIdReserva.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedReserva == null) && (entity == null)) {
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
            entity = new Reserva();

            Integer idReserva = FacesUtils.checkInteger(txtIdReserva);

            entity.setFechaReserva(FacesUtils.checkDate(txtFechaReserva));
            entity.setIdMesa(FacesUtils.checkInteger(txtIdMesa));
            entity.setIdReserva(idReserva);
            entity.setMesa((FacesUtils.checkInteger(txtIdMesa_Mesa) != null)
                ? businessDelegatorView.getMesa(FacesUtils.checkInteger(
                        txtIdMesa_Mesa)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtIdUsuario_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtIdUsuario_Usuario)) : null);
            //entity.setMenus(FacesUtils.checkMenu(txtMenus));
            businessDelegatorView.saveReserva(entity);
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
                Integer idReserva = new Integer(selectedReserva.getIdReserva());
                entity = businessDelegatorView.getReserva(idReserva);
            }

            entity.setFechaReserva(FacesUtils.checkDate(txtFechaReserva));
            entity.setIdMesa(FacesUtils.checkInteger(txtIdMesa));
            entity.setMesa((FacesUtils.checkInteger(txtIdMesa_Mesa) != null)
                ? businessDelegatorView.getMesa(FacesUtils.checkInteger(
                        txtIdMesa_Mesa)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtIdUsuario_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtIdUsuario_Usuario)) : null);
            //entity.setMenus(FacesUtils.checkMenu(txtMenus));
            businessDelegatorView.updateReserva(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedReserva = (ReservaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedReserva"));

            Integer idReserva = new Integer(selectedReserva.getIdReserva());
            entity = businessDelegatorView.getReserva(idReserva);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idReserva = FacesUtils.checkInteger(txtIdReserva);
            entity = businessDelegatorView.getReserva(idReserva);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteReserva(entity);
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
            selectedReserva = (ReservaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedReserva"));

            Integer idReserva = new Integer(selectedReserva.getIdReserva());
            entity = businessDelegatorView.getReserva(idReserva);
            businessDelegatorView.deleteReserva(entity);
            data.remove(selectedReserva);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Date fechaReserva, Integer idMesa,
        Integer idReserva, Integer idMesa_Mesa, Integer idUsuario_Usuario)
        throws Exception {
        try {
            entity.setFechaReserva(FacesUtils.checkDate(fechaReserva));
            entity.setIdMesa(FacesUtils.checkInteger(idMesa));
            businessDelegatorView.updateReserva(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ReservaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtIdMesa() {
        return txtIdMesa;
    }

    public void setTxtIdMesa(InputText txtIdMesa) {
        this.txtIdMesa = txtIdMesa;
    }

    public InputText getTxtIdMesa_Mesa() {
        return txtIdMesa_Mesa;
    }

    public void setTxtIdMesa_Mesa(InputText txtIdMesa_Mesa) {
        this.txtIdMesa_Mesa = txtIdMesa_Mesa;
    }

    public InputText getTxtIdUsuario_Usuario() {
        return txtIdUsuario_Usuario;
    }

    public void setTxtIdUsuario_Usuario(InputText txtIdUsuario_Usuario) {
        this.txtIdUsuario_Usuario = txtIdUsuario_Usuario;
    }

    public Calendar getTxtFechaReserva() {
        return txtFechaReserva;
    }

    public void setTxtFechaReserva(Calendar txtFechaReserva) {
        this.txtFechaReserva = txtFechaReserva;
    }

    public InputText getTxtIdReserva() {
        return txtIdReserva;
    }

    public void setTxtIdReserva(InputText txtIdReserva) {
        this.txtIdReserva = txtIdReserva;
    }

    public List<ReservaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataReserva();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ReservaDTO> reservaDTO) {
        this.data = reservaDTO;
    }

    public ReservaDTO getSelectedReserva() {
        return selectedReserva;
    }

    public void setSelectedReserva(ReservaDTO reserva) {
        this.selectedReserva = reserva;
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
