package br.com.fiapmspedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients({"br.com.fiapmspedidos.infrastructure.*", "br.com.fiapmspedidos.repository.*"})
public class FiapMspedidosApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiapMspedidosApplication.class, args);
    }

}
