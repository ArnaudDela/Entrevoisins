
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.hamcrest.core.IsNull.notNullValue;

import java.util.List;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;
    private List<Neighbour> listNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When we click on item, the detail page is opned and show detail info
     */
    @Test
    public void showDetail() {
        // choisir un voisin
        Neighbour neighbour = listNeighbours.get(1);

        // Lancer la vue detail
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

       // verifier que le textview n'est pas vide est egale au nom du voisin de la position 1
        onView(ViewMatchers.withId(R.id.activity_detail_neighbour_first_name)).check(matches(withText(neighbour.getName())));
    }

    /**
     * When we click on item, the detail page is opned and show detail info
     */
    @Test
    public void onlyMyFavorite() {

        // s'assurer que la liste est vide
        onView(ViewMatchers.withId(R.id.favorite_list_neighbours)).check(withItemCount(0));

        // voir la vue detail voisin
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

        // cliquer sur le bouton favoris
        onView(withId(R.id.fav_btn)).perform(click());
        // revenir sur la vue listVoisin : cliquer sur back
        onView(withId(R.id.back_btn)).perform(click());
        // selectionner la tabulation des favoris ou bien scoller à droite
        onView(withId(R.id.container)).perform(scrollRight());

        // verifier que la list favoris contienne un element et c'est bien l'element qu'on a ajouté
        onView(ViewMatchers.withId(R.id.favorite_list_neighbours)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.favorite_list_neighbours)).check(withItemCount(1));
    }

}