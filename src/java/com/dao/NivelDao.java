/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.modelo.Nivel;
import com.util.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Meg@_Trom
 */
public class NivelDao {
    public void crear(Nivel nivel){
        Transaction tx=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx=session.beginTransaction();
            session.save(nivel);
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
    public Nivel getNivel(int idn){
        Nivel nivel=null;
        Transaction tx=null;
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx=s.beginTransaction();
            Query query=s.createQuery("from Nivel n where n.idn = :idToFind");
            query.setInteger("idToFind",idn);
            nivel=(Nivel)query.uniqueResult();
        }catch(Exception ex){
            ex.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            s.flush();
            s.close();
        }
        return nivel;
    }
    
    public void updateNivel(int idn, Nivel newNivel){
        Transaction tx=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx=session.beginTransaction();
            Nivel nivel=(Nivel)session.load(Nivel.class,new Integer(idn));
            nivel.setDescripcion(newNivel.getDescripcion());
            nivel.setCatequista(newNivel.getCatequista()) ;
            nivel.setCelular(newNivel.getCelular()) ;
            session.update(nivel);
            tx.commit();
            
         
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
    
    public void deleteNivel(int idn){
        Nivel nivel=null;
        Transaction tx=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx=session.beginTransaction();
              nivel=(Nivel)session.load(Nivel.class,new Integer(idn));
             session.delete(nivel);
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
}
