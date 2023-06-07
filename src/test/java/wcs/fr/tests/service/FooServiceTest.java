package wcs.fr.tests.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FooServiceTest {

    // Inject Mocks -> Spring bean injection
    @InjectMocks
    private FooService fooService;

    // JUNIT 5
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test methods");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test method");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each test method");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all test methods");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE}) // six numbers
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        assertTrue(fooService.isOdd(number));
    }


    @Test
    void lambdaExpressions() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        assertTrue(numbers.stream()
                .mapToInt(Integer::intValue)
                .sum() > 5, () -> "Sum should be greater than 5");
    }

    @Test
    void groupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
                () -> assertEquals(numbers[0], 0),
                () -> assertEquals(numbers[3], 3),
                () -> assertEquals(numbers[4], 4)
        );
    }

    @Test
    void shouldThrowException() {

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> fooService.throwException());

        assertEquals("Not supported", exception.getMessage());
    }

    @Test
    void assertThrowsException() {
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> {
            Integer.valueOf(str);
        });
    }

    /*
     * DYNAMIC TEST
     */

    List<Integer> in = Arrays.asList(1,2);

    List<Integer> out = Arrays.asList(2,4);

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromStream() {
        return in.stream()
                .map(value ->
                        DynamicTest.dynamicTest("Test translate " + value, () -> {
                            int id = in.indexOf(value);
                            assertEquals(out.get(id), fooService.timesTwo(value));
                        })
                );
    }

}