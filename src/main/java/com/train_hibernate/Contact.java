package com.train_hibernate;

// ----------------------------------------------------------------------------------------------------- //

// Import native classes
import com.train_hibernate.fk.ContactAppointment;

// Import Java classes
import java.util.List;
import java.util.ArrayList;

// Import JPA classes
import jakarta.persistence.*;

// ----------------------------------------------------------------------------------------------------- //

@Entity
@Table(name=Constants.CONTACTS_TABLE_NAME)
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="address")
	private String address;

	@Column(name="phone")
	private String phone;

	@OneToMany(mappedBy="contact")
	private List<ContactAppointment> contactAppointments = new ArrayList<>();

	public int getId() { return id; }
	public String getName() { return name; }
	public String getAddress() { return address; }
	public String getPhone() { return phone; }

	public void setName(String name) { this.name = name; }
	public void setAddress(String address) { this.address = address; }
	public void setPhone(String phone) { this.phone = phone; }

	@Override
	public String toString() { 
		
		return "\n[ID " + id + "]\n" +
			   "Nome: " + name + "\n" +
			   "Endereço: " + address + "\n" +
			   "Telefone: " + phone + "";
	}
}