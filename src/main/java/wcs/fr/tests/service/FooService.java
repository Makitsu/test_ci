package wcs.fr.tests.service;

import org.springframework.stereotype.Service;

@Service
public class FooService {

    public boolean isOdd(int number) {
        return number % 2 != 0;
    }

    public Integer timesTwo(Integer val) {
        return val * 2;
    }
}
