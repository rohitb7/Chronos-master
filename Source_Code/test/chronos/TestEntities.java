package chronos;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coen275.chronos.project.Project;
import coen275.chronos.project.User;
import coen275.chronos.service.Application;
import coen275.chronos.service.Services;
public class TestEntities {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
//			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-mysql");
//			emf.createEntityManager();
			Application a = new Application();
			a.contextInitialized(null);
			Services s = Services.getInstance();
			User u = s.getUserMgmtService().getUser("kaushik");
			 Set<Project> p = u.getProjects();
			 assertNotNull(p);
			 assertTrue(p.size()>0);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
