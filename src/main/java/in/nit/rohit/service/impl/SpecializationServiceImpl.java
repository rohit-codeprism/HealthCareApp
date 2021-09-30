package in.nit.rohit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import in.nit.rohit.entity.Specialization;
import in.nit.rohit.exception.SpecializationNotFoundException;
import in.nit.rohit.repo.SpecializationRepository;
import in.nit.rohit.service.ISpecializationService;
import in.nit.rohit.util.myCollectionsUtil;
import in.nit.rohit.view.SpecializationExcelView;

@Service
public class SpecializationServiceImpl implements ISpecializationService {
	
	@Autowired
	private SpecializationRepository repo;

	@Override
	public Long saveSpecialization(Specialization specialization) {
		
		return repo.save(specialization).getId();
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		/*
		Optional<Specialization> opt = repo.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}else {
			throw new SpecializationNotFoundException(id+"Not Found");
		}
		*/
		return repo.findById(id).orElseThrow(()-> new SpecializationNotFoundException(id+"Not Found"));
		
	}

	@Override
	public List<Specialization> getAllSpecialization() {
		List<Specialization> list = repo.findAll();
		return list;
	}

	@Override
	public void deleteSpecialization(Long id) {
		//repo.deleteById(id);
		repo.delete(getOneSpecialization(id));
		
	}

	@Override
	public void UpdateSpecialization(Specialization specialization) {
		repo.save(specialization);
		
	}

	@Override
	public boolean isSpecCodeExist(String specCode) {
		
		/*Integer count = repo.getSpecializationSpecCodeCount(specCode);
		boolean exist = count>0 ? true:false;
		return exist;
		*/
		return repo.getSpecCodeCount(specCode)>0;
	}

	@Override
	public boolean isSpecCodeExistForEdit(String specCode, Long id) {
		
		return repo.getSpecCodeCountForEdit(specCode, id) > 0;
	}

	@Override
	public Map<Long,String> getSpecIdAndName() {
		
		List<Object[]> list  = repo.getSpecIdAndName();
		Map<Long,String> map = myCollectionsUtil.convertToMap(list) ;
		return map;
		
	}
	
	
	

}
