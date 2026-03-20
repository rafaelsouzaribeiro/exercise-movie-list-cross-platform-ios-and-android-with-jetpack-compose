### We are under construction.

# Move 🎬

App Kotlin Multiplatform (Android + iOS) para listagem de filmes, consumindo a API do [The Movie DB (TMDB)](https://www.themoviedb.org/).

---

## ⚙️ Configuração inicial

### 1. Criar conta no TMDB

1. Acesse **[https://www.themoviedb.org/signup](https://www.themoviedb.org/signup)**
2. Preencha nome, e-mail e senha → clique em **Junte-se ao TMDB**
3. Confirme o e-mail recebido na sua caixa de entrada

---

### 2. Gerar o Access Token

1. Faça login em **[https://www.themoviedb.org/login](https://www.themoviedb.org/login)**
2. Clique no seu avatar (canto superior direito) → **Configurações**
3. No menu lateral esquerdo, clique em **API**
4. Na seção **Token de Leitura da API**, copie o token completo

> ⚠️ Use o **Token de Leitura da API**, e não a Chave da API.

---

### 3. Colar o token no projeto

Abra o arquivo:
- composeApp/src/commonMain/kotlin/com/example/move/data/network/Secrets.kt
---
Substitua `"cole_seu_token_aqui"` pelo seu token copiado:

```kotlin

object Secrets {
    const val TMDB_ACCESS_TOKEN = "cole_seu_token_aqui"
}

```