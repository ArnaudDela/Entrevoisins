package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoriteNeighbours = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours(boolean isFavorite) {
        return isFavorite ? favoriteNeighbours : neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour, boolean isFavorite) {
        if (isFavorite){
            favoriteNeighbours.remove(neighbour);
        }
        else {
            neighbours.remove(neighbour);
            favoriteNeighbours.remove(neighbour);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNeighbour(Neighbour neighbour, boolean isFavorite) {
        if(isFavorite){
            favoriteNeighbours.add(neighbour);
        }
        else{
            neighbours.add(neighbour);
        }
    }
}