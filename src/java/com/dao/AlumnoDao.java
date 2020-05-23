/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.modelo.Alumno;
import com.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author crist
 */
public class AlumnoDao {
    public void crear(Alumno alumno){
        Transaction tx=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx=session.beginTransaction();
            session.save(alumno);
            session.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
               
    }
    
        public List<Alumno> getAllAlumnos() {
        Session session = null;
        List<Alumno> lista = null;
        session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Alumno");
        lista = (List<Alumno>) query.list();

        return lista;
    }


    
    public Alumno getAlumno(String cedula){
        Alumno alumno=null;
        Transaction tx=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx=session.beginTransaction();
            Query query=session.createQuery("from Alumno a where a.cedula = :idToFind");
            query.setString("idToFind",cedula);
            alumno=(Alumno)query.uniqueResult();
        }catch(Exception ex){
            ex.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
        return alumno;
    }
    
    public void updateCliente(int ide, Alumno newAlumno) {
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Alumno alumno = (Alumno) session.load(Alumno.class, new Integer(ide));

            alumno.setCedula(newAlumno.getCedula());
            alumno.setNombres(newAlumno.getNombres());
            alumno.setApellidos(newAlumno.getApellidos());
            alumno.setDireccion(newAlumno.getDireccion());
            alumno.setEdad(newAlumno.getEdad());
            alumno.setFechan(newAlumno.getFechan());
            
            alumno.setCedular(newAlumno.getCedular());           
            alumno.setNombresr(newAlumno.getNombresr());
            alumno.setApellidosr(newAlumno.getApellidosr());
            alumno.setCelular(newAlumno.getCelular());
            
            session.update(alumno);
            tx.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
    }
    
    public void deleteCliente(int ide){
        Alumno alumno=null;
        Transaction tx=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx=session.beginTransaction();
             alumno=(Alumno)session.load(Alumno.class,new Integer(ide));
             session.delete(alumno);
             session.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
    }
    
    
    
    
    public Alumno buscarPorCedula(String cedula){
          Alumno cliente=null;
          Transaction tx=null;
          Session session=NewHibernateUtil.getSessionFactory().openSession();
          tx=session.beginTransaction();
          Query query=session.createQuery("from Alumno a where a.cedula=:cedula");
          query.setParameter("cedula",cedula);
          cliente=(Alumno)query.uniqueResult();
          session.close();
          return cliente;
    }
    
    
    
    public List<Alumno> existeCedula(String cedula){
        
          List<Alumno>alumnos=new ArrayList<Alumno>();
          Transaction tx=null;
          Session session=NewHibernateUtil.getSessionFactory().openSession();
          tx=session.beginTransaction();
          Query query=session.createQuery("from Alumno a where a.cedula=:cedula");
          query.setParameter("cedula",cedula);
          alumnos=query.list();
          session.close();
          return alumnos;
        
    }
}
