package com.pedrao.pokeapi_webservice;

import com.pedrao.pokeapi_webservice.model.PokemonDTO;
import com.pedrao.pokeapi_webservice.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PokeapiWebserviceApplication implements CommandLineRunner {

	@Autowired
	private PokemonService pokemonService;

	public static void main(String[] args) {
		SpringApplication.run(PokeapiWebserviceApplication.class, args);
	}

	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		System.out.println("Crie Seu Time de Pokemon! ");
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");

		while (running) {
			System.out.println("--------------------------------");
			System.out.println("Escolha uma opção:");
			System.out.println("0: POST (Adicionar Pokémon)");
			System.out.println("1: GET (Consultar time)");
			System.out.println("2: Sair");
			System.out.println("--------------------------------\n");
			int choice = scanner.nextInt();
			scanner.nextLine();  // Consumir a nova linha

			switch (choice) {
				case 0:
					// Operação POST
					System.out.print("Digite o nome ou número do Pokémon: ");
					String nameOrId = scanner.nextLine();
					System.out.print("Digite o nível do Pokémon: ");
					int level = scanner.nextInt();
					scanner.nextLine();  // Consumir a nova linha

					try {
						PokemonDTO newPokemon = new PokemonDTO(nameOrId, level);
						pokemonService.addPokemon(newPokemon);
						System.out.println("Pokémon adicionado ao time!");
					} catch (Exception e) {
						System.out.println("Erro: " + e.getMessage());
					}
					break;

				case 1:
					if(pokemonService.getTeam().isEmpty()){
						System.out.println("Você não tem Pokemons no seu time! ");
					}
					else {
						System.out.println("Time atual de Pokémon: " + pokemonService.getTeam());
						break;
					}
				case 2:
					// Sair do programa
					running = false;
					System.out.println("Saindo do programa...");
					break;

				default:
					System.out.println("Opção inválida! Tente novamente.");
					break;
			}
		}

		scanner.close();
	}
}
