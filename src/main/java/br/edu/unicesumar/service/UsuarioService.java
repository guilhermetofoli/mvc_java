package br.edu.unicesumar.service;

import br.edu.unicesumar.dao.UsuarioDAO;
import br.edu.unicesumar.model.Usuario;
import br.edu.unicesumar.utils.Utils;


public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService(){
        usuarioDAO = new UsuarioDAO();
    }

    public void saveUsuario(Usuario usuario) {
        //VERIFICANDO SE O NOME NÃO ESTÁ VAZIO
        if(usuario.getNome() == null || usuario.getNome().isEmpty()){
            return;
        }

        //VERIFICANDO SE O CPF NÃO ESTÁ VAZIO
        if(usuario.getCpf() == null || usuario.getCpf().isEmpty()){
            return;
        }

        //NEGANDO CPF INVÁLIDO
        if(Utils.validarCPF(usuario.getCpf())){
            return;
        }

        usuarioDAO.save(usuario);
    }
    
}