# TV Show Challenge - *MovieCompose*

**MovieCompose** is an android app that uses the TMDB API to display now playing tv shows from different category.


## API key 🔑
You'll need to provide API key to fetch data from TheMovieDataBase API.

- Add API key to project
- Create new file named -> `tmdb.properties` in our project app/ folder
- Add the API key as shown below:
```
    TMDB_API_KEY=abcdexxx
```
- Build the app 🚀


## User Stories

The following **required** functionality is completed:

* [x] Display a splash screen as soon as the app starts.
* [x] Display a list of TV Shows:
    * [x] a. Every item should contain a rating, photo, and title of the show.
    * [x] b. The User should be able to filter TV Shows by Popular, Top Rated, On TV, or Airing Today.
    * [x] c. Lists should be paginated.
* [x] Display a TV Show Details Screen:
    * [x] d. After tapping on a list item, the user should be able to see the show's details.
    * [x] e. The screen header view must contain the show’s poster, its rating, and summary.
    * [x] f. The body of the screen should display a list containing all seasons of the show.


The following **things to consider** features are implemented:

* [ ] You should persist all information possible on the local cache (use Room).
* [x] Internet connection errors should be handled.
* [x] You should cache images and display a placeholder while loading.
* [ ] Meaningful unit tests must be added.
* [x] Code quality, architecture, and project structure will be evaluated.



The following **bonus** features are implemented:

* [ ] Reach 70% of unit test coverage throughout all the project’s codebase.
* [ ] Add tablet support.
* [ ] Use a Collapsing Toolbar animation on the details screen
* [ ] Use of at least one animation
* [ ] Manage light and Dark Theme
* [ ] Store Favorite TV Shows on the local database

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://i.imgur.com/c6qbWmP.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />




## Libraries and tools 🛠

VaroMovieChallenge App uses libraries and tools used to build Modern Android application, mainly part of Android Jetpack 🚀

- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Dagger-hilt](https://dagger.dev/hilt/)🗡
- [Flow](https://developer.android.com/kotlin/flow)
- [Coil](https://coil-kt.github.io/coil/)
- [Timber](https://github.com/JakeWharton/timber)
- [Retrofit](https://square.github.io/retrofit/)
- Architecture components
- Other [Android Jetpack](https://developer.android.com/jetpack) components


## Architecture

The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.

Read more:
- [Building Modern Android Apps with Architecture Guidelines](https://medium.com/@aky/building-modern-apps-using-the-android-architecture-guidelines-3238fff96f14)
- [Guide to app architecture](https://developer.android.com/jetpack/docs/guide)




## License

    Copyright 2023 Jephte Colin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.