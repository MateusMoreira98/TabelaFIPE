package br.com.moreira.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosRepresentacaoVeiculo(@JsonAlias("Valor") String valor,
                                        @JsonAlias("Marca") String marca,
                                        @JsonAlias("Modelo") String modelo,
                                        @JsonAlias("AnoModelo") Integer AnoModelo,
                                        @JsonAlias("Combustivel") String combustivel,
                                        @JsonAlias("MesReferencia") String MesReferencia,
                                        @JsonAlias("Combustivel") String Combustivel) {

}
