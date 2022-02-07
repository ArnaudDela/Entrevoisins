package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours(false);
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void getFavoriteNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours(true);
        assertTrue(neighbours.isEmpty());
    }

    @Test
    public void deleteNeighbourWithSuccess() {// false: manipuler liste globale
        Neighbour neighbourToDelete = service.getNeighbours(false).get(0);
        service.deleteNeighbour(neighbourToDelete, false);
        assertFalse(service.getNeighbours(false).contains(neighbourToDelete));
    }

    // Pour manipuler la liste des favoris il faut utiliser true
    @Test
    public void deleteFavoriteNeighbourWithSuccess() {
        // Recuperer un voisin de la liste globale
        Neighbour neighbourToAdd = service.getNeighbours(false).get(0);
        // Ajouter l'element selectionné dans la liste favoris
        service.addNeighbour( neighbourToAdd , true );
        // Supprimer le voisin de la liste favoris
        Neighbour neighbourToDelete = service.getNeighbours(true).get(0);
        service.deleteNeighbour(neighbourToDelete, true);
        assertFalse(service.getNeighbours(true).contains(neighbourToDelete));
    }

    @Test
    public void addNeighbourWithSuccess() {
        // declarer et instancier un voisin
        Neighbour neighbourToAdd = new Neighbour(0, "test", "avatarTest", "adressTest",
               "TelTest", "aboutMeTest");
        // Ajouter l'element selectionné dans la liste initial
        service.addNeighbour( neighbourToAdd , false );

        assertTrue(service.getNeighbours(false).contains(neighbourToAdd));
    }

    @Test
    public void addFavoriteNeighbourWithSuccess() {
        // declarer et instancier un voisin
        Neighbour neighbourToAdd = new Neighbour(0, "test", "avatarTest", "adressTest",
                "TelTest", "aboutMeTest");
        // Ajouter l'element selectionné dans la liste favori
        service.addNeighbour( neighbourToAdd , true );

        assertTrue(service.getNeighbours(true).contains(neighbourToAdd));
    }
}
