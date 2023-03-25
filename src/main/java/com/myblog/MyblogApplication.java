package com.myblog;

import com.myblog.entity.Role;
import com.myblog.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication  //starting point of the execution in my project and also some configuration has being done here
public class MyblogApplication implements CommandLineRunner {//112 STEP (implement)
	@Autowired
	public RoleRepository roleRepository;//113 STEP

	public static void main(String[] args) {
		SpringApplication.run(MyblogApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper() {//Java library class
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {//prepopulate the data ///114 STEP
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);

		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
	}
}