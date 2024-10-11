package br.com.moreira.tabelafipe.controller;

import br.com.moreira.tabelafipe.model.DadosModelo;
import br.com.moreira.tabelafipe.model.DadosRepresentacaoVeiculo;
import br.com.moreira.tabelafipe.model.DadosVeiculo;
import br.com.moreira.tabelafipe.service.ConsumoAPI;
import br.com.moreira.tabelafipe.service.ConverterDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
        Scanner scanner = new Scanner(System.in);
        private ConsumoAPI consumo = new ConsumoAPI();

        private final String URL = "https://parallelum.com.br/fipe/api/v1/";
        private  ConverterDados conversor = new ConverterDados();

        public void Menu () {

            System.out.println("Qual veiculo deseja consultar:" +
                        "*Carros " +
                        "*Motos " +
                        "*Caminhoes");

            String opcao = scanner.nextLine().toLowerCase();

            String endereco = "";
               if (opcao.toLowerCase().contains("carr")) {
                       endereco = URL + "carros/marcas";
                   }
               else if (opcao.toLowerCase().contains("mot")) {
                       endereco = URL + "motos/marcas";
               }
               else if (opcao.toLowerCase().contains("caminhao")) {
                       endereco = URL + "caminhoes/marcas";
            }

            var json = consumo.obterHttp(endereco);
               var marcas = conversor.obterDadosList(json, DadosVeiculo.class);
                     marcas.stream()
                           .sorted(Comparator.comparing(DadosVeiculo::nome))
                           .forEach(e -> System.out.println(" Cód: " + e.codigo() + "  | Marca: " + e.nome()));


           System.out.println("===============================================");
           System.out.println("Informe o código da marca para consultar modelos:");
             var codigoMarca = scanner.nextLine();
           endereco = endereco+ "/" +codigoMarca+"/modelos";
           json = consumo.obterHttp(endereco);
           var ModeloLista = conversor.obterDados(json, DadosModelo.class);
                 System.out.println("Modelos dessa marca: ");
                   ModeloLista.modelos().stream()
                              .sorted(Comparator.comparing(DadosVeiculo::codigo))
                              .forEach(e -> System.out.println(" Cód: " + e.codigo() + " | Modelo: " + e.nome()));


           System.out.println("===============================================");
           System.out.println("Digite o nome do modelo que deseja consultar:");
             var nomeModelo = scanner.nextLine().toLowerCase();

             List<DadosVeiculo> modelosFiltrados = ModeloLista.modelos()
                   .stream()
                   .filter(m -> m.nome().toLowerCase().contains(nomeModelo.toLowerCase()))
                   .collect(Collectors.toList());

            System.out.println("Modelos do "+nomeModelo+": ");
            modelosFiltrados.forEach(e -> System.out.println(" Cód: " + e.codigo() + " | Modelo: " + e.nome()));

            System.out.println("Digite o codigo do modelo para buscar os valores e avalições:");
            String codigoModelo = scanner.nextLine().toLowerCase();
            endereco = endereco + "/" +codigoModelo+"/anos";
            json = consumo.obterHttp(endereco);
            List<DadosVeiculo> anos = conversor.obterDadosList(json, DadosVeiculo.class);
            List<DadosRepresentacaoVeiculo> veiculos = new ArrayList<>();

            for (int i = 0; i < anos.size(); i++) {
                var Enderecoanos = endereco + "/" + anos.get(i).codigo();
                json = consumo.obterHttp(Enderecoanos);
                DadosRepresentacaoVeiculo veiculo = conversor.obterDados(json, DadosRepresentacaoVeiculo.class);
                veiculos.add(veiculo);
            }
            veiculos.forEach(e -> System.out.println(
                           "   valor: " + e.valor()
                         + " | Marca: " + e.marca()
                         + " | Modelo: " + e.modelo()
                         + " | AnoModelo: " + e.AnoModelo()
                         + " | Combustivel: "  +  e.Combustivel()
                         + " | Mẽs de Referencia: " + e.MesReferencia()));

        }}