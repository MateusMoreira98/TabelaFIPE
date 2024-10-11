package br.com.moreira.tabelafipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ICoverterDadosVeiculos {
    <T> T obterDados(String json, Class<T>classe);

    <T> List<T> obterDadosList(String json, Class<T>classe) throws JsonProcessingException;
}
