package com.pedrao.pokeapi_webservice.model;


public class PokemonDTO {
    private String name;
    private int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public PokemonDTO(String nameOrId, int level) {
        this.name = nameOrId;
        this.level = level;
    }
}