package com.fallon.springbootapp.fibonacci;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciController {

    @RequestMapping("/fibonacci")
    public long[] getFibonacciNumbers(@RequestParam(value="number", defaultValue="1") int number) {
        if(number < 0) {
            return new long[0];
        }
        long[] fibonacciSequence = new long[number];

        for(int i = 1; i <= number; i++) {
            fibonacciSequence[i-1] = fibonacci(i);
        }

        return fibonacciSequence;
    }

    private long fibonacci(long n)  {
        if(n == 0)
            return 0;
        else if(n == 1)
            return 1;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
