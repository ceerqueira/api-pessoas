package br.com.attornatus.Pessoas.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfigModelMapper{

    @Bean
    public ModelMapper novoModelMapper() {
       ModelMapper modelMapper = new ModelMapper();
       return modelMapper;
    }
}