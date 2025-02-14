## Pokemon List
Android app that fetches pokemon data from [pokeapi](https://pokeapi.co/docs/v2#pokemonstat)

The app is made using MVVM architecture, Retrofit to perform api calls and Room as a database to provide a caching feature.
The app contains two screens: a main screen listing pokemons with pagination and a pokemon details screens, presenting some details of the selected pokemon.
The cache is only implemented on the main list screen, and it'll respect the pagination. 
If any errors occur on the api/database calls, the app will present a generic error with a try again button to the user.
