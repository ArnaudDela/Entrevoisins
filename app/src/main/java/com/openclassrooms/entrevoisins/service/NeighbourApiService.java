package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours or my favorites Neighbours
     *
     * @param isFavorite
     * @return {@link List}
     */
    List<Neighbour> getNeighbours(boolean isFavorite);

    /**
     * Delete a neighbour or remove a favorite neighbour
     *
     * @param neighbour
     * @param isFavorite
     */
    void deleteNeighbour(Neighbour neighbour, boolean isFavorite);

    /**
     * Add a neighbour or add a favorite neighbour
     *
     * @param neighbour
     * @param isFavorite
     */
    void addNeighbour(Neighbour neighbour, boolean isFavorite);
}
