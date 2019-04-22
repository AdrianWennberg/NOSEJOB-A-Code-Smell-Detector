package nosejob;

/**
 * @author bacci
 * */
public class Celsius implements HeatScalable {
    private double temperature;

    public Celsius(double temperature){
        this.temperature = temperature;
    }

    @Invariant @Conversion (min = -273,
            max = 1000000000)
    public double toCelsius() {
        return temperature;
    }

    @Conversion(min = -459, //-273 is fahrenheit
            max = 1800000032) // 1B in Fahrenheit
    public double toFahrenheit() {
        return (1.8*temperature)+32;
    }

    @Conversion(min = 0, //There is no negative in the Kelvin scale
            max = 1000000000)     //1.5M is the temperature of the sun, so 1B is a reasonable maximum.
    public double toKelvin() {
        return temperature + 273.15;
    }

    @Override
    public String toString(){
        return HeatScalable.formatter.format(temperature).concat("C");
    }
}
