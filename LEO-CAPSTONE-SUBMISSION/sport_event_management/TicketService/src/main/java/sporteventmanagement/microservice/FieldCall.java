package sporteventmanagement.microservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sporteventmanagement.entity.Field;

@FeignClient(name = "${Field.name}", url = "${Field.URL}")
public interface FieldCall {

    @GetMapping("/getField/{field_id}")
    Field getField(@PathVariable("field_id") Integer fieldID);
}
