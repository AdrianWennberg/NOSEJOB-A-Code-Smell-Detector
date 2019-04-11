package nosejob;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public interface HeatScalable {
	NumberFormat formatter = new DecimalFormat("#0.00");

	double toCelsius();

	double toFahrenheit();

	double toKelvin();
}
