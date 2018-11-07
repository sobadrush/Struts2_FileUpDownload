package _00_config;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.DataSource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = {})
@PropertySource(value = { "classpath:database.properties" }, encoding = "utf-8")
public class RootConfig {

	@Autowired
	private Environment springEnv;

	@Bean
	public DataSource driverManagerDS() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUsername(springEnv.getProperty("mssql.home.username"));
		ds.setPassword(RootConfig.Util.doDecode(springEnv.getProperty("mssql.home.password")));
		ds.setDriverClassName(springEnv.getProperty("mssql.home.driverClass"));
		ds.setUrl(springEnv.getProperty("mssql.home.url"));
		return ds;
	}

	public static final class Util {

		private final static Base64.Decoder decoder = Base64.getDecoder();
		private final static Base64.Encoder encoder = Base64.getEncoder();

		public static String doEncode(String unencstr) {
			return encoder.encodeToString(unencstr.getBytes());
		}

		public static String doDecode(String encStr) {
			try {
				return new String(decoder.decode(encStr), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				System.err.println(ExceptionUtils.getStackTrace(e));
			}
			return encStr;
		}

	}

	public static void main(String[] args) throws SQLException {

//		System.out.println(RootConfig.Util.doEncode("sa123456"));
//		System.out.println(RootConfig.Util.doDecode("c2ExMjM0NTY="));

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
		DataSource ds = context.getBean("driverManagerDS", DataSource.class);
		System.out.println("DatabaseProductName >>> " + ds.getConnection().getMetaData().getDatabaseProductName());
		context.close();
	}

}
