package sporteventmanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import sporteventmanagement.entity.Field;
import sporteventmanagement.exception.InvalidInputException;
import sporteventmanagement.pojo.FieldPojo;
import sporteventmanagement.repository.FieldRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class FieldServiceTest {

    @InjectMocks
    private FieldService fieldService;

    @Mock
    private FieldRepository fieldRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFields() {
        when(fieldRepository.findAll()).thenReturn(getFieldList());
        List<Field> fieldList = fieldService.getAllFields();
        assertNotNull(fieldList);
    }

    @Test
    void getField() {
        when(fieldRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestField()));
        Field field = fieldService.getField(any(Integer.class));
        assertNotNull(field);
    }

    @Test
    void deleteField() {
        String result = "Delete Field Successful.";
        when(fieldRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestField()));
        String testResult = fieldService.deleteField(1);
        assertEquals(result,testResult);
    }

    @Test
    void deleteFieldFailed() {
        exception.expect(InvalidInputException.class);
        when(fieldRepository.findAll()).thenReturn(getFieldList());
        fieldService.deleteField(0);
    }

    @Test
    void addField() {
        when(fieldRepository.findAll()).thenReturn(getFieldList());
        assertNotNull(fieldService.addField(getFieldPojo()));
    }

    @Test
    void updateField() {
        when(fieldRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTestField()));
        assertNotNull(fieldService.updateField(getFieldPojo(),1));
    }

    @Test
    void updateFieldFailed() {
        assertNotNull(fieldService.updateField(getFieldPojo(),1));
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
}