package APIlearn.APITest;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.InfluxDBFactory;

public class UpdateResults 
{

	  private static final InfluxDB INFLXUDB = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
	  private static final String DB_NAME = "selenium";
	  static {
	    INFLXUDB.setDatabase(DB_NAME);
	  }
	  public static void post(final Point point) {
	    INFLXUDB.write(point);
	  }
	
}
