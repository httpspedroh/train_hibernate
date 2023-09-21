package com.train_hibernate.fk;

// ----------------------------------------------------------------------------------------------------- //

// Import Java classes
import java.io.Serializable;
import java.util.Objects;

// Import JPA classes
import jakarta.persistence.*;

// ----------------------------------------------------------------------------------------------------- //

@Embeddable
public class ContactAppointmentId implements Serializable {

	@Column(name="contact_id")
	private int contact_id;

	@Column(name="appointment_id")
	private int appointment_id;

	public int getContactId() { return contact_id; }
	public int getAppointmentId() { return appointment_id; }

	public void setContactId(int contact_id) { this.contact_id = contact_id; }
	public void setAppointmentId(int appointment_id) { this.appointment_id = appointment_id; }

	@Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactAppointmentId that = (ContactAppointmentId) o;
        return contact_id == that.contact_id && appointment_id == that.appointment_id;
    }

    @Override
    public int hashCode() { return Objects.hash(contact_id, appointment_id); }
}