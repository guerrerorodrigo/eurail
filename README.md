# Articles app

This app displays a list of help articles along with their details.

## Architecture

It is divided in three different layers:
- Data
- Domain
- UI

`Data` layer contains implementation for local and remote data sources. It also contains a module/folder for the feature implementation. The feature contains repositories and datasources. 

The `local` module contains only the KMP implementation for the database for Android. 

The `remote` module contains the `Ktor` configuration, requests and `DTO`. It also contains the [mock server](https://github.com/guerrerorodrigo/eurail/blob/main/app/src/main/kotlin/com/rodrigoguerrero/eurail/data/remote/mockserver/MockServer.kt) used. 

The `shared` module lives in the `data` layer, and contains shared database implementation as well as the [cache policy](https://github.com/guerrerorodrigo/eurail/blob/main/shared/src/commonMain/kotlin/com/rodrigoguerrero/shared/data/local/CachePolicy.kt) and a [cache manager](https://github.com/guerrerorodrigo/eurail/blob/main/shared/src/commonMain/kotlin/com/rodrigoguerrero/shared/data/local/CachedArticlesManager.kt) to help with the cache operations.

The `domain` layer contains the interactors used. They use the repositories from the data layer. 

The `UI` layer contains a custom [MVI framework](https://github.com/guerrerorodrigo/eurail/tree/main/app/src/main/kotlin/com/rodrigoguerrero/eurail/ui/mvi), used in other personal projects. This framework makes it easy to implement the `MVI` pattern by sending actions, using `Middleware`s to process these actions and perform any needed logic, reducer to update the state and view model that holds everything together. Maybe for this simple app is a bit too much, but since I have this framework already, it was easier to reuse existing code.

UI is divided into folders that contain the implementations for the different screens as well as for the navigation and the theme.

This is a single module app (without taking into account the KMP shared code). However, it is using interfaces for the `datasources`, `repositories` and `interactors` simulating a multi-module app.

## Error Handling

The `HttpClientConfig` [file](https://github.com/guerrerorodrigo/eurail/blob/main/app/src/main/kotlin/com/rodrigoguerrero/eurail/data/remote/config/HttpClientConfig.kt) contains the error handling for network errors. It differentiates between the following errors: client, server, parsing and other errors. 

If the BE returns a client error, I try to parse its body to obtain the custom error information. If there is no custom error information, then a generic client error is returned.

Whenever there are errors, a full error screen displays to the user with some information and a button to retry.

## Auto Refresh

The app works under a cache first policy. Whenever there is no cache, or the cache is invalid, and the client tries to display the articles, the data is fetched from the BE. If there is cache and it is valid, then the cached data is displayed.

A [`Worker`](https://github.com/guerrerorodrigo/eurail/blob/main/app/src/main/kotlin/com/rodrigoguerrero/eurail/workers/RefreshWorker.kt) with the work manager is used to update the cache every 24 hours. This will happen only when the device is connected, there is enough battery and there is enough storage. The decision to refresh every 24 hours should be aligned with the requirements. But for this exercise, I think that these articles are not supposed to change that often. So refreshing the data every day should be enough.

## Cache

A `Room` database is used for the cache using KMP. With `Room`, the data can be preserved after the app has been closed. I decided to use Room for the solely purpose of mantaining the data even when the app is closed. Plus, it is easy to use in `KMP`.

I decided to have the cache expiry to 24 hours since I expect the data to not change that often. This expiry time should be discussed with product and agreed upon after considering different scenarios.

## More

I would have liked to use dependency injection in the shared module. I have used Koin before with kMP, but lately I'm more used to Hilt for the Android side. I didn't have enough time to investigate how to make both frameworks interact between them.

I also would like to improve the accessibility features in the UI. 

Also I'd have liked to add more tests, both unit tests and UI tests.

Finally, I'd have liked to randomize the BE responses, so when testing, we could get the different errors and scenarios.
