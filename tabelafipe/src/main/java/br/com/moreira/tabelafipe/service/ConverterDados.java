package br.com.moreira.tabelafipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverterDados implements ICoverterDadosVeiculos {


 private ObjectMapper mapper = new ObjectMapper();


@Override
   public <T> T obterDados(String json, Class<T> classe) {
    try {
        return mapper.readValue(json, classe);
    } catch (JsonProcessingException e) {
        throw new RuntimeException("Erro ao processar JSON: " + e.getMessage());
    }


}

    @Override
    public <T> List<T> obterDadosList(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);
             try {
                return mapper.readValue(json,lista);
             } catch (JsonProcessingException e) {
                 throw new RuntimeException("Erro ao processar lista em JSON: " + e.getMessage());}
    }
}
