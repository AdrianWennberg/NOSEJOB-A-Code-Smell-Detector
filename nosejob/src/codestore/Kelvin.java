package nosejob;

public class Kelvin implements HeatScalable {
    private double temperature;

    public Kelvin(double temperature) {
        this.temperature = temperature;
    }

    @Invariant
    @Conversion(min = 0)
    public double toKelvin() {
        return this.temperature;
    }

    @Conversion(min = -273.15)
    public double toCelsius() {
        return this.temperature - 273.15;
    }

    @Conversion(min = -459.67)
    public double toFahrenheit() {
        return this.temperature * 9 / 5 - 459.67;
    }



    @Override
    public String toString() {
        return formatter.format(this.temperature).concat("K");
    }

}
