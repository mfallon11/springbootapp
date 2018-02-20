package com.fallon.springbootapp.employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {
    private MockMvc mockMvc;

    @Mock
    private JdbcTemplate jdbcTemplateMock;

    @Before
    public void setup() {
        HsqldbEmployeeDao employeeDao = new HsqldbEmployeeDao(jdbcTemplateMock);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeController(employeeDao)).build();
    }

    @Test
    public void addEmployeeReturnsTrue() throws Exception {
        when(jdbcTemplateMock.update("INSERT INTO employees(employee_number, first_name, last_name) VALUES (?,?,?)",
                1L, "Matt", "Fallon")).thenReturn(1);

        mockMvc.perform(post("/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"employeeNumber\":\"1\",\"firstName\":\"Matt\",\"lastName\":\"Fallon\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"success\":true}"));
    }

    @Test
    public void getEmployeeReturnsEmployee() throws Exception {
        ArrayList employeeList = new ArrayList();
        employeeList.add(new Employee(1, "Matt", "Fallon"));

        when(jdbcTemplateMock.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(employeeList);

        mockMvc.perform(get("/employee/get?employeeNumber=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"success\":true,\"employees\":[{\"employeeNumber\":1,\"firstName\":\"Matt\",\"lastName\":\"Fallon\"}]}"));
    }

    @Test
    public void getAllEmployeesReturnsEmployees() throws Exception {
        ArrayList employeeList = new ArrayList();
        employeeList.add(new Employee(1, "Matt", "Fallon"));
        employeeList.add(new Employee(2, "John", "Doe"));

        when(jdbcTemplateMock.query(anyString(), any(RowMapper.class))).thenReturn(employeeList);

        this.mockMvc.perform(get("/employee/getall").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{\"success\":true,\"employees\":[{\"employeeNumber\":1,\"firstName\":\"Matt\",\"lastName\":\"Fallon\"},{\"employeeNumber\":2,\"firstName\":\"John\",\"lastName\":\"Doe\"}]}"));
    }

    @Test
    public void deleteEmployeeReturnsTrue() throws Exception {
        when(jdbcTemplateMock.update("DELETE FROM employees WHERE employee_number=?", 1)).thenReturn(1);

        mockMvc.perform(delete("/employee/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"employeeNumber\":\"1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"success\":true}"));
    }
}
