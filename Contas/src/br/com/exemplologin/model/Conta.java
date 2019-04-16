/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exemplologin.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eduardo
 */
@Entity(name = "conta")
@NamedQueries({
    @NamedQuery(name = "conta.listarMes", query = "SELECT c FROM conta c, Usuario u WHERE EXTRACT(MONTH FROM c.dataCompra) = :mes and c.usuario.id=u.id and u.id = :id "),

    
    @NamedQuery(name = "conta.listar", query = "SELECT c FROM conta c, Usuario u WHERE c.usuario.id=u.id and u.id = :id ")

})
public class Conta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String titulo;
    private double valor;
    private boolean parcelado;
    private int vezes;
    @Temporal(TemporalType.DATE)
    private Calendar dataCompra;

    @ManyToOne
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public Conta(String titulo, double valor, boolean parcelado, int vezes, Calendar dataCompra) {
        this.titulo = titulo;
        this.valor = valor;
        this.parcelado = parcelado;
        this.vezes = vezes;
        this.dataCompra = dataCompra;
    }

    public Conta() {
        this.dataCompra = Calendar.getInstance();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isParcelado() {
        return parcelado;
    }

    public void setParcelado(boolean parcelado) {
        this.parcelado = parcelado;
    }

    public int getVezes() {
        return vezes;
    }

    public void setVezes(int vezes) {
        this.vezes = vezes;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

}
