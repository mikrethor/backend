package fr.ablx.daycare.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import fr.ablx.daycare.config.Application;
import fr.ablx.daycare.jpa.User;
import fr.ablx.daycare.objects.Credentials;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class LoginControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper mapperJsonObject;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void login() throws Exception {

        Credentials user = new Credentials("test@parent","test");

        String json = new Gson().toJson(user);

        ResultActions resultActions = mockMvc.perform(
                post("/login").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
        String result = resultActions.andReturn().getResponse().getContentAsString();

        User u = mapperJsonObject.readValue(result, User.class);
        Assert.assertEquals(
                "{\"id\":1,\"login\":\"test@parent\",\"daycareId\":1,\"educator\":null,\"parent\":{\"id\":1,\"firstName\":\"Xavier\",\"lastName\":\"B\"},\"admin\":null}",
                resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void logout() throws Exception {

    }

}