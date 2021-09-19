package in.nit.rohit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="doctorTab")
public class Doctor {

	@Id
	@GeneratedValue
	@Column(name="docId")
	private Integer id;
	
	@Column(name="docName")
	private String name;
	
	@Column(name="docEmailId")
	private String emailId;
	
	@Column(name="docSpecialization")
	private String specialization;
	
	@Column(name="docAdd")
	private String address;
	
	@Column(name="docMobile")
	private String mobile;
	
	@Column(name="docGender")
	private String gender;
	
	@Column(name="docNote")
	private String note;
	
	@Column(name="docPhoto")
	private String photo;
	
	
	

}
