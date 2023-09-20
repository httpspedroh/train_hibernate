package com.train_hibernate;

// ----------------------------------------------------------------------------------------------------- //

// Import DbOp
import com.train_hibernate.utils.DbOp;

// Import Java classes
import java.util.List;

// Import logger classes
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
// ----------------------------------------------------------------------------------------------------- //

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
 
    // ---------------------------------------------- //

    public static void main(String[] args) {
        
        logger.info(".......Hibernate Crud Operations Example.......\n");

		// ---------------------------------------------------- //

		logger.info("\n======= CREATE RECORDS =======\n");

		DbOp.createContact();

		// ---------------------------------------------------- //
		
		logger.info("\n======= READ RECORDS =======\n");

		List<Contact> contacts = DbOp.getContacts();

		if(contacts != null & contacts.size() > 0) {

			for(Contact contact : contacts) logger.info(contact.toString());
		}

		// ---------------------------------------------------- //
		
		logger.info("\n======= UPDATE RECORDS =======\n");

		int updateId = 1;
		DbOp.updateRecord(updateId);

		// ---------------------------------------------------- //
		
		logger.info("\n======= READ RECORDS AFTER UPDATE =======\n");

		List<Contact> contactsAfterUpdate = DbOp.getContacts();

		if(contactsAfterUpdate != null & contactsAfterUpdate.size() > 0) {

			for(Contact contact : contactsAfterUpdate) logger.info(contact.toString());
		}

		// ---------------------------------------------------- //
		
		logger.info("\n======= DELETE RECORD =======\n");

		int deleteId = 5;

		DbOp.deleteRecord(deleteId);

		// ---------------------------------------------------- //

		logger.info("\n======= READ RECORDS AFTER DELETION =======\n");

		List<Contact> contactsAfterDeletion = DbOp.getContacts();

		if(contactsAfterDeletion != null & contactsAfterDeletion.size() > 0) {

			for(Contact contact : contactsAfterDeletion) logger.info(contact.toString());
		}

		System.exit(0);
    }
}