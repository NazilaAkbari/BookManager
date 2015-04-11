package com.nazi;



import java.io.FileNotFoundException;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class App {

	
	
	
	
	public static void main(String[] args) throws Throwable {
		SpringApplication.run(App.class, args);
	}
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DataSource driverManagerDataSource = new DataSource();
		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
		driverManagerDataSource
				.setUrl("jdbc:postgresql://localhost:5432/bookDB");
		driverManagerDataSource.setUsername("postgres");
		driverManagerDataSource.setPassword("123456");
		return driverManagerDataSource;
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() throws FileNotFoundException
	
	{
		String keystoreFile="C:/Users/n.akbari";
        String keystorePassword="nazila   123";
        String keystoreType="PKCS12";
        String keystoreAlias="tomcat";
	    final String absoluteKeystoreFile = ResourceUtils.getFile(keystoreFile).getAbsolutePath();
	     
	    return (ConfigurableEmbeddedServletContainerFactory) -> {
			TomcatEmbeddedServletContainerFactory containerFactory = new TomcatEmbeddedServletContainerFactory();
	        containerFactory.addConnectorCustomizers((TomcatConnectorCustomizer) (Connector connector) -> {
	            connector.setSecure(true);
	            connector.setScheme("https");
	            connector.setAttribute("keystoreFile", absoluteKeystoreFile);
	            connector.setAttribute("keystorePass", keystorePassword);
	            connector.setAttribute("keystoreType", keystoreType);
	            connector.setAttribute("keyAlias", keystoreAlias);
	            connector.setAttribute("clientAuth", "false");
	            connector.setAttribute("sslProtocol", "TLS");
	            connector.setAttribute("SSLEnabled", true);
	        });
	    };
	}

}
