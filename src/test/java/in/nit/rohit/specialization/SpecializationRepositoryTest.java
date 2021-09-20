package in.nit.rohit.specialization;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import in.nit.rohit.entity.Specialization;
import in.nit.rohit.repo.SpecializationRepository;

@DataJpaTest(showSql=true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class SpecializationRepositoryTest {

	@Autowired
	private SpecializationRepository repo;
	
	/***
	 * 1. Test save operation
	 */
	@Test
	@Order(1)
	public void testSpecCreat()
	{
		Specialization spec = new Specialization(null,"CRDLS","CardioLogist","They are expert on heart the blood vessel");
		spec= repo.save(spec);
		assertNotNull(spec.getId(),"Spec is not create!");
		
	}
	/***
	 * Tets display all operation
	 */
	@Test
	@Order(2)
	public void testSpecFetchAll()
	{
		List<Specialization> list = repo.findAll();
		assertNull(list);
		if(list.isEmpty()) {
			fail("No data exist in Database");
		}
	}
}
