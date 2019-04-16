package br.com.exemplologin.controller;

import br.com.exemplologin.util.CriptografiaUtil;
import br.com.exemplologin.dao.UsuarioDAO;
import br.com.exemplologin.model.SessaoUsuario;
import br.com.exemplologin.model.Usuario;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rgarcia
 */
public class UsuarioController {
    private UsuarioDAO dao;
    private Usuario usuario;
    private List<Usuario> usuarios;
    private SessaoUsuario sessao;
    
    static int id;
    
    public UsuarioController() {
        usuario = new Usuario();
        dao = new UsuarioDAO();
        usuarios = ObservableCollections.observableList(new ArrayList<>());
        pesquisar();
    }
    
    private void pesquisar(){
        usuarios.clear();
        usuarios.addAll(dao.findUsuarioEntities());
    }
    
    public void salvar() throws NoSuchAlgorithmException{
        encriptografarSenhaUsuario();
        dao.create(usuario);
        pesquisar();
    }
    public void vinculaUsuario(SessaoUsuario sessao){  //passa os atributos para conta controller
        ContaController.idUsuario = sessao.getId();
        ContaController.loginUsuario = sessao.getLogin();
    }
 
    public boolean efetuarLogin() throws NoSuchAlgorithmException{
        encriptografarSenhaUsuario();
        if(dao.efetuarLogin(usuario) != null){
            id=dao.getId(usuario);
            sessao = new SessaoUsuario(dao.getId(usuario), usuario.getLogin());
            try{
                vinculaUsuario(sessao); //tenta vincular usuario
            }catch(Exception e){}
            return true;
        }
        else return false;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    private void encriptografarSenhaUsuario() throws NoSuchAlgorithmException {
        usuario.setSenha(CriptografiaUtil.encriptografarSenha(usuario.getSenha()));
    }
    
    public void cancelar(){
        usuario = new Usuario();
    }

    public SessaoUsuario getSessao() {
        return sessao;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UsuarioController.id = id;
    }
    
    
}
