package in.nit.rohit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="patientTab")
public class Patient {

	@Id	
	@GeneratedValue
	@Column(name="patId")
	private Integer id;
	
	@Column(name="patName")
	private String name;
	
	@Column(name="patGender")
	private String gender;
	
	@Column(name="patPhoneNumber")
	private String phoneNumber;
	
	@Column(name="patMarialStatus")
	private String marialStatus;
	
	@Column(name="patPresentAdd")
	private String presentAdd;
	
	@Column(name="patCommunicationAdd")
	private String CommunicationAdd;
	
	@Column(name="patPostMedicalHistory")
	private String postMedicalHistory;
	
	@Column(name="patOtherDetails")
	private String otherDetails;
	
}
