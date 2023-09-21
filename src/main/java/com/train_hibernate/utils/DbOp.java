package com.train_hibernate.utils;

// ----------------------------------------------------------------------------------------------------- //

// Import native classes
import com.train_hibernate.Contact;
import com.train_hibernate.Constants;

// Import Java classes
import java.util.ArrayList;
import java.util.List;

// Import logger classes
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// Import hibernate classes
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

// ----------------------------------------------------------------------------------------------------- //

public class DbOp {

	static Session session = null;
	public final static Logger logger = LogManager.getLogger(DbOp.class);

	// ---------------------------------------------- //

	public static void init() {

		try {

			// Start session and transaction
			session = HibernateUtil.getSession();
			session.beginTransaction();
		}
		catch (Exception e) { logger.error("Error starting database: " + e.getMessage()); }
	}

    // ---------------------------------------------- //

	public static void insertContact(Contact contact) {
		
        Transaction transaction = null;
		  
        try {

			// Start session and transaction
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
            
			// Save the student object
            session.persist(contact);
            
			// Commit transaction
            transaction.commit();

        } catch (Exception e) {

            // In case of any exception, rollback the transaction
			if (transaction != null) { 
				
				logger.error("\n[x] Transaction is being rolled back.\n");
				transaction.rollback();
			}
            
            logger.info("Error: " + e.getMessage());

        } finally {

            if (session != null) session.close();
        }
    }

    // ---------------------------------------------- //

	public static List<Contact> getContacts() {

		List<Contact> contacts = new ArrayList<Contact>();

		try {

			// Start session and transaction
			session = HibernateUtil.getSession();
			session.beginTransaction();
            
			Query<Contact> queryObj = session.createQuery("FROM " + Constants.CONTACTS_ENTITY_NAME, Contact.class);
			contacts = queryObj.list();

		} catch(Exception sqlException) {

			if(null != session.getTransaction()) {

				logger.error("\n[x] Transaction is being rolled back.\n");
				session.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {

			if(session != null) session.close();
		}

		return contacts;
	}

	// ---------------------------------------------- //

	public static void updateContact(Contact contact) {

		try {

			// Start session and transaction
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			session.merge(contact);

			// Commit transaction
			session.getTransaction().commit();

		} catch(Exception sqlException) {

			if(null != session.getTransaction()) {

				logger.error("\n[x] Transaction is being rolled back.\n");
				session.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {

			if(session != null) session.close();
		}
	}

	// ---------------------------------------------- //

	public static void deleteContact(Contact contact) {

		try {

			// Start session and transaction
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			session.remove(contact);

			// Commit transaction
			session.getTransaction().commit();

		} catch(Exception sqlException) {

			if(null != session.getTransaction()) {

				logger.error("\n[x] Transaction is being rolled back.\n");
				session.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {

			if(session != null) session.close();
		}
	}

	// ---------------------------------------------- //

	public static Contact getContactById(int contactId) {

		Contact contact = null;

		try {

			// Start session and transaction
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			contact = session.get(Contact.class, contactId);

		} catch(Exception sqlException) {

			if(null != session.getTransaction()) {

				logger.error("\n[x] Transaction is being rolled back.\n");
				session.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {

			if(session != null) session.close();
		}
		return contact;
	}

	// ---------------------------------------------- //
}