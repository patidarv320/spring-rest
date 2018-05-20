package test.spring.rest.cotroller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import com.spring.rest.config.AppConfig;
import com.spring.rest.service.HelloService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class,HelloWorldControllerTest.class})
@WebAppConfiguration
public class HelloWorldControllerTest{

	private MockMvc mockMvc;

	private HelloService helloService = Mockito.mock(HelloService.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		// We have to reset our mock between tests because the mock objects
		// are managed by the Spring container. If we would not reset them,
		// stubbing and verified behavior would "leak" from one test to another.
		Mockito.reset(helloService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}
	
	@Test
	public void testSayHello() throws Exception {

		Mockito.when(helloService.sayHello()).thenReturn("Hello World!");
		String expected = "Hello World!";
		System.out.println(expected);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/hello").accept(MediaType.TEXT_PLAIN);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		junit.framework.Assert.assertEquals(result.getResponse().getContentAsString(),expected);
		
		//org.junit.Assert.assertTrue(result.getResponse().getContentAsString().equals(expected));

	}
}

