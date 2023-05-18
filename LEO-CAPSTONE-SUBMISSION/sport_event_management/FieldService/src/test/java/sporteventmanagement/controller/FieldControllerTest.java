package sporteventmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sporteventmanagement.entity.Field;
import sporteventmanagement.pojo.FieldPojo;
import sporteventmanagement.repository.FieldRepository;
import sporteventmanagement.service.FieldService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FieldControllerTest {

    @InjectMocks
    private FieldController fieldController;

    @MockBean
    private FieldService fieldService;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(fieldController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }


    @Test
    void getAllFields() throws Exception {
        List<Field> fieldList = getFieldList();
        when(fieldService.getAllFields()).thenReturn(fieldList);
        mockMvc.perform(MockMvcRequestBuilders.get("/fields/getFields").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(fieldList)));
    }

    @Test
    void getField() throws Exception {
        Field field = getTestField();
        when(fieldService.getField(any(Integer.class))).thenReturn(field);
        mockMvc.perform(MockMvcRequestBuilders.get("/getField/{field_id}",1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(field)));
    }

    @Test
    void addField() throws Exception {
        FieldPojo fieldPojo = getFieldPojo();
        String result = "Insert Field Success!";
        when(fieldService.addField(any(FieldPojo.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/fields/addField")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(fieldPojo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Insert Field Success!"));
    }

    @Test
    void updateField() throws Exception {
        FieldPojo fieldPojo = getFieldPojo();
        List<Object> result = new ArrayList<>();
        result.add(true);
        result.add("Update Field Success!");
        when(fieldService.updateField(any(FieldPojo.class),any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.put("/fields/updateField")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(fieldPojo))
                        .param("field_id","1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Update Field Success!"));
    }
    @Test
    void updateFieldFailed() throws Exception {
        FieldPojo fieldPojo = getFieldPojo();
        List<Object> result = new ArrayList<>();
        result.add(false);
        result.add(null);
        when(fieldService.updateField(any(FieldPojo.class),any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.put("/fields/updateField")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(fieldPojo))
                        .param("field_id","1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteTicket() throws Exception {
        String result = "Delete Field Successful.";
        when(fieldService.deleteField(any(Integer.class))).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.delete("/fields/deleteField")
                        .param("field_id", String.valueOf(1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    private FieldPojo getFieldPojo(){
        FieldPojo fieldPojo = new FieldPojo();
        fieldPojo.setFieldAddressPojo("testAddress");
        fieldPojo.setFieldCapacityPojo(1234);
        fieldPojo.setFieldNamePojo("testName");
        return fieldPojo;
    }

    private Field getTestField(){
        Field field = new Field();
        field.setFieldID(1);
        field.setFieldName("testName");
        field.setFieldAddress("testAddress");
        field.setFieldCapacity(10000);
        field.setLastUpdate(new Date());
        return field;
    }

    private List<Field> getFieldList(){
        List<Field> fieldList = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            Field field = new Field();
            field.setFieldID(i);
            fieldList.add(field);
        }
        return fieldList;
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}