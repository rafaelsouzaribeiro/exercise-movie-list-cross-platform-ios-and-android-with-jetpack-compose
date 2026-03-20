### We are under construction.

# Move 🎬

App Kotlin Multiplatform (Android + iOS) para listagem de filmes, consumindo a API do [The Movie DB (TMDB)](https://www.themoviedb.org/).

---

## ⚙️ Configuração inicial

### 1. Criar conta no TMDB

1. Acesse **[https://www.themoviedb.org/signup](https://www.themoviedb.org/signup)**
2. Preencha nome, e-mail e senha → clique em **Cadastrar**
3. Confirme o e-mail recebido na sua caixa de entrada

---

### 2. Gerar o Access Token

1. Faça login em **[https://www.themoviedb.org/login](https://www.themoviedb.org/login)**
2. Clique no seu avatar (canto superior direito) → **Configurações**
3. No menu lateral esquerdo, clique em **API**
4. Na seção **API Read Access Token (v4 auth)**, copie o token completo

> ⚠️ Use o **API Read Access Token**, e não a API Key (v3).

---

### 3. Colar o token no projeto

Abra o arquivo:
- composeApp/src/commonMain/kotlin/com/example/move/data/network/Secrets.kt
---
Substitua `"TOKEN"` pelo seu token copiado:

```kotlin
package com.example.move.data.network

object Secrets {
    const val TMDB_ACCESS_TOKEN = "cole_seu_token_aqui"
}

This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…