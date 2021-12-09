package in.nit.rohit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.rohit.entity.SlotRequest;
import in.nit.rohit.repo.SlotRequestRepository;
import in.nit.rohit.service.ISlotRequestService;
import in.nit.rohit.service.constant.SlotStatus;

@Service
public class SlotRequestServiceImpl implements ISlotRequestService {
	
	@Autowired
	private SlotRequestRepository repo;

	@Override
	public Long saveSlotRequest(SlotRequest sr) {
		
		return repo.save(sr).getId();
	}

	@Override
	public SlotRequest getOneSlotRequest(Long id) {
		Optional<SlotRequest> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		
		return null;
	}

	@Override
	public List<SlotRequest> getAllSlotRequests() {
		
		return repo.findAll();
	}

	@Transactional
	public void updateSlotRequestStatus(Long id, String status) {
		repo.updateSlotRequestStatus(id, status);
		
	}

	@Override
	public List<SlotRequest> viewSlotsByPatientMail(String patientMail) {
		
		return repo.getAllPatientSlotes(patientMail);
	}

	@Override
	public List<SlotRequest> viewSlotsByDoctorMail(String doctorMail) {
		
		return repo.getAllDoctorSlots(doctorMail,SlotStatus.ACCEPTED.name());
	}

	@Override
	public List<Object[]> getSlotsStatusAndCount() {
		
		return repo.getSlotsStatusAndCount();
	}

}
