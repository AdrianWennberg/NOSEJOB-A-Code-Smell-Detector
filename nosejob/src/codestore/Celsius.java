package nosejob;

public class Celsius implements HeatScalable {
    private double temperature;

    public Celsius(double temperature) {
        this.temperature = temperature;
    }

    @Invariant
    @Conversion(min = -273.15)
    public double toCelsius() {
        return this.temperature;
    }

    @Conversion(min = -459.67)
    public double toFahrenheit() {
        return this.temperature * 9 / 5 + 32;
    }

    @Conversion(min = 0)
    public double toKelvin() {
        return this.temperature + 273.15;
    }

    @Override
    public String toString() {
        return formatter.format(this.temperature).concat("C");
    }
}
