package com.phonephreak.citybikes_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonephreak.citybikes_backend.station.Station;


@SpringBootTest
@Sql(scripts = "clean-up-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@AutoConfigureMockMvc
class CitybikesBackendApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
  
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void whenGetExistingStation_thenReturns200() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/station/1")
    	.contentType("application/json"))
    	.andExpect(status().isOk());
	}

	@Test
	void whenGetNotExistingStation_thenReturns404() throws Exception {
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

	@Test
	void whenStationAdded_thenReturns201() throws Exception {
		Station station = new Station(999, "X", "X", "X", 20.0f, 60.0f);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/station")
    	.contentType("application/json")
		.content(objectMapper.writeValueAsString(station)))
    	.andExpect(status().isCreated());
	}

	@Test
	void whenStationAddedWithEmptyParameters_thenReturns400() throws Exception {
		Station station = new Station(999, "", "X", "X", 20.0f, 60.0f);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/station")
    	.contentType("application/json")
		.content(objectMapper.writeValueAsString(station)))
    	.andExpect(status().isBadRequest());
	}

	@Test
	void whenDeleteNotExistingStation_thenReturns404() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/station/0")
    	.contentType("application/json"))
    	.andExpect(status().isNotFound());
	}

}
