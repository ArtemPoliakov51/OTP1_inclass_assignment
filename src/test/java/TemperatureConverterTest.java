import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureConverterTest {

    private TemperatureConverter converter;

    @BeforeEach
    void setUp() {
        converter = new TemperatureConverter();
    }

    @Test
    void testFahrenheitToCelsius_basicCases() {
        assertEquals(0.0, converter.fahrenheitToCelsius(32.0), 1e-9);
        assertEquals(100.0, converter.fahrenheitToCelsius(212.0), 1e-9);
        assertEquals(-40.0, converter.fahrenheitToCelsius(-40.0), 1e-9);
    }

    @Test
    void testFahrenheitToCelsius_moreCases() {
        assertEquals(37.0, converter.fahrenheitToCelsius(98.6), 1e-9);
        assertEquals(20.0, converter.fahrenheitToCelsius(68.0), 1e-9);
    }

    @Test
    void testCelsiusToFahrenheit_basicCases() {
        assertEquals(32.0, converter.celsiusToFahrenheit(0.0), 1e-9);
        assertEquals(212.0, converter.celsiusToFahrenheit(100.0), 1e-9);
        assertEquals(-40.0, converter.celsiusToFahrenheit(-40.0), 1e-9);
    }

    @Test
    void testCelsiusToFahrenheit_moreCases() {
        assertEquals(98.6, converter.celsiusToFahrenheit(37.0), 1e-9);
        assertEquals(68.0, converter.celsiusToFahrenheit(20.0), 1e-9);
    }

    @Test
    void testIsExtremeTemperature_trueCases() {
        assertTrue(converter.isExtremeTemperature(-41.0));
        assertTrue(converter.isExtremeTemperature(51.0));
        assertTrue(converter.isExtremeTemperature(-100.0));
        assertTrue(converter.isExtremeTemperature(200.0));
    }

    @Test
    void testIsExtremeTemperature_falseCases_andBoundaries() {
        assertFalse(converter.isExtremeTemperature(-40.0));
        assertFalse(converter.isExtremeTemperature(50.0));

        assertFalse(converter.isExtremeTemperature(0.0));
        assertFalse(converter.isExtremeTemperature(20.0));
        assertFalse(converter.isExtremeTemperature(-10.0));
    }
    @Test
    void testKelvinToCelsius_basicCases() {
        assertEquals(0.0, converter.kelvinToCelsius(273.15), 1e-9);
        assertEquals(100.0, converter.kelvinToCelsius(373.15), 1e-9);
        assertEquals(-273.15, converter.kelvinToCelsius(0.0), 1e-9);
    }

    @Test
    void testKelvinToCelsius_exampleCase() {
        assertEquals(26.85, converter.kelvinToCelsius(300.0), 1e-9);
    }
}
