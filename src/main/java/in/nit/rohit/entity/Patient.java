package in.nit.rohit.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="patient_tab")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Patient {

	@Id	
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name="pat_first_name_col")
	private String firstName;
	
	@Column(name="pat_last_name_col")
	private String lastName;
	
	@Column(name="pat_gen_col")
	private String gender;
	
	@Column(name="pat_mob_col")
	private String mobile;
	
	@Column(name="pat_email_col")
	private String email;
	
	@Column(name="pat_dob_col")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name="pat_marialStatus")
	private String marialStatus;
	
	@Column(name="pat_paddr_col")
	private String presentAddr;
	
	@Column(name="pat_caddr_col")
	private String commAddr;
	
	@ElementCollection
	@CollectionTable(name="pat_medi_hist_tab", joinColumns = @JoinColumn(name="pat_medi_hst_id_fk_col"))
	@Column(name= "pat_medi_hist_col")
	private Set<String> mediHistory;
	
	@Column(name="pat_other_call")
	private String ifOther;
	
	@Column(name="pat_note_col")
	private String note;
	
}
