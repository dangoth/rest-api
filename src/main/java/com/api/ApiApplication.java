package com.api;

import com.api.repository.entity.Customers;
import com.api.service.CustomersService;
import com.api.service.OrderDetailsService;
import com.api.service.OrdersService;
import com.api.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/*@Bean
	CommandLineRunner init(CustomersService customersService, OrderDetailsService orderDetailsService,
						   OrdersService ordersService, ProductService productService) {
		return (args) -> {
			Customers customer1 = customersService.addCustomer("John Doe Inc.", "John",
					"Doe", "555-12-34-61", "13 Grove Rd.",
					"Kansas City, MO", "64030", "United States");


			Customers customer2 = customersService.addCustomer("Bradbury Shaw Ltd", "Thomas",
					"Bradbury", "08-231-66-23", "78 Farrington Estate",
					"Birmingham", "3A C23", "United Kingdom");

			Customers customer3 = customersService.addCustomer("German Steel Inc",
					"Rudolf", "Springer", "1-32-264-23-61",
					"Torenweg 112", "Duisburg", "002351", "Germany");

			Customers customer4 = customersService.addCustomer("Koninklijke BAM", "Guido",
					"van Zandt", "06-232-26-7112", "Stationsplein 23",
					"Amersfoort", "2734VZ", "The Netherlands");

		};
	}*/
}




