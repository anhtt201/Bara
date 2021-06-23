package tran.tuananh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import tran.tuananh.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class BaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaraApplication.class, args);
	}

}
