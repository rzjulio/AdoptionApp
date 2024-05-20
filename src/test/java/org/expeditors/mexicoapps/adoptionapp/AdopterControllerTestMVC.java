package org.expeditors.mexicoapps.adoptionapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.expeditors.mexicoapps.adoptionapp.domain.Adopter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdopterControllerTestMVC {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testGetAll() throws Exception {
        MockHttpServletRequestBuilder builder = get("/api/adopter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions actions = mockMvc.perform(builder)
                .andExpect(status().isOk());

    }

    @Test
    public void testGetOneWithGoodId() throws Exception {
        MockHttpServletRequestBuilder builder = get("/api/adopter/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions actions = mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOneWithBadId() throws Exception {
        MockHttpServletRequestBuilder builder = get("/api/adopter/{id}", 1000)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions actions = mockMvc.perform(builder)
                .andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testPostAdopter() throws Exception {
        Adopter c = new Adopter("Julio","868-888-696");
        String jsonString = mapper.writeValueAsString(c);

        ResultActions actions = mockMvc.perform(post("/api/adopter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString));

        actions = actions.andExpect(status().isCreated());

        MvcResult result = actions.andReturn();
        String locHeader = result.getResponse().getHeader("Location");
        assertNotNull(locHeader);
        System.out.println(STR."locHeader: \{locHeader}");
    }

    @Test
    public void testDeleteAdopterWithGoodId() throws Exception {
        Adopter c = new Adopter("Julio","868-888-696");
        String jsonString = mapper.writeValueAsString(c);

        ResultActions actions = mockMvc.perform(post("/api/adopter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString));

        actions = actions.andExpect(status().isCreated());

        MvcResult result = actions.andReturn();
        String locHeader = result.getResponse().getHeader("Location");
        assertNotNull(locHeader);

        actions = mockMvc.perform(delete(locHeader)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteAdopterWithBadId() throws Exception {
        ResultActions actions = mockMvc.perform(delete("/api/adopter/{id}", 1000)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testUpdateAdopterWithGoodId() throws Exception {
        MockHttpServletRequestBuilder builder = get("/api/adopter/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions actions = mockMvc.perform(builder)
                .andExpect(status().isOk());
        MvcResult result = actions.andReturn();

        String jsonResult = result.getResponse().getContentAsString();
        JsonNode node = mapper.readTree(jsonResult);
        Adopter adopter = mapper.treeToValue(node, Adopter.class);
        adopter.setName("JulioRdz");

        String updatedJson = mapper.writeValueAsString(adopter);

        actions = mockMvc.perform(put("/api/adopter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson));

        actions = actions.andExpect(status().isNoContent());

        builder = get("/api/adopter/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        actions = mockMvc.perform(builder)
                .andExpect(status().isOk());
        result = actions.andReturn();

        jsonResult = result.getResponse().getContentAsString();
        node = mapper.readTree(jsonResult);
        adopter = mapper.treeToValue(node, Adopter.class);

        assertEquals("JulioRdz", adopter.getName());
    }

    @Test
    public void testUpdateAdopterWithBadId() throws Exception {
        MockHttpServletRequestBuilder builder = get("/api/adopter/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions actions = mockMvc.perform(builder)
                .andExpect(status().isOk());
        MvcResult result = actions.andReturn();

        String jsonResult = result.getResponse().getContentAsString();
        JsonNode node = mapper.readTree(jsonResult);
        Adopter adopter = mapper.treeToValue(node, Adopter.class);
        adopter.setName("JulioRdz");
        adopter.setId(1000);

        String updatedJson = mapper.writeValueAsString(adopter);

        actions = mockMvc.perform(put("/api/adopter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson));

        actions = actions.andExpect(status().isNotFound());

    }
}
