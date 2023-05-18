package sporteventmanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sporteventmanagement.entity.Field;
import sporteventmanagement.exception.InvalidInputException;
import sporteventmanagement.repository.FieldRepository;
import sporteventmanagement.pojo.FieldPojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FieldService {

    @Autowired
    FieldRepository fieldRepository;

    Logger logger = Logger.getLogger(FieldService.class.getName());

    public List<Field> getAllFields(){
        return fieldRepository.findAll();
    }

    public Field getField(Integer fieldID){
        return fieldRepository.findById(fieldID).orElse(null);
    }

    public String deleteField(int fieldID){
        try {
            Optional<Field> field = fieldRepository.findById(fieldID);
            if (field.isEmpty()){
                throw new InvalidInputException("The field ID inserted does not exist.");
            }else {
                fieldRepository.deleteById(fieldID);
                return "Delete Field Successful.";
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            return e.getMessage();
        }
    }


    public String addField(FieldPojo fieldPojo) {
        Date date = new Date();
        fieldRepository.save(new Field(fieldPojo.getFieldNamePojo(),
                fieldPojo.getFieldAddressPojo(), fieldPojo.getFieldCapacityPojo(), date));
        return "Insert Field Success!";

    }

    public List<Object> updateField(FieldPojo fieldPojo, Integer fieldID) {
        Date date = new Date();
        List<Object> result = new ArrayList<>();
        try {
            Optional<Field> fieldTemp = fieldRepository.findById(fieldID);
            if(fieldTemp.isEmpty()){
                throw new InvalidInputException("The field ID inserted does not exist.");
            }
            fieldRepository.save(new Field(fieldID,fieldPojo.getFieldNamePojo(),
                    fieldPojo.getFieldAddressPojo(), fieldPojo.getFieldCapacityPojo(), date));
            result.add(true);
            result.add("Update Field Success!");
            return result;
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            result.add(false);
            result.add(e.getMessage());
            return result;
        }
    }
}
