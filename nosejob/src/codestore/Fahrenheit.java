package nosejob;

public class Fahrenheit implements HeatScalable {
    private double temperature;

    public Fahrenheit(double temperature) {
        this.temperature = temperature;
    }

    @Conversion(min = -459.67)
    @Invariant
    public double toFahrenheit() {
        return this.temperature;
    }

    @Conversion(min = 0)
    public double toKelvin() {
        return (this.temperature + 459.67) * 5 / 9;
    }

    @Conversion(min = -273.15)
    public double toCelsius() {
        return (this.temperature - 32) * 5 / 9;
    }

    @Override
    public String toString() {
        return formatter.format(this.temperature).concat("F");
    }

}
