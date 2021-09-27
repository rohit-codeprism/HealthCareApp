package in.nit.rohit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="doctor_tab")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="docId")
	private Long id;
	
	@Column(name="doc_fn_col")
	private String firstName;
	
	@Column(name="doc_ln_col")
	private String lastName;
	
	
	@Column(name="doc_mail_col")
	private String emailId;
	
	@Column(name="doc_addr_col")
	private String address;
	

	@Column(name="doc_mob_col")
	private String mobile;
	
	@Column(name="doc_gen_col")
	private String gender;
	
	@Column(name="doc_note_col")
	private String note;
	
	
	
	@Column(name="doc_img_col")
	private String photoLoc;
	
	// Associate Mapping 
	
	@ManyToOne
	@JoinColumn(name="spec_key_fk_col")
	private Specialization specialization; // Has-A
	
	
	
	

}
