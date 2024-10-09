package com.pedrao.pokeapi_webservice.controller;

import com.pedrao.pokeapi_webservice.model.PokemonDTO;
import com.pedrao.pokeapi_webservice.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    // Rota GET para consultar o time atual de Pokémon
    @GetMapping("/team")
    public ResponseEntity<List<String>> getTeam() {
        return ResponseEntity.ok(pokemonService.getTeam());
    }

    // Rota POST para adicionar um Pokémon ao time
    @PostMapping("/add")
    public ResponseEntity<String> addPokemon(@RequestBody PokemonDTO pokemon) {
        pokemonService.addPokemon(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pokémon adicionado ao time!");
    }
}
