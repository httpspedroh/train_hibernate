package com.train_hibernate.fk;

// ----------------------------------------------------------------------------------------------------- //

// Import native classes
import com.train_hibernate.Appointment;
import com.train_hibernate.Contact;
import com.train_hibernate.Constants;

// Import JPA classes
import jakarta.persistence.*;

// ----------------------------------------------------------------------------------------------------- //

@Entity
@Table(name=Constants.CONTACTS_APPOINTMENTS_TABLE_NAME)
public class ContactAppointment {

	@EmbeddedId
	private ContactAppointmentId id = new ContactAppointmentId();

	@ManyToOne
	@MapsId("contact_id") // Attribute name in ContactAppointmentId.java
	@JoinColumn(name="contact_id") // Foreign key column name in table
	private Contact contact;

	@ManyToOne
	@MapsId("appointment_id") // Attribute name in ContactAppointmentId.java
	@JoinColumn(name="appointment_id") // Foreign key column name in table
	private Appointment appointment;

	private String role;

	public ContactAppointmentId getId() { return id; }
	public Contact getContact() { return contact; }
	public Appointment getAppointment() { return appointment; }
	public String getRole() { return role; }

	public void setId(ContactAppointmentId id) { this.id = id; }

	public void setContact(Contact contact) { 

		this.contact = contact;
		this.id.setContactId(contact.getId());
	}

	public void setAppointment(Appointment appointment) { 
		
		this.appointment = appointment; 
		this.id.setAppointmentId(appointment.getId());
	}

	public void setRole(String role) { this.role = role; }

	@Override
	public String toString() { 
		
		return "\nContato: " + contact.getName() + "\n" +
			   "Compromisso: " + appointment.getDescription() + "\n" +
			   "Data: " + appointment.getDate() + "\n" +
			   "Função: " + role + "";
	}
}