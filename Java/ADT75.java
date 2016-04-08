// Distributed with a free-will license.
// Use it any way you want, profit or free, provided it fits in the licenses of its associated works.
// ADT75
// This code is designed to work with the ADT75_I2CS I2C Mini Module available from ControlEverything.com.
// https://www.controleverything.com/content/Temperature?sku=ADT75_I2CS#tabs-0-product_tabset-2

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

public class ADT75
{
	public static void main(String args[]) throws Exception
	{
		// Create I2C bus
		I2CBus Bus = I2CFactory.getInstance(I2CBus.BUS_1);
		// Get I2C device, ADT75 I2C address is 0x48(72)
		I2CDevice device = Bus.getDevice(0x48);
		Thread.sleep(500);
		
		// Read 2 bytes of data
		byte[] data = new byte[2];
		device.read(0x00, data, 0, 2);
		
		// Convert the data to 12-bits
		int temperature = ((data[0] & 0xFF) * 256 + (data[1] & 0xFF)) / 16;
		double cTemp = temperature * 0.0625;
		double fTemp = (temperature * 0.1125) +32;
		
		// Output data to screen
		System.out.printf("Temperature in Celsius : %.2f C %n", cTemp);
		System.out.printf("Temperature in Fahrenheit : %.2f F %n", fTemp);
	}
}