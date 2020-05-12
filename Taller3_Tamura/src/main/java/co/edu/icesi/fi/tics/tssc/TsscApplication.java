package co.edu.icesi.fi.tics.tssc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.service.TsscAdminService;

@SpringBootApplication
public class TsscApplication {
	/**
	 * Mayumi Tamura Hernandez
	 */
	 
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TsscApplication.class, args);	
	}
	
	@Bean
	public CommandLineRunner run(TsscAdminService tssAdminService) {
		return (args) -> {
			TsscAdmin superAdmin = new TsscAdmin();
			superAdmin.setUser("Mayumi");
			superAdmin.setPassword("{noop}1234");
			superAdmin.setSuperAdmin("superAdministrator");
			tssAdminService.saveAdmin(superAdmin);
			
			TsscAdmin admin = new TsscAdmin();
			admin.setUser("Leo");
			admin.setPassword("{noop}5678");
			admin.setSuperAdmin("administrator");
			tssAdminService.saveAdmin(admin);
		};
	}
}