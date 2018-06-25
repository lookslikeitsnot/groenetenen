package be.vdab.datasource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class TestDataSourceConfig {
	@Bean
	DataSource dataSource() {
//		return new DriverManagerDataSource("jdbc:mysql://localhost/groenetenen?useSSL=false", "cursist", "cursist");
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}
}
