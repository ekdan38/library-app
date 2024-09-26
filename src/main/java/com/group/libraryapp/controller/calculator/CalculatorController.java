package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

//    @GetMapping("/add")
    public String calculatorV1(@RequestParam(value = "number1", required = false, defaultValue = "0") int number1,
                             @RequestParam(value = "number2", required = false, defaultValue = "0") int number2){
        int result = number1 + number2;
        return "result = " + result;
    }

    @GetMapping("/add")
    public int calculatorV2(@ModelAttribute CalculatorAddRequest request){
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")
    public ResponseEntity<Test> multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
        return new ResponseEntity<>(new Test(request.getNumber1() + request.getNumber2()), HttpStatus.OK);
    }

    static class Test{
        private int result;

        public Test(int result) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }
    }

}
