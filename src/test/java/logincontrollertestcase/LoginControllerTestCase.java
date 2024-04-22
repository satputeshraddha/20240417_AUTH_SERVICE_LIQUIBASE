package logincontrollertestcase;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import javax.xml.xpath.XPathExpressionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testng.annotations.Test;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTestCase extends AbstractTestNGSpringContextTests 
{
	@Autowired
	MockMvc mockMvc;
	

	@Test(priority=1)
	public void verifyLoginSuccessful() throws XPathExpressionException, Exception {
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/api/v1/public/auth/validate").content("<username>Admin</username>\r\n"
				+ "  <password>Admin123</password>\r\n"
			
			).contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE);
		
		MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isCreated())
				  .andExpect(xpath("/LoginResponse/status").string("Success"))
		  .andExpect(xpath("/LoginResponse/message"). string("Login successful"))
				  
			  .andReturn();
	
	}
	
	
	
	@Test(priority=2)
	public void verifyLoginFail() throws XPathExpressionException, Exception {
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/api/v1/public/auth/validate").content("<username>Admin</username>\r\n"
				+ "  <password>Admin1234</password>\r\n"
				
			).contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE);
		
		MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk())
				  .andExpect(xpath("/LoginResponse/status").string("Fail"))
				  .andExpect(xpath("/LoginResponse/message"). string("Login failed"))
				  
				  .andReturn();
	}
	

}