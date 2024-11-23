package br.com.engjaconi.curso_springboot_3.controller;

import br.com.engjaconi.curso_springboot_3.service.MathService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping(value = "/sum/{numOne}/{numTwo}")
    public Double sum(@PathVariable String numOne, @PathVariable String numTwo) {
        return mathService.sum(numOne, numTwo);
    }

    @GetMapping(value = "/subtraction/{numOne}/{numTwo}")
    public Double subtraction(@PathVariable String numOne, @PathVariable String numTwo) {
        return mathService.subtraction(numOne, numTwo);
    }

    @GetMapping(value = "/multiplication/{numOne}/{numTwo}")
    public Double multiplication(@PathVariable String numOne, @PathVariable String numTwo) {
        return mathService.multiplication(numOne, numTwo);
    }

    @GetMapping(value = "/division/{numOne}/{numTwo}")
    public Double division(@PathVariable String numOne, @PathVariable String numTwo) {
        return mathService.division(numOne, numTwo);
    }

    @GetMapping(value = "/mean/{numOne}/{numTwo}")
    public Double mean(@PathVariable String numOne, @PathVariable String numTwo) {
        return mathService.mean(numOne, numTwo);
    }

    @GetMapping(value = "/squareRoot/{number}")
    public Double squareRoot(@PathVariable String number) {
        return mathService.squareRoot(number);
    }

}
