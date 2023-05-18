package org.match_service.microservice;


import org.match_service.entity.Field;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${Field.name}", url = "${Field.URL}")
public interface FieldCall {

    @GetMapping("/getField/{field_id}")
    Field getField(@PathVariable("field_id") Integer fieldID);
}
