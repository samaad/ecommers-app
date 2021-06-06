package org.my.ecom;

import org.my.ecom.entities.Categories;
import org.my.ecom.entities.Persons;
import org.my.ecom.entities.Product;
import org.my.ecom.repository.CategoriesRepository;
import org.my.ecom.repository.PersonRepository;
import org.my.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class EcomAssismentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomAssismentApplication.class, args);
    }

}
