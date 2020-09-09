# GADS-Leaderboard
> This is the first pratice project for Google Africa Developers Scholarship(GADS) Android track. 
> The application displays the top 20 learners in the Learning Leaders’ category and top 20 learners in the Skill IQ Leaders’ category.

## How it works 
The application makes a get api request to the <a href="https://gadsapi.herokuapp.com/api/hours" target="_blank">**Learning leaders API endpoint**</a>
to fetch the learning leaders and another get api request to the <a href="https://gadsapi.herokuapp.com/api/skills" target="_blank">**Skills IQ leaders API endpoint**</a>
to fetch the skills IQ leaders. The data is fetched once and cached using an in-app database to support offline usage of the application. The pratice project is submitted through the app 
by using a post request to this <a href="https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse" target="_blank">**google form**</a>.

## Libraries used 
- [Room](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Glide](https://bumptech.github.io/glide/) - Glide is a fast and efficient image loading library for Android focused on smooth scrolling. Glide offers an easy to use API, a performant and extensible resource decoding pipeline and automatic resource pooling.
- [Android Jetpack](https://developer.android.com/jetpack) - Jetpack is a suite of libraries to help developers follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices so that developers can focus on the code they care about.

## How to use it 
Clone the repositiory on your local machine, then open the folder with <a href="https://developer.android.com/studio" target="_blank">**Android Studio 4.0**</a> or higher.
