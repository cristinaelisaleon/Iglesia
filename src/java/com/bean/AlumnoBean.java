/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.AlumnoDao;
import com.dao.NivelDao;
import com.modelo.Alumno;
import com.modelo.Nivel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.context.RequestContext;

/**
 *
 * @author crist
 */
@Named(value = "alumnoBean")
@SessionScoped
public class AlumnoBean implements Serializable {

    private int ide;
    private Nivel nivel;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private int edad;
    private Date fechan;
    private String cedular;
    private String nombresr;
    private String apellidosr;
    private String celular;
    private Alumno alumno;

    private String result = "confirmado";
    private String descripcion = "";

    public AlumnoBean() {
        alumno = new Alumno();
    }

    public AlumnoBean(int ide, Nivel nivel, String cedula, String nombres, String apellidos, String direccion, int edad, Date fechan, String cedular, String nombresr, String apellidosr, String celular) {
        this.ide = ide;
        this.nivel = nivel;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.edad = edad;
        this.fechan = fechan;
        this.cedular = cedular;
        this.nombresr = nombresr;
        this.apellidosr = apellidosr;
        this.celular = celular;
    }

    public AlumnoBean(int ide, String cedula, String nombres, String apellidos, String direccion, int edad, Date fechan, String cedular, String nombresr, String apellidosr, String celular) {
        this.ide = ide;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.edad = edad;
        this.fechan = fechan;
        this.cedular = cedular;
        this.nombresr = nombresr;
        this.apellidosr = apellidosr;
        this.celular = celular;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechan() {
        return fechan;
    }

    public void setFechan(Date fechan) {
        this.fechan = fechan;
    }

    public String getCedular() {
        return cedular;
    }

    public void setCedular(String cedular) {
        this.cedular = cedular;
    }

    public String getNombresr() {
        return nombresr;
    }

    public void setNombresr(String nombresr) {
        this.nombresr = nombresr;
    }

    public String getApellidosr() {
        return apellidosr;
    }

    public void setApellidosr(String apellidosr) {
        this.apellidosr = apellidosr;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String addAlumno() {
        System.err.println("*********************************************************************");
        String result = null;
        if (nombres.equalsIgnoreCase("") || apellidos.equalsIgnoreCase("") || direccion.equalsIgnoreCase("") || edad == 0 || fechan == null || cedular.equalsIgnoreCase("")
                || nombresr.equalsIgnoreCase("") || apellidosr.equalsIgnoreCase("") || celular.equalsIgnoreCase("")) {

            RequestContext.getCurrentInstance().execute("alert('Campos Vacios Existentes');");

            result = "nocreado";

        } else {
            NivelDao nivelDao = new NivelDao();
            Nivel nivel = nivelDao.getNivel(1);

            Alumno alumno = new Alumno(nivel,getCedula(), getNombres(), getApellidos(), getDireccion(), getEdad(), getFechan(), getCedular(),
                    getNombresr(), getApellidosr(), getCelular());
            AlumnoDao alumnoDao = new AlumnoDao();
            List<Alumno> alumnos = new ArrayList<Alumno>();

            AlumnoDao dao = new AlumnoDao();
            alumnos = dao.existeCedula(alumno.getCedula());
            if (alumnos.size() > 1) {
                RequestContext.getCurrentInstance().execute("alert('Alumno con esa cedula ya existe');");
            } else {
                alumnoDao.crear(alumno);
                RequestContext.getCurrentInstance().execute("alert('Alumno Registrado Exitosamente');");
                result = "creado";

            }

        }
        return result;

    }

    public String leer() {
        String result = "";

        AlumnoDao alumnoDao = new AlumnoDao();
        alumno = alumnoDao.getAlumno(cedula);
        if (alumno != null) {
            result = "correcto";
        }
        return result;

    }

    public String leer2() {
        String result = "";
        AlumnoDao alumnoDao = new AlumnoDao();
        alumno = alumnoDao.getAlumno(cedula);
        if (alumno != null) {
            result = "existe";
        }
        return result;
    }

    public void mostrar() {
        AlumnoDao alumnoDao = new AlumnoDao();
        Alumno alumno = alumnoDao.getAlumno(cedula);

    }

    public Alumno mostrar2() {
        AlumnoDao alumnoDao = new AlumnoDao();
        Alumno alumno = alumnoDao.getAlumno(cedula);
        return alumno;
    }

    public void actualizar() {

        AlumnoDao alumnoDao = new AlumnoDao();
        AlumnoBean d = new AlumnoBean();
        int id = alumnoDao.getAlumno(cedula).getIde();

        Alumno newCliente = new Alumno(alumno.getCedula(), alumno.getNombres(), alumno.getApellidos(), alumno.getDireccion(), alumno.getEdad(), alumno.getFechan(), alumno.getCedular(),
                alumno.getNombresr(), alumno.getApellidosr(), alumno.getCelular());

        alumnoDao.updateCliente(id, newCliente);

    }

    public String delete() {
        String result = "";
        AlumnoDao alumnoDao = new AlumnoDao();
        int id = alumnoDao.getAlumno(cedula).getIde();
        alumnoDao.deleteCliente(id);
        result = "borrado";
        return result;
    }

    public void buscarCedula() {
        AlumnoDao alumnoDao = new AlumnoDao();
        List<Alumno> alumnos = new ArrayList<Alumno>();
        alumnos = alumnoDao.existeCedula(alumno.getCedula());
        if (alumnos.size() > 1) {
            RequestContext.getCurrentInstance().execute("ya existe esta cedula ");

        } else {
            alumno = alumnoDao.buscarPorCedula(alumno.getCedula());

        }

    }
}
