/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.NivelDao;
import com.modelo.Nivel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author crist
 */
@Named(value = "nivelBean")
@SessionScoped
public class NivelBean implements Serializable {

    private int idn;
     private String descripcion;
     private String catequista;
     private String celular;
    
    public NivelBean() {
    }

    public NivelBean(int idn, String descripcion, String catequista, String celular) {
        this.idn = idn;
        this.descripcion = descripcion;
        this.catequista = catequista;
        this.celular = celular;
    }

    public int getIdn() {
        return idn;
    }

    public void setIdn(int idn) {
        this.idn = idn;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCatequista() {
        return catequista;
    }

    public void setCatequista(String catequista) {
        this.catequista = catequista;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void addNivel(){
        Nivel nivel=new Nivel(getDescripcion(),getCatequista(),getCelular());
         NivelDao daoCategori=new NivelDao();
         daoCategori.crear(nivel); 
    }
    public void leer(){
         NivelDao daoCategori=new NivelDao();
        daoCategori.getNivel(idn);
        System.err.println(""+daoCategori.toString());
    }
    public void actualizar(){
        NivelDao daoCategori=new NivelDao();
                Nivel nivel=new Nivel(getDescripcion(),getCatequista(),getCelular());
                daoCategori.updateNivel(1, nivel); 
        
    }
    public void delete(){
           NivelDao daoCategori=new NivelDao();
            daoCategori.deleteNivel(1);
    }
    
}
