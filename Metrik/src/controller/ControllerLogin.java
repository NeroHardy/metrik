package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;



import model.Usuario;
import model.DAOUsuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;


public class ControllerLogin {
	
/*	@FXML TextField txtUsuario;
	@FXML TextField txtPassword;*/
	@FXML JFXTextField txtUsuario;
	@FXML JFXPasswordField txtPassword;


	private DAOUsuarios daoUsuario;
	private model.Usuario usuario;
	private ControladorVentanas ventanas;
	Alert mensaje = new Alert(AlertType.NONE);
	private ControllerMenu cmenu;
	public ControllerLogin(){
		daoUsuario=new DAOUsuarios();
		ventanas=ControladorVentanas.getInstancia();
		cmenu=new ControllerMenu();
	}
	public void Advertencias(String titulo, String header, String content) {
		mensaje.setAlertType(AlertType.WARNING);
		mensaje.setTitle(titulo);
		mensaje.setHeaderText(header);
		mensaje.setContentText(content);
		mensaje.show();
	}

	@FXML public void clickAcceso() throws InstantiationException, IllegalAccessException{
		
		if(txtUsuario.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()){
			Advertencias("ADVERTENCIA", null, "Todos los Campos son Obligatorios.");
		}
		else{
			usuario= new Usuario();
			usuario.setUsuario(txtUsuario.getText());
			usuario.setPassword(txtPassword.getText());
			usuario=daoUsuario.validarUsuario(usuario);
			if(usuario==null){
				mensaje.setAlertType(AlertType.ERROR);
				mensaje.setTitle("Error al ingresar");
				mensaje.setHeaderText(null);
				mensaje.setContentText("Usuario y/o Contraseņa incorrectos");
				mensaje.show();
				txtPassword.clear();
				txtUsuario.clear();
			}
			else{
				switch (usuario.getTipo_usuario()) {
				case "Administrador":
					ventanas.asignarMenu("../views/fxml/main.fxml", "main");
					break;
				case "Lider":
					ventanas.asignarMenu("../views/fxml/main.fxml", "main");
					//cmenu.buttons("Lider");
					ControllerMenu.class.newInstance().buttons("Lider");
					break;
				case "Miembro":
					ventanas.asignarMenu("../views/fxml/main.fxml", "main");
					break;
				}
			
			}
		}
	}
	
	public void clickSalir(){
		txtUsuario.clear();
		System.exit(0);
		
	}
}