    package in.nit.rohit.service;

import java.util.List;

import in.nit.rohit.entity.SlotRequest;

public interface ISlotRequestService {
	
	// Patient can book  slot
	Long saveSlotRequest(SlotRequest sr);
	
	//Fetch one slot
	SlotRequest getOneSlotRequest(Long id);
	
	// Admin Can view All the slots
	List<SlotRequest> getAllSlotRequests();
	
	//Admin/Patient can update the status
	void updateSlotRequestStatus(Long id, String status);
	
	// Patient Can view there slot 
	List<SlotRequest> viewSlotsByPatientMail(String patientMail);
	
	// Doctor can view there slot
	List<SlotRequest> viewSlotsByDoctorMail(String doctorMail);
	
	// Admin Can see the numbet of Slots and it's count 
	List<Object[]> getSlotsStatusAndCount();
	

}
