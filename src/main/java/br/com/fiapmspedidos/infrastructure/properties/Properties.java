package br.com.fiapmspedidos.infrastructure.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Properties {

    // APPLICATION
    @Value("${application.msprodutos.host}")
    private String msProdutosHost;

    @Value("${application.msclientes.host}")
    private String msClientesHost;

}
