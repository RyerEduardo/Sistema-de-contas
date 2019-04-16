/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exemplologin.controller;

import br.com.exemplologin.dao.ContaDAO;
import br.com.exemplologin.model.Conta;
import br.com.exemplologin.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Eduardo
 */
public class ContaController {

    private ContaDAO dao;
    private List<Conta> contas;
    private Conta conta;
    static Integer idUsuario;
    static String loginUsuario;
    public ContaController() {
        novo();
        dao = new ContaDAO();
        contas = ObservableCollections.observableList(new ArrayList<>());
        pesquisar();
    }

    public void pesquisar() {
        contas.clear();
        contas.addAll(dao.listar());
    }
    
    public void buscaContasPorMes(int mes){
        contas.clear();
        contas.addAll(dao.findConta(mes));
    }

    public void novo() {
        Conta conta = new Conta();
        Usuario usuario = new Usuario();
        
        usuario.setId(idUsuario); //ta vindo do usuario controller
        usuario.setLogin(loginUsuario);
        conta.setUsuario(usuario);
        setConta(conta);
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void salvarConta() {
        dao.create(conta);
        pesquisar();
        novo();
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}
