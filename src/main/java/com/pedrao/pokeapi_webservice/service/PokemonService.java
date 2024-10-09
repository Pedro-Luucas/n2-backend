package com.pedrao.pokeapi_webservice.service;

import com.pedrao.pokeapi_webservice.model.PokemonDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private static final int MAX_POKEMONS = 6;
    private List<PokemonDTO> team = new ArrayList<>();

    public List<String> getTeam() {
        List<String> formattedTeam = new ArrayList<>();

        for (PokemonDTO pokemon : team) {
            String jsonFormat = String.format("{\"pokemon\": \"%s\", \"nivel\": %d}", pokemon.getName(), pokemon.getLevel());
            formattedTeam.add(jsonFormat);
        }

        return formattedTeam;
    }

    public void addPokemon(PokemonDTO pokemon) {
        if (team.size() >= MAX_POKEMONS) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O time já possui 6 pokémons.");
        }

        if (!pokemonExists(pokemon.getName())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokémon não encontrado.");
        }

        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemon.getName().toLowerCase();


        team.add(pokemon);
    }

    private boolean pokemonExists(String name) {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + name.toLowerCase();
        RestTemplate restTemplate = new RestTemplate();

        try {
            restTemplate.getForObject(apiUrl, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
