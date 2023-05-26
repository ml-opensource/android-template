# <img src="https://engineering.monstar-lab.com/assets/img/logo/ml-logo-grey.png"  height="23"> &nbsp;     Android Template

This is a opinionated template for new Android projects used and open sourced by Monstarlab.

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
Chucker is a HTTP inspector to help check the api calls when the app is being executed, this is helpful for both devs and QAs to analyse the trafic data. It should show as a notification when the app is ran. Note that is uses an `Interceptor` in the `OkHttpClient` to get the data, so if you need to create a new `OkHttpClient` for any reason, remember to also add the Chucker`s `Interceptor`.

### [LeakCanary](https://square.github.io/leakcanary/)
LeakCanary is a memory detectiong library that will automatically run in `devDebug` builds. It will automatically detect memory leaks and in case anything is found a notification will be shown. After that the details of the leak can be viweed both by clicking the notification on in the Logcat. This is intended for devs to spot and fix any leaks as early as possible. 
