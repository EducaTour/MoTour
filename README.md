![EducaTour Screen](https://github.com/EducaTour/MoTour/assets/101736662/e9cbe709-4d25-411a-a53b-6d8d84f4ff95)# Terralysis Mobile Development

## Architecture

<p align="center">
    <img src="https://github.com/EducaTour/MoTour/assets/101736662/632ef5e7-6553-4f00-92e4-d9153d9a7ce8" alt="Architecture">
</p>

## App Screens/Components

<p align="center">
    <img src="https://github.com/EducaTour/MoTour/assets/101736662/874214da-8f5b-4705-a8f1-beeac0d383e1" alt="EducaTour Screen">  
</p>

## Features

- Splash Screen\
  Upon launching the application, a visually appealing splash screen featuring the EducaTour logo is displayed.
- Home\
  The home screen serves as the central hub of the application, displaying a list of 25 notable landmarks. Users can easily access key features and guides from this screen.
- Detail\
  This screen is displayed after selecting a landmark from the home screen list. It provides comprehensive information about the chosen landmark, including its name, description, historical background, location, photographs, unique activities available, opening hours, ticket prices, and contact information.
- Scanner\
  The scanning feature allows users to capture images of historical sites using the device's camera or by selecting images from the gallery. The application analyzes these images to provide detailed information regarding the site's historical significance and key facts.
- Change Language\
  Users can change the application's language preference between Indonesian, English, and Chinese, allowing them to interact with the app in their preferred language.
- About\
  The about section provides information about EducaTour, including its purpose, mission, and the current version of the application.
- FAQ\
  A section dedicated to frequently asked questions to assist users with common inquiries about the application’s features and functionalities.

## Permission

- Internet\
  The application requires internet access to communicate with the server and retrieve data for various features, including historical list, historical detail information, and historical landmark Scanner.
- Camera\
  Camera permission is needed for the application to use the device's camera to capture images of historical sites.
- Read External Storage\
  Permission to read external storage is necessary for the application to access the device's gallery, allowing users to select and upload images of historical sites.
  
## Build With

- [Kotlin](https://kotlinlang.org) - Version 2.0.0
- [RecyclerView](https://developer.android.com/develop/ui/views/layout/recyclerview)
- [Retrofit2](https://github.com/square/retrofit) - Version 2.11.0
- [GSON Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) - Version 2.11.0
- [OkHttp3](https://github.com/square/okhttp) - Version 4.12.0
- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Version 2.8.1
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Version 2.8.1
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Version 2.8.1
- [Navigation Component](https://developer.android.com/guide/navigation/get-started) - Version 2.7.7
- [Glide](https://github.com/bumptech/glide) - Version 4.16.0
- [Room Database](https://developer.android.com/jetpack/androidx/releases/room) - Version 2.6.1
- [DataStore](https://developer.android.com/jetpack/androidx/releases/datastore) - Version 1.1.1
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Version 1.9.0-RC
- [CircleImageView](https://github.com/hdodenhof/CircleImageView) - Version 3.1.0
- [SplashScreen](https://developer.android.com/jetpack/androidx/releases/core) - Version 1.0.1
- [Core KTX](https://developer.android.com/jetpack/androidx/releases/core) - Version 1.13.1
- [JUnit](https://junit.org/junit4/) - Version 4.13.2
- [AppCompat](https://developer.android.com/jetpack/androidx/releases/appcompat) - Version 1.7.0
- [Material Components](https://github.com/material-components/material-components-android) - Version 1.12.0
- [Activity KTX](https://developer.android.com/jetpack/androidx/releases/activity) - Version 1.9.0
- [ConstraintLayout](https://developer.android.com/jetpack/androidx/releases/constraintlayout) - Version 2.1.4
- [Process Phoenix](https://github.com/JakeWharton/ProcessPhoenix) - Version 3.0.0
- [Dagger](https://dagger.dev) - Version 2.51.1
- [Legacy Support V4](https://developer.android.com/jetpack/androidx/releases/legacy) - Version 1.0.0
- [Fragment KTX](https://developer.android.com/jetpack/androidx/releases/fragment) - Version 1.7.1
- [DataBinding](https://developer.android.com/topic/libraries/data-binding) - Version 8.4.1
- [ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP) - Version 1.4.7

## Requirement

- Android Studio
- Target device (Android device or emulator) with minimum android version of 7.0 (Nougat) or higher

## Installation

1. **Download or Clone this project to your computer**
   
    - **Repository Link:** [EducaTour/MoTour](https://github.com/EducaTour/MoTour.git)
      
    - **Clone the repository:**
    
        ```sh
        git clone https://github.com/EducaTour/MoTour.git
        ```
        
        **-OR-**

    - **Download the repository:**
      - On the GitHub repository page, click on the “Code” button and select “Download ZIP”.
      - Extract the downloaded ZIP file to your computer.

2. **Open the project in Android Studio**

3. **Add Base URL in your `gradle.properties`**
   
    ```properties
    BASE_URL=https://educatour-backend.swusjask.dev/
    ```

4. **Run the application**
    - Ensure you have either connected your Android device to your computer or set up an emulator as a target to run the application.
    - Click on the “Run” button (green triangle) in Android Studio’s toolbar to run the application.
    - The application will be installed on the selected device or emulator.

## Download APP

Download the app from the following link:

- [MoTour v1.0.0 Release](https://github.com/EducaTour/MoTour/releases/tag/v1.0.0)

Download the `apk-release.apk` file from the release page. 
