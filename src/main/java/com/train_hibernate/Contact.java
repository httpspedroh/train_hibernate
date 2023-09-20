package com.train_hibernate;

// ----------------------------------------------------------------------------------------------------- //

// Import Java classes
import java.io.Serializable;

// Import JPA classes
  import javax.persistence.*;
  
// ----------------------------------------------------------------------------------------------------- //

@Entity
@Table(name="contacts_753045")
public class Contact implements Serializable {

	// private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="address")
	private String address;

	@Column(name="phone")
	private String phone;

	public String getName() { return name; }
	public String getAddress() { return address; }
	public String getPhone() { return phone; }

	public void setName(String name) { this.name = name; }
	public void setAddress(String address) { this.address = address; }
	public void setPhone(String phone) { this.phone = phone; }

	@Override
	public String toString() { return "Contact [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + "]"; }
}