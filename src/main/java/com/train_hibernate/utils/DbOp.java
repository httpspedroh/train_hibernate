package com.train_hibernate.utils;

// ----------------------------------------------------------------------------------------------------- //

// Import Contact
import com.train_hibernate.Contact;

// Import Java classes
import java.util.ArrayList;
import java.util.List;

// Import logger classes
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// Import hibernate classes
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.query.Query;

// ----------------------------------------------------------------------------------------------------- //

public class DbOp {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	public final static Logger logger = LogManager.getLogger(DbOp.class);

    // ---------------------------------------------- //

	private static SessionFactory buildSessionFactory() {

		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 

		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

    // ---------------------------------------------- //

    public static void createContact() {

        Contact contact = null;

        try {
			
            sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

		    for(int j = 101; j <= 105; j++) {

				contact = new Contact();
				contact.setAddress("Avenida Dom José Gaspar, 500");
				contact.setName("Aluno " + j);
				contact.setPhone("(31) 99999-8877");
			}

			sessionObj.getTransaction().commit();
			logger.info("\nSuccessfully created 5 records in the database!\n");

		} catch(Exception sqlException) {

			if(null != sessionObj.getTransaction()) {

				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {

			if(sessionObj != null) sessionObj.close();
		}
    }

    // ---------------------------------------------- //

	public static List<Contact> getContacts() {

		List<Contact> contacts = new ArrayList<Contact>();

		try {

			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			Query<Contact> queryObj = sessionObj.createQuery("FROM Contact", Contact.class);

			contacts = queryObj.list();

		} catch(Exception sqlException) {

			if(null != sessionObj.getTransaction()) {

				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {

			if(sessionObj != null) sessionObj.close();
		}

		return contacts;
	}

	// ---------------------------------------------- //

	public static void updateRecord(int contactId) {

		try {

			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			Contact contact = (Contact) sessionObj.get(Contact.class, contactId);
			contact.setName("Aluno 9");
			contact.setAddress("Avenida Dom Lúcio Antunes, 200");
			contact.setPhone("(31) 98975-8877");

			sessionObj.getTransaction().commit();
			logger.info("\nContact With Id?= " + contactId + " Is Successfully Updated In The Database!\n");

		} catch(Exception sqlException) {

			if(null != sessionObj.getTransaction()) {

				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {

			if(sessionObj != null) sessionObj.close();
		}
	}

	// ---------------------------------------------- //

	public static void deleteRecord(int contactId) {

		try {

			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			Contact contact = sessionObj.get(Contact.class, contactId);
			sessionObj.remove(contact);

			sessionObj.getTransaction().commit();
			logger.info("\nContact With Id?= " + contactId + " Is Successfully Deleted From The Database!\n");

		} catch(Exception sqlException) {

			if(null != sessionObj.getTransaction()) {

				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {

			if(sessionObj != null) sessionObj.close();
		}
	}

	// ---------------------------------------------- //
}