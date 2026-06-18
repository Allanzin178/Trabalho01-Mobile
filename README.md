# 📱 Clima App - Trabalho Mobile

Aplicativo Android nativo desenvolvido em **Kotlin**, utilizando arquitetura **MVVM**, integrando múltiplas APIs e recursos avançados de hardware para fornecer previsões climáticas precisas via CEP ou Geolocalização.

---

## 📌 Funcionalidades Principais

O aplicativo atende a todos os critérios técnicos exigidos, oferecendo:

1.  **Busca por CEP**: Fluxo integrado (ViaCEP + Open-Meteo) para obter o clima de qualquer região do Brasil.
2.  **Busca por Geolocalização (Mapa)**: Integração com **Google Maps** que permite ao usuário selecionar qualquer ponto no globo para verificar o clima instantaneamente.
3.  **Histórico de Consultas**: Persistência local de todas as buscas realizadas.
4.  **Temas Personalizados**: Opção de alternar entre modo claro e escuro, com preferência salva no dispositivo.
5.  **Interação por Movimento**: Limpeza do histórico de buscas ao chacoalhar o celular (Acelerômetro).

---

## 🧱 Critérios Técnicos Atendidos

-   **Arquitetura MVVM**: Separação clara de responsabilidades entre View, ViewModel e Model/Repository.
-   **Mínimo de 5 Telas**:
    -   Menu Principal (Navegação central e troca de tema)
    -   Busca por CEP (Entrada de dados e validação)
    -   Busca por Geolocalização (Integração com Mapa)
    -   Resultado do Clima (Exibição detalhada)
    -   Histórico de Consultas (Listagem persistente)
-   **API REST**: Consumo das APIs ViaCEP e Open-Meteo via **Retrofit**.
-   **Persistência de Dados**: Uso de **SQLite** nativo para armazenamento seguro do histórico de buscas.
-   **SharedPreferences**: Armazenamento local da preferência de tema do usuário.
-   **Processamento em Segundo Plano**: Operações de rede e banco de dados executadas via **Kotlin Coroutines**.
-   **Testes Unitários**: Implementação de testes para validar a lógica de criação de dados e timestamps.
-   **Sensores**: Uso do **Acelerômetro** para detecção de chacoalho (Shake) para limpar o histórico.
-   **Mapas**: Integração completa com **Google Maps SDK**.

---

## ⚠️ Configuração Obrigatória (Google Maps)

Para que a funcionalidade de mapa carregue corretamente e não exiba uma tela branca, é **obrigatório** configurar uma Chave de API do Google Cloud:

1.  Acesse o [Google Cloud Console](https://console.cloud.google.com/).
2.  Habilite a **Maps SDK for Android**.
3.  Gere uma **API Key**.
4.  Abra o arquivo `app/src/main/AndroidManifest.xml`.
5.  Substitua o valor no campo abaixo pela sua chave:
    ```xml
    <meta-data
        android:name="com.google.android.geo.API_KEY"
        android:value="SUA_CHAVE_AQUI" />
    ```

---

## 🛠️ Tecnologias Utilizadas

-   **Linguagem**: Kotlin
-   **Ambiente**: Android Studio
-   **Interface**: XML (ConstraintLayout, Material Design 3)
-   **Comunicação**: Retrofit + GSON
-   **Banco de Dados**: SQLite
-   **Async**: Coroutines + LiveData
-   **Hardware**: Sensor Manager (Acelerômetro) + Google Maps SDK

---

## 👨‍💻 Desenvolvedores

-   Allan Barros
-   Daniel da Cunha
-   Kalleby Rodrigues
