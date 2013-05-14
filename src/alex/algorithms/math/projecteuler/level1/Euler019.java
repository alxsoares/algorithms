package alex.algorithms.math.projecteuler.level1;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Euler019 {

	public static void main(String[] args) {
		Long sundays = 0L;

        for (int year = 1901; year <= 2000; year++) {
            for (int month = 1; month <= 12; month++) {
                if ((new GregorianCalendar(year, month, 1)).get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    sundays++;
                }
            }
        }

        System.out.printf("%d\n",sundays);
	}

}
