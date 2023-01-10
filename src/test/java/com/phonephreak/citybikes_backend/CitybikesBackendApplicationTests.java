package com.phonephreak.citybikes_backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class CitybikesBackendApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
  
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void whenStationExists_thenReturns200() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/station/111")
    	.contentType("application/json"))
    	.andExpect(status().isOk());
	}

	@Test
	void whenStationDoesNotExist_thenReturns404() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/station/0")
    	.contentType("application/json"))
    	.andExpect(status().isNotFound());
	}

	@Test
	void whenStationsPageParametersMissing_thenReturns400() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/station/page")
    	.contentType("application/json"))
    	.andExpect(status().isBadRequest());
	}
	
	@Test
	void whenJourneysPageParametersMissing_thenReturns400() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/journey/page")
    	.contentType("application/json"))
    	.andExpect(status().isBadRequest());
	}

}
