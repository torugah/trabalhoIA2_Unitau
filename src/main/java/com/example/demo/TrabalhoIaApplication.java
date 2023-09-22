package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrabalhoIaApplication {

	private List<String> cidades = lerNomesCidades();
	private List<List<String>> cidadesPorLinha = lerCidadesPorLinha();
	private List<Map<String, Integer>> cidadesPorLinhaCusteado = lerCidadesPorLinhaCusteado();

	public TrabalhoIaApplication() {
	}

	public List<List<String>> getCidadesPorLinha() {
		return cidadesPorLinha;
	}

	public List<String> getCidades() {
		return cidades;
	}

	public List<Map<String, Integer>> getCidadesPorLinhaCusteado(){
		return cidadesPorLinhaCusteado;
	}

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoIaApplication.class, args);
	}

	private List<String> lerNomesCidades() {
		List<String> cidades = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\valePaulista.txt"))) {
			String linha = br.readLine();
			if (linha != null) {
				String[] nomes = linha.split(",");
				for (String nome : nomes) {
					cidades.add(nome.trim());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cidades;
	}

	public List<List<String>> lerCidadesPorLinha() {
		List<List<String>> cidadesPorLinha = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\valePaulista.txt"))) {
			// Ignorar a primeira linha
			br.readLine();

			String linha;
			while ((linha = br.readLine()) != null) {
				String[] nomes = linha.split(",");
				List<String> cidades = new ArrayList<>();
				for (String nome : nomes) {
					cidades.add(nome.trim());
				}
				cidadesPorLinha.add(cidades);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cidadesPorLinha;
	}

	public List<Map<String, Integer>> lerCidadesPorLinhaCusteado() {
		List<Map<String, Integer>> cidadesPorLinha = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\valePaulistaCusteado.txt"))) {
			// Ignorar a primeira linha
			br.readLine();

			String linha;
			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(",");
				Map<String, Integer> cidadesComPeso = new HashMap<>();

				for (int i = 1; i < partes.length; i++) {
					String[] cidadePeso = partes[i].split(":");
					String cidade = cidadePeso[0].trim();
					int peso = Integer.parseInt(cidadePeso[1].trim());
					cidadesComPeso.put(cidade, peso);
				}

				cidadesPorLinha.add(cidadesComPeso);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cidadesPorLinha;
	}

}
