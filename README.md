# 📱 App de Consulta Climática por CEP

Aplicativo Android nativo desenvolvido em **Kotlin**, utilizando arquitetura **MVVM**, com o objetivo de fornecer informações climáticas a partir de um CEP brasileiro, integrando múltiplas APIs.

---

## 📌 Visão Geral

O sistema realiza consultas em cadeia utilizando três APIs:

1. **ViaCEP** → obtém dados de endereço a partir do CEP  
2. **Open-Meteo Geocoding** → converte cidade em latitude e longitude  
3. **Open-Meteo Forecast** → retorna dados climáticos com base nas coordenadas  

---

## 🧱 Arquitetura

O projeto segue o padrão **MVVM (Model-View-ViewModel)**:

### View
- Activities e XML Layouts
- Componentes como `TextView`, `EditText`, `ListView`

### ViewModel
- Gerencia estados (Loading, Success, Error)
- Utiliza `LiveData` para atualizar a UI automaticamente
- Uso de **Coroutines** para chamadas assíncronas

### Model / Repository
- Consumo de APIs com **Retrofit**
- Regras de negócio
- Validação de CEP
- Mapeamento de JSON com data classes

---

## ✅ Etapa 1 — Implementação

Funcionalidades já desenvolvidas:

### 🔎 Consulta Climática
- Busca de clima a partir de um CEP válido
- Fluxo encadeado entre APIs (CEP → localização → clima)

### 🔗 Integração com APIs
- ViaCEP
- Open-Meteo (Geocoding + Forecast)

### 🧠 Arquitetura e Tecnologias
- MVVM com ViewModel e LiveData
- Kotlin Coroutines para chamadas assíncronas
- Retrofit para consumo de API

### 🧾 Funcionalidades do App
- Inserção de CEP (8 dígitos)
- Validação de entrada (somente números)
- Exibição de:
  - Cidade
  - Estado
  - Temperatura
  - Sensação térmica
- Histórico de consultas com **ListView**

### 🎨 Interface
- Material Design 3
- Suporte a tema claro/escuro
- Uso de:
  - TextInputLayout
  - MaterialButton
  - CardView
  - Snackbar para erros

### 🔄 Navegação
- Uso de **Intents**
- Passagem de parâmetros entre telas

### ⚠️ Tratamento de Erros
- CEP inválido
- Falha de API
- Falta de conexão

---

## 🔄 Fluxo do Sistema

1. Usuário insere o CEP  
2. Validação do formato  
3. Consulta na API ViaCEP  
4. Conversão para latitude/longitude  
5. Consulta climática  
6. Exibição dos dados na tela  

> Regra: cada etapa só executa se a anterior for bem-sucedida.

---

## 🚧 Etapa 2 — Escopo (Em Planejamento)

Melhorias e novas funcionalidades previstas:

### 🔍 Novas Funcionalidades
- Busca direta por **nome de município**
- Tela inicial com clima de **cidades principais**
- Sistema de **login opcional**
- Implementação de um **chatbot**

### 🎨 Melhorias de Interface
- Refinamento do design (UI/UX)
- Melhor organização visual dos dados

### ⚙️ Melhorias Técnicas
- Tratamento de erros mais robusto
- Melhor qualidade e detalhamento dos dados climáticos

---

## 🛠️ Tecnologias Utilizadas

- Kotlin  
- Android Studio  
- MVVM  
- LiveData  
- Coroutines  
- Retrofit  
- Material Design 3  

---

## 👨‍💻 Desenvolvedores

- Allan Barros  
- Daniel da Cunha  
- Kalleby Rodrigues