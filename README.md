# Catchflicks 🎬

[![CircleCI](https://circleci.com/gh/anandwana001/catchflicks.svg?style=svg&branch=master)](https://app.circleci.com/pipelines/github/anandwana001/catchflicks)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/780e812d7e2e4d0aa46e9d1da8038c84)](https://app.codacy.com/manual/anandwana001/catchflicks?utm_source=github.com&utm_medium=referral&utm_content=anandwana001/catchflicks&utm_campaign=Badge_Grade_Dashboard)

[![GitHub license](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/anandwana001/catchflicks.svg?style=social&label=Star&maxAge=2592000)](https://GitHub.com/anandwana001/catchflicks/stargazers/)
[![GitHub forks](https://img.shields.io/github/forks/anandwana001/catchflicks.svg?style=social&label=Fork&maxAge=2592000)](https://GitHub.com/anandwana001/catchflicks/network)
[![GitHub watchers](https://img.shields.io/github/watchers/anandwana001/catchflicks.svg?style=social&label=Watch&maxAge=2592000)](https://GitHub.com/anandwana001/catchflicks/watchers)
[![GitHub followers](https://img.shields.io/github/followers/anandwana001.svg?style=social&label=Follow&maxAge=2592000)](https://github.com/anandwana001?tab=followers)
![Twitter Follow](https://img.shields.io/twitter/follow/akshay81844?label=Follow&style=social)


**Catchflicks** is a sample project after completing [Android Development Online Course For Professionals by MindOrks](https://bootcamp.mindorks.com/). 

This app shows the popular movies, upcoming movies and the ongoing movies in theatre from the movie db API.
The goal of this sample project is to implement most of the learning from the Bootcamp. Also, this project works as a kitchen sink project where anyone can play with any new Android API's and concepts.


## Screenshot 📱
<p float="left">
<img src="https://raw.github.com/anandwana001/catchflicks/master/screenshot/update_screenshot.jpg" width="200" height="400" />
<img src="https://raw.github.com/anandwana001/catchflicks/master/screenshot/detail_screen.jpg" width="200" height="400" />
<img src="https://raw.github.com/anandwana001/catchflicks/master/screenshot/search_frag.jpg" width="200" height="400" />
<img src="https://raw.github.com/anandwana001/catchflicks/master/screenshot/search_result.jpg" width="200" height="400" />
</p>


## Architecture
Project consist of multiple packages in order to achieve MVVM architecture.
* data
* di
* ui
* utils


## Tech-stack 🛠
This project use multiple libraries to bring easy way of implementation
- [Dagger2](https://dagger.dev/) - Dependency Injection Framework
- [Retrofit](https://square.github.io/retrofit/) - Used for Networking
- [Timber](https://github.com/JakeWharton/timber) - Helps in logging 
- [Glide](https://github.com/bumptech/glide) - use to load image
- [RxJava](https://github.com/ReactiveX/RxJava) - to perform asynchronous work
- [GSON](https://github.com/google/gson) - use to serialize and deserialize Java objects to JSON
- [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - manage activity and fragment lifecycle
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - observable data holder
- [AndroidX](https://developer.android.com/jetpack/androidx) - android library for core functionalities
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - manage data in lifecycle aware fashion
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android
- [Data Binding](https://developer.android.com/topic/libraries/data-binding) - helps to bind UI with data source
- [Palette API](https://developer.android.com/training/material/palette-colors) - dynamically change app color scheme


## Development Setup ⚙️
This project uses the [TMDb API](https://developers.themoviedb.org/4/getting-started) to fetch movies data.<br>
To begin with the setup, firstly you need to create an API key. 

1. Create an account at [themoviedb.org](https://www.themoviedb.org/documentation/api)
1. Go to settings from the profile icon
1. Click on API
1. Click on create

```
# Insert at ~/.gradle/gradle.properties or catchflicks/gradle.properties
API_KEY=<insert>
```
This project uses the Dagger2 for dependency Injection. After opening this project in your Android Studio you might get some error which is due unavailability of few classes. You need to `make project` or try to build the project, this will generate all the required classes for dagger. 


## Custom ViewModelProviderFactory 🦾🦿
I had created a custom `ViewModelProviderFactory` class because I need to pass arguments in my View Model class constructor. 

- Default -> `ViewModelProviders.of(this).get(MyViewModel.class);`
- `ViewModelProviders.of`, this depends upon a `ViewModelStore` and a `ViewModelFactory`
- `return new ViewModelProvider(activity.getViewModelStore(), factory);`
- `getViewModelStore()` under the hood uses `onRetainNonConfigurationInstance()` method and `SavedStateRegistryController` class which calls `performRestore()` and `performSave()` method of `SavedStateRegistry` class. 
- `ViewModelStore` store view model using `HashMap<String, ViewModel>`
- So, if we have arguments in our view model class constructor then we create custom `ViewModelProviderFactory`.

<br>

```
class ViewModelProviderFactory<T : ViewModel>(
    private val kClass: KClass<T>,
    private val creator: () -> T
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(kClass.java)) return creator() as T
        throw IllegalArgumentException("Unknown class name")
    }
}
```

```
    ViewModelProviders.of(activity, ViewModelProviderFactory(MainViewModel::class){
            // MainViewModel(PARAM_1,PARAM_2)
        }).get(MainViewModel::class.java)
```

- We are extending our custom class with `ViewModelProvider.NewInstanceFactory()`.
- This is used to create the instance of the given class. 
- This by default returns class object with empty argument list. `return modelClass.newInstance();`
- **KClass** is the holder of a class of type ViewModel that needs to be injected.
- Instances of **KClass** class are obtainable by the `::class` syntax.
- This is the Lambda function, this is provided by the ActivityModule/FragmentModule, when creator lambda is called then that module creates and return the instance of ViewModel.


## Work in Progress 🚧🛠🚧
- [ ] Search feature
- [ ] Unit Testing
- [ ] UI Testing, using espresso and mockito
- [ ] Update Region option
- [ ] Update Language option
- [ ] Try Pagination API
- [ ] Dark Mode
- [ ] Try Navigation Component
- [ ] Coroutine
- [ ] Better UI
- [ ] Bookmark Movie


## Contact 🔗
Let's connect here -> [akshayn.app](https://akshayn.app/)


## License 📝
```
MIT License

Copyright (c) 2020 Akshay Nandwana

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
