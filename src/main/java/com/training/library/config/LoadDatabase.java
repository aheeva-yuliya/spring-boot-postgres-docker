package com.training.library.config;

import com.training.library.model.Product;
import com.training.library.model.User;
import com.training.library.repository.ProductRepository;
import com.training.library.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ProductRepository productRepository, UserRepository userRepository) {

    return args -> {
      log.info("Preloading " + productRepository.save(new Product("Book", 5.36)));
      log.info("Preloading " + productRepository.save(new Product("Notebook", 1.27)));
      log.info("Preloading " + productRepository.save(new Product("Pen", 0.53)));
      log.info("Preloading " + productRepository.save(new Product("Ruler", 0.47)));

      log.info("Preloading " + userRepository.save(new User("yuliya.aheeva@gmail.com", "$2a$04$5QEI9QNmQtyfpzsTbwDl/u/BNp.sRDyBsZERFLlhlFA/4DAm2IEjO")));
    };

  }
}
