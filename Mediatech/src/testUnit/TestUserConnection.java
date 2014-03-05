package testUnit;

import org.junit.Before;
import org.junit.Test;

import model.Facade;
import model.IFacade;

public class TestUserConnection 
{
	private IFacade facade;
	private final String USER_NAME="toto", PWD="TOTO";

	@Before
	public void setUp() throws Exception 
	{
		facade = new Facade();
	}

	@Test
	public void test() 
	{
	
		facade.userExist(USER_NAME, PWD);
	}

}
