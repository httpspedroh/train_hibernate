package com.train_hibernate.utils;

import org.hibernate.Session;

// ----------------------------------------------------------------------------------------------------- //

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// ----------------------------------------------------------------------------------------------------- //

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    // private static final Session session;
    
    static {

        try { 
            
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            
        } catch (Throwable ex) {

            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
   
    public static Session getSession() { return sessionFactory.openSession(); }
}