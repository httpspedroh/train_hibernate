package com.train_hibernate.utils;

// ----------------------------------------------------------------------------------------------------- //

// Import native classes
import com.train_hibernate.Contact;
import com.train_hibernate.fk.ContactAppointment;
import com.train_hibernate.Appointment;
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

	public static void createRecord(Object object) {
		
		Transaction transaction = null;
		  
		try {

			// Start session and transaction
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			// Save the object
			session.persist(object);

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

	public static List<Object> readRecords(String entityName) {

		List<Object> objects = new ArrayList<Object>();

		try {

			// Start session and transaction
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			Query<Object> queryObj = session.createQuery("FROM " + entityName, Object.class);
			objects = queryObj.list();

		} catch(Exception sqlException) {

			if(null != session.getTransaction()) {

				logger.error("\n[x] Transaction is being rolled back.\n");
				session.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {

			if(session != null) session.close();
		}

		return objects;
	}

	// ---------------------------------------------- //

	public static void updateRecord(Object object) {

		try {

			// Start session and transaction
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			session.merge(object);

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

	public static void deleteRecord(Object object) {

		try {

			// Start session and transaction
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			session.remove(object);

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

	public static List<ContactAppointment> readAppointments(Object object, String entityName) {

		List<ContactAppointment> contactAppointments = new ArrayList<ContactAppointment>();

		try {

			// Start session and transaction
			session = HibernateUtil.getSession();
		
			String jpql = "SELECT ca FROM " + Constants.CONTACTS_APPOINTMENTS_ENTITY_NAME + " ca WHERE ca." + entityName.toLowerCase() + " = :object";
			Query<ContactAppointment> query = session.createQuery(jpql, ContactAppointment.class);
			query.setParameter("object", object);

			contactAppointments = query.getResultList();

		} catch(Exception sqlException) {

			if(null != session.getTransaction()) {

				logger.error("\n[x] Transaction is being rolled back.\n");
				session.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {

			if(session != null) session.close();
		}

		return contactAppointments;
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

	public static Appointment getAppointmentById(int appointmentId) {

		Appointment appointment = null;

		try {

			// Start session and transaction
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			appointment = session.get(Appointment.class, appointmentId);

		} catch(Exception sqlException) {

			if(null != session.getTransaction()) {

				logger.error("\n[x] Transaction is being rolled back.\n");
				session.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {

			if(session != null) session.close();
		}
		return appointment;
	}

	// ---------------------------------------------- //
}