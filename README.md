# <img src="https://engineering.monstar-lab.com/assets/img/logo/ml-logo-grey.png"  height="23"> &nbsp;     Android Template <img src="https://android-developers.googleblog.com/favicon.ico" height=23/>

This is a opinionated template for new Android projects used and open sourced by Monstarlab.


💻 **Techstack** 
-   <img src="https://kotlinlang.org/assets/images/favicon.svg?&v=8607ff59d5296c7642ecd72bd3daa79b" height=12 /> 100% Kotlin
- 🔄 Kotlin Coroutines for async operations 
- 🚀 Jetpack Architecture Components
- <img src="https://developer.android.com/static/images/spot-icons/jetpack-compose.svg" height = 16>  Jetpack Compose ❤️
- ☁️ [OkHttp](https://github.com/square/okhttp) and [Retrofit](https://github.com/square/retrofit) for networking
- 🔗 [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) as primary JSON (de)serialization tool
- 🗡️ [Dagger / Hilt](https://dagger.dev/hilt/) for Dependency Injection
- ✨ [Spotless](https://github.com/diffplug/spotless) for code-format control
- 🔍 [Detekt](https://github.com/detekt/detekt) for static code analysis
- 🌐 [Chucker](https://github.com/ChuckerTeam/chucker) - network traffic logger
- 🐤 [LeakCanary](https://square.github.io/leakcanary/) -  memory leak detection 


# Table of contents
1. [Before you start](#⚠️-before-you-start)
2. [Template Structure](#project-structure)
3. [Architecture](#architecture)
4. [Flavours](#flavors)
5. [Tools](#tools)


## ⚠️ Before you start 

Before you start using the template, you probably want to install the [Jetpack Compose UI Architecture Plugin](https://plugins.jetbrains.com/plugin/19034-jetpack-compose-ui-architecture-templates) on Android Studio.

This plugins facilitates the work when creating new Compose screens.

After installing, creating new features in the correct template structure should be as easy as doing the following:

**1 - Select the "New feature" option**

![](https://user-images.githubusercontent.com/8679058/223730854-7e199e33-1b99-49a3-ae1b-30a6ea725681.png)


**2 - Name your new feature**

![](https://user-images.githubusercontent.com/8679058/223731483-d0fce3f9-dba4-459d-9bb5-65e76abe2ee6.png)


**3 - The correct folder structure should be created for you**

![](https://user-images.githubusercontent.com/8679058/223731540-1604198c-78ac-4b3b-ad84-b3fc290746af.png)



## Project Structure & Modules
Android template project has a modular structure and has following mopdules: `:core`, `:designsystem` and `:app`. The intention is to make this template as flexible as possible by introducing shared modules while having the all the features reside in `:app` module. In case project grows, this allows to easily decouple features from `:app` module and/or introduce new features as part of separate module.

![](./resources/moduels.svg)

### `core` module
Core module is meant to be Domain-agnostic. It means that it should never reference anything project specific. Instead, it serves as a home for components that can be part of any project. This includes extensions on Kotlin Classes, Utility classes, Base classes, Compose layouts and Compose Effects and Modifiers that are behavioural rather then UI emitting


### `designsystem` module
The design system module contains the building blocks for your application's UI. The main thing you will find here is `Theme`. This template is using custom `Theme` provider for Jetpack Compose while having `Material Theme` as its foundation. This allows to tweak `Colors` and `Typography` so it reflects actual Design System that is used on the project and supply custom properties like `Dimensions`

Another part of the design system are components. Examples of `designsystem` components are Buttons, TopBars, TextFields and so on. The template provides `AppButton` `AppTextField` and `AppTopBar` that you can modify and tweak and use throughout the project.

### `localization module`
Localization module contains NStack configuration and Translations.java file so that all localization is in one place. This module is meant to be used in the `app` module to prvoide translations for the `app` and `designsystem` module to provide translations for the design system components (such as accesaibility labels)
The NStack is configured in its `build.gradle.kts` and initilized using androidx Startup library. 

### `app` module
This is a main modules that contains feature packages - the meat of any project. A Feature typically contains all the necessary code, resources and assets required to implement a specific application functionality, such as a login screen, shopping cart, or authentication.  It may also have its own sets of dependencies, like third-party libraries or other features.

#### Feature modules
While the template doesn't really have feature modules and every feature is part of the `:app` module, it is adviced to make use of feature modules in case the you see there is good candidate for it. It will especially benefit mid to large sized projects under active development

 Each feature (module or not) is following [Clean Architecture Principles and has a three-layer split](#architecture)

## Architecture
Template implements [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) and follows [best practices](https://developer.android.com/topic/architecture) provided by Google with some tweaks here and there

### Presentation layer
In our Android world, the Presentation layer or UI Layer is our Activities, Fragments, Jetpack Compose UI screens and components and ViewModels. The Presentation layer interacts with Domain Layer where our business logic happens.

### Domain layer
The domain layer contains the application's business logic. This layer should only work with abstractions and as such it would never know about how different layers look like. It shouldn’t know about any Databases, APIs, or even Android Framework.

### Data layer
The data layer is where the actual interactions happen between different data sources. This layer “implements” parts of the Domain layer and communicates with the APIs, Databases, and other services and SDKs.


![](/resources/arch.svg)


## Flavors

- **Dev**: Intende for developers use only. Usually will have the same endpoints as staging. This have LeakCanary to help detect leaks.
- **Staging**: Intended for QA and internal testing. This usually have api endpoint for a testing server.
- **Release**: Intended for release. This version usually have live endpoint and signed builds.

## Tools
These are the tools used in the template, to improve the development, that you should be aware of:

### [Spotless](https://github.com/diffplug/spotless)
Spotless is a Gradle plugin used to format source files. You can use it running the following Gradle commands:

`./gradlew spotlessCheck` - Checks the style formatting and displays any issues

`./gradlew spotlessApply` - Same as above but automatically try to fix most of the issues. If for any reason it can't, then a list of problems is displayed.


### [Chucker](https://github.com/ChuckerTeam/chucker)
Chucker is a HTTP inspector to help check the api calls when the app is being executed, this is helpful for both devs and QAs to analyse the trafic data. It should show as a notification when the app is ran. Note that is uses an `Interceptor` in the `OkHttpClient` to get the data, so if you need to create a new `OkHttpClient` for any reason, remember to also add the Chucker's `Interceptor`.

### [LeakCanary](https://square.github.io/leakcanary/)
LeakCanary is a memory detectiong library that will automatically run in `devDebug` builds. It will automatically detect memory leaks and in case anything is found a notification will be shown. After that the details of the leak can be viweed both by clicking the notification on in the Logcat. This is intended for devs to spot and fix any leaks as early as possible. 
