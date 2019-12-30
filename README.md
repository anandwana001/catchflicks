# Catchflicks
Catchflicks is a sample project after completing [Android Development Online Course For Professionals by MindOrks](https://bootcamp.mindorks.com/). 

The goal of this sample project is to implement all the learning from the Bootcamp. Also, to implement the latest android concepts, this project works as a playground app.

Project consist of multiple packages in order to achieve mvvm architecture.
* data
* di
* ui
* utils

### Custom ViewModelProviderFactory
I had created a custom `ViewModelProviderFactory` class because I need to pass arguments in my View Model class constructor. 

- Default -> `ViewModelProviders.of(this).get(MyViewModel.class);`
- `ViewModelProviders.of`, this depends upon a `ViewModelStore` and a `ViewModelFactory`
- `return new ViewModelProvider(ViewModelStores.of(fragment), factory);`
- `ViewModelStore` store with a `HashMap<String, ViewModel>`
- `ViewModelFactory` is using reflection to instantiate the ViewModel
- `AndroidViewModelFactory` used in `ViewModelProviders.of` overrides a generic `ViewModelFactory`
- `ViewModelStoreOwner` helps in surviving the configuration changes using Holder class and this whole gives us the viewModel instance without the parameter.
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


### Tech-stack
This project use multiple libraries to bring easy way of implementation.
* [Dagger2](https://dagger.dev/)
* [Retrofit](https://square.github.io/retrofit/)
* [Timber](https://github.com/JakeWharton/timber)
* [Glide](https://github.com/bumptech/glide)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [GSON](https://github.com/google/gson)
* [Room](https://developer.android.com/topic/libraries/architecture/room)
* [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [AndroidX](https://developer.android.com/jetpack/androidx)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Material Design](https://material.io/develop/android/)
* [Data Binding](https://developer.android.com/topic/libraries/data-binding)
* [Palette API](https://developer.android.com/training/material/palette-colors)

### Screenshot
<p float="left">
<img src="https://raw.github.com/anandwana001/catchflicks/master/screenshot/first_screenshot.jpg" width="200" height="400" /> 
<img src="https://raw.github.com/anandwana001/catchflicks/master/screenshot/update_screenshot.jpg" width="200" height="400" />
<img src="https://raw.github.com/anandwana001/catchflicks/master/screenshot/detail_screen.jpg" width="200" height="400" />
</p>

### Work in Progress
- [x] Detail Screen
- [x] Share Item
- [ ] Genre List Screen
- [ ] Search feature
- [ ] Unit Testing
- [ ] Update Region option
- [ ] Update Language option
- [ ] Try Pagination Api by Jetpack
- [ ] Dark Mode
- [ ] Try Navigation Component
- [ ] Better UI
- [ ] Bookmark Movie
- [ ] Try Coil lib
