package com.pipeline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//Must contain unit tests for all getters and setters, as well as any utility methods.
//Must contain integration tests for at least all non-authenticated GET request endpoints.

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PipelineApplicationTests {
	@Autowired
	MockMvc mockMvc;
	@Test
	public void contextLoads() {
	}
		@Test public void testHomePage_containsUsername() throws Exception {
		//Root/index
			this.mockMvc
					.perform(MockMvcRequestBuilders.get("/"))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.content().string(
							org.hamcrest.Matchers.containsString("LOGIN")
					));
		}

	@Test public void testRegistrationPage() throws Exception {
		//registration
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/registration"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect((MockMvcResultMatchers.content().string(
						org.hamcrest.Matchers.containsString("Register")
				)));
		}
}




