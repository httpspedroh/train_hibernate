package com.train_hibernate;

// ----------------------------------------------------------------------------------------------------- //

// Import native classes
import com.train_hibernate.fk.ContactAppointment;

// Import Java classes
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

// Import JPA classes
import jakarta.persistence.*;

// ----------------------------------------------------------------------------------------------------- //

@Entity
@Table(name=Constants.APPOINTMENTS_TABLE_NAME)
public class Appointment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="date")
	private Date date;

	@Column(name="description")
	private String description;

	@OneToMany(mappedBy="appointment")
	private List<ContactAppointment> contactAppointments = new ArrayList<>();

	public int getId() { return id; }
	public Date getDate() { return date; }
	public String getDescription() { return description; }

	public void setId(int id) { this.id = id; }
	public void setDate(Date date) { this.date = date; }
	public void setDescription(String description) { this.description = description; }

	@Override
	public String toString() { 
		
		return "\n[ID " + id + "]\n" +
			   "Data: " + date + "\n" +
			   "Descrição: " + description + "";
	}
}