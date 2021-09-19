package in.nit.rohit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="specialization_tab")
public class Specialization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="spee_id_col")
	private Long id;
	
	@Column(name="spec_code_col")
	private String spaceCode;
	
	@Column(name="spec_name_col")
	private String specName;
	
	
	
	@Column(name="spec_note_col")
	private String specNote;
	

}
