package com.veeron.lambdaforaws;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private FunctionCatalog functionCatalog;

    @PostMapping
    public String invokeFunction(@RequestHeader("functionName") String functionName, @RequestBody String data) {
        Function<String, String> function = functionCatalog.lookup(functionName);

        if (function == null) {
            return "Function not found: " + functionName;
        }

        return function.apply(data);
    }
}