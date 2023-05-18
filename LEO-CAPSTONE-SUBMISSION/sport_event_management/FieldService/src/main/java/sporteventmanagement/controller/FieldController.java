package sporteventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sporteventmanagement.entity.Field;
import sporteventmanagement.exception.InvalidInputException;
import sporteventmanagement.pojo.FieldPojo;
import sporteventmanagement.service.FieldService;


import java.util.ArrayList;
import java.util.List;

@RestController
public class FieldController {

    @Autowired
    FieldService fieldService;

    @GetMapping("/fields/getFields")
    public List<Field> getAllFields(){
        return fieldService.getAllFields();
    }

    @GetMapping("/getField/{field_id}")
    public Field getField(@PathVariable("field_id") Integer fieldID){
        return fieldService.getField(fieldID);
    }

    @PostMapping("/fields/addField")
    public ResponseEntity<String> addField(@RequestBody FieldPojo fieldPojo){
        return new ResponseEntity<>(fieldService.addField(fieldPojo), HttpStatus.OK);
    }
    @PutMapping("/fields/updateField")
    public ResponseEntity<String> updateField(@RequestBody FieldPojo fieldPojo,
                                               @RequestParam(value = "field_id", required = false) Integer fieldID){
        List<Object> updateResult = new ArrayList<>(fieldService.updateField(fieldPojo,fieldID));
        if(Boolean.TRUE.equals(updateResult.get(0))){
            return new ResponseEntity<>(String.valueOf(updateResult.get(1)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(String.valueOf(updateResult.get(1)), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/fields/deleteField")
    public ResponseEntity<String> deleteTicket(@RequestParam (value = "field_id", required = false) Integer fieldID){
        return new ResponseEntity<>(fieldService.deleteField(fieldID), HttpStatus.OK);
    }

}
