package com.reservapp.entrega.presentation.backingBeans;

import com.reservapp.entrega.exceptions.*;
import com.reservapp.entrega.modelo.*;
import com.reservapp.entrega.modelo.dto.UsuarioDTO;
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
public class UsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuarioView.class);
    private InputText txtApellido;
    private InputText txtContrasena;
    private InputText txtCorreo;
    private InputText txtDireccion;
    private InputText txtNombre;
    private InputText txtRol_1;
    private InputText txtSexo;
    private InputText txtTelefono;
    private InputText txtIdRol_Rol;
    private InputText txtIdUsuario;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsuarioDTO> data;
    private UsuarioDTO selectedUsuario;
    private Usuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsuarioView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UsuarioDTO usuarioDTO = (UsuarioDTO) e.getObject();

            if (txtApellido == null) {
                txtApellido = new InputText();
            }

            txtApellido.setValue(usuarioDTO.getApellido());

            if (txtContrasena == null) {
                txtContrasena = new InputText();
            }

            txtContrasena.setValue(usuarioDTO.getContrasena());

            if (txtCorreo == null) {
                txtCorreo = new InputText();
            }

            txtCorreo.setValue(usuarioDTO.getCorreo());

            if (txtDireccion == null) {
                txtDireccion = new InputText();
            }

            txtDireccion.setValue(usuarioDTO.getDireccion());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(usuarioDTO.getNombre());

            if (txtRol_1 == null) {
                txtRol_1 = new InputText();
            }

            txtRol_1.setValue(usuarioDTO.getRol_1());

            if (txtSexo == null) {
                txtSexo = new InputText();
            }

            txtSexo.setValue(usuarioDTO.getSexo());

            if (txtTelefono == null) {
                txtTelefono = new InputText();
            }

            txtTelefono.setValue(usuarioDTO.getTelefono());

            if (txtIdRol_Rol == null) {
                txtIdRol_Rol = new InputText();
            }

            txtIdRol_Rol.setValue(usuarioDTO.getIdRol_Rol());

            if (txtIdUsuario == null) {
                txtIdUsuario = new InputText();
            }

            txtIdUsuario.setValue(usuarioDTO.getIdUsuario());

            Integer idUsuario = FacesUtils.checkInteger(txtIdUsuario);
            entity = businessDelegatorView.getUsuario(idUsuario);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUsuario = null;

        if (txtApellido != null) {
            txtApellido.setValue(null);
            txtApellido.setDisabled(true);
        }

        if (txtContrasena != null) {
            txtContrasena.setValue(null);
            txtContrasena.setDisabled(true);
        }

        if (txtCorreo != null) {
            txtCorreo.setValue(null);
            txtCorreo.setDisabled(true);
        }

        if (txtDireccion != null) {
            txtDireccion.setValue(null);
            txtDireccion.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtRol_1 != null) {
            txtRol_1.setValue(null);
            txtRol_1.setDisabled(true);
        }

        if (txtSexo != null) {
            txtSexo.setValue(null);
            txtSexo.setDisabled(true);
        }

        if (txtTelefono != null) {
            txtTelefono.setValue(null);
            txtTelefono.setDisabled(true);
        }

        if (txtIdRol_Rol != null) {
            txtIdRol_Rol.setValue(null);
            txtIdRol_Rol.setDisabled(true);
        }

        if (txtIdUsuario != null) {
            txtIdUsuario.setValue(null);
            txtIdUsuario.setDisabled(false);
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
            Integer idUsuario = FacesUtils.checkInteger(txtIdUsuario);
            entity = (idUsuario != null)
                ? businessDelegatorView.getUsuario(idUsuario) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtApellido.setDisabled(false);
            txtContrasena.setDisabled(false);
            txtCorreo.setDisabled(false);
            txtDireccion.setDisabled(false);
            txtNombre.setDisabled(false);
            txtRol_1.setDisabled(false);
            txtSexo.setDisabled(false);
            txtTelefono.setDisabled(false);
            txtIdRol_Rol.setDisabled(false);
            txtIdUsuario.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtApellido.setValue(entity.getApellido());
            txtApellido.setDisabled(false);
            txtContrasena.setValue(entity.getContrasena());
            txtContrasena.setDisabled(false);
            txtCorreo.setValue(entity.getCorreo());
            txtCorreo.setDisabled(false);
            txtDireccion.setValue(entity.getDireccion());
            txtDireccion.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtRol_1.setValue(entity.getRol_1());
            txtRol_1.setDisabled(false);
            txtSexo.setValue(entity.getSexo());
            txtSexo.setDisabled(false);
            txtTelefono.setValue(entity.getTelefono());
            txtTelefono.setDisabled(false);
            txtIdRol_Rol.setValue(entity.getRol().getIdRol());
            txtIdRol_Rol.setDisabled(false);
            txtIdUsuario.setValue(entity.getIdUsuario());
            txtIdUsuario.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                           .get("selectedUsuario"));
        txtApellido.setValue(selectedUsuario.getApellido());
        txtApellido.setDisabled(false);
        txtContrasena.setValue(selectedUsuario.getContrasena());
        txtContrasena.setDisabled(false);
        txtCorreo.setValue(selectedUsuario.getCorreo());
        txtCorreo.setDisabled(false);
        txtDireccion.setValue(selectedUsuario.getDireccion());
        txtDireccion.setDisabled(false);
        txtNombre.setValue(selectedUsuario.getNombre());
        txtNombre.setDisabled(false);
        txtRol_1.setValue(selectedUsuario.getRol_1());
        txtRol_1.setDisabled(false);
        txtSexo.setValue(selectedUsuario.getSexo());
        txtSexo.setDisabled(false);
        txtTelefono.setValue(selectedUsuario.getTelefono());
        txtTelefono.setDisabled(false);
        txtIdRol_Rol.setValue(selectedUsuario.getIdRol_Rol());
        txtIdRol_Rol.setDisabled(false);
        txtIdUsuario.setValue(selectedUsuario.getIdUsuario());
        txtIdUsuario.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsuario == null) && (entity == null)) {
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
            entity = new Usuario();

            Integer idUsuario = FacesUtils.checkInteger(txtIdUsuario);

            entity.setApellido(FacesUtils.checkString(txtApellido));
            entity.setContrasena(FacesUtils.checkString(txtContrasena));
            entity.setCorreo(FacesUtils.checkString(txtCorreo));
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            entity.setIdUsuario(idUsuario);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setRol_1(FacesUtils.checkString(txtRol_1));
            entity.setSexo(FacesUtils.checkString(txtSexo));
            entity.setTelefono(FacesUtils.checkString(txtTelefono));
            entity.setRol((FacesUtils.checkInteger(txtIdRol_Rol) != null)
                ? businessDelegatorView.getRol(FacesUtils.checkInteger(
                        txtIdRol_Rol)) : null);
            businessDelegatorView.saveUsuario(entity);
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
                Integer idUsuario = new Integer(selectedUsuario.getIdUsuario());
                entity = businessDelegatorView.getUsuario(idUsuario);
            }

            entity.setApellido(FacesUtils.checkString(txtApellido));
            entity.setContrasena(FacesUtils.checkString(txtContrasena));
            entity.setCorreo(FacesUtils.checkString(txtCorreo));
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setRol_1(FacesUtils.checkString(txtRol_1));
            entity.setSexo(FacesUtils.checkString(txtSexo));
            entity.setTelefono(FacesUtils.checkString(txtTelefono));
            entity.setRol((FacesUtils.checkInteger(txtIdRol_Rol) != null)
                ? businessDelegatorView.getRol(FacesUtils.checkInteger(
                        txtIdRol_Rol)) : null);
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                               .get("selectedUsuario"));

            Integer idUsuario = new Integer(selectedUsuario.getIdUsuario());
            entity = businessDelegatorView.getUsuario(idUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idUsuario = FacesUtils.checkInteger(txtIdUsuario);
            entity = businessDelegatorView.getUsuario(idUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsuario(entity);
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
            selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                               .get("selectedUsuario"));

            Integer idUsuario = new Integer(selectedUsuario.getIdUsuario());
            entity = businessDelegatorView.getUsuario(idUsuario);
            businessDelegatorView.deleteUsuario(entity);
            data.remove(selectedUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String apellido, String contrasena,
        String correo, String direccion, Integer idUsuario, String nombre,
        String rol_1, String sexo, String telefono, Integer idRol_Rol)
        throws Exception {
        try {
            entity.setApellido(FacesUtils.checkString(apellido));
            entity.setContrasena(FacesUtils.checkString(contrasena));
            entity.setCorreo(FacesUtils.checkString(correo));
            entity.setDireccion(FacesUtils.checkString(direccion));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setRol_1(FacesUtils.checkString(rol_1));
            entity.setSexo(FacesUtils.checkString(sexo));
            entity.setTelefono(FacesUtils.checkString(telefono));
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsuarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(InputText txtApellido) {
        this.txtApellido = txtApellido;
    }

    public InputText getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(InputText txtContrasena) {
        this.txtContrasena = txtContrasena;
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

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtRol_1() {
        return txtRol_1;
    }

    public void setTxtRol_1(InputText txtRol_1) {
        this.txtRol_1 = txtRol_1;
    }

    public InputText getTxtSexo() {
        return txtSexo;
    }

    public void setTxtSexo(InputText txtSexo) {
        this.txtSexo = txtSexo;
    }

    public InputText getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(InputText txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public InputText getTxtIdRol_Rol() {
        return txtIdRol_Rol;
    }

    public void setTxtIdRol_Rol(InputText txtIdRol_Rol) {
        this.txtIdRol_Rol = txtIdRol_Rol;
    }

    public InputText getTxtIdUsuario() {
        return txtIdUsuario;
    }

    public void setTxtIdUsuario(InputText txtIdUsuario) {
        this.txtIdUsuario = txtIdUsuario;
    }

    public List<UsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsuarioDTO> usuarioDTO) {
        this.data = usuarioDTO;
    }

    public UsuarioDTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioDTO usuario) {
        this.selectedUsuario = usuario;
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
