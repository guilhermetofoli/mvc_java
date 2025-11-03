package br.edu.unicesumar.controller;

import java.lang.classfile.Label;

import br.edu.unicesumar.model.Usuario;
import br.edu.unicesumar.service.UsuarioService;
import javafx.fxml.FXML;

public class UsuarioController {

    @FXML
    private Label label;

    UsuarioService usuarioService = new UsuarioService();

    @FXML
    public void saveUsuario(){

        Usuario u = new Usuario();
        u.setNome("Anderson");
        u.setCpf("11111111");
        //u.setTipoUsuario(TipoUsuario.ADMIN);
        usuarioService.saveUsuario(u);
    }

}
