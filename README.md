Entrevoisins


Technologies and IDE

    Langage : Java
    Frameworks : Butterknife, Gson, Glide, EventBus, Expresso
    IDE : Android Studio

Compile and run the app

    Download the zip folder of the code from this repository
    Extract all its files
    Open Entrevoisins with Android Studio. 
    Launch the app on a device or emulator with "run 'app'"

App description

This application is developed for Android devices.

Its main activity ListNeighbourActivity contains a fragment with a RecyclerView which displays a list of neighbours with their avatars and names. A ViewPager is implemented to swipe between this list and the list of the favourite neighbours.

Each item contains a delete button to remove a neighbour from the neighbour list, or from the favourites list.

This activity contains an add button which launches AddNeighbourActivity. AddNeighbourActivity displays a random avatar and some TextInputs in order to create a new neighbour by clicking on a button "save".

When an item of the list is clicked, the DetailNeighbourActivity is launched.

The DetailNeighbourActivity features a back button, the avatar, the name, the adress, the phone number, the social adress and the description of the neighbour. 
Also featured, the " Favourite" button that manages the addition or deletion of the neigbhour from the favourite list.


Tests

Unit tests and unit instrumented tests have been developed for the main features of the app. The application can compile and execute and all features of the above description work.
