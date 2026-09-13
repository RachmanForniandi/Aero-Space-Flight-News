<h1 align="center">🚀 Aero Space Flight News</h1>

<p align="center">
  An Android application that brings you the latest aerospace &amp; spaceflight news, articles, and blogs — powered by the public <a href="https://api.spaceflightnewsapi.net">Spaceflight News API</a>.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/>
  <img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
  <img src="https://img.shields.io/badge/Min%20SDK-27-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Target%20SDK-36-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Version-1.0-orange?style=for-the-badge"/>
</p>

---

## 📋 Table of Contents

1. [Splashscreen](#1-splashscreen)
2. [Tab 1 — Home](#2-tab-1--home)
3. [Tab 2 — Articles](#3-tab-2--articles)
4. [Tab 3 — Blogs](#4-tab-3--blogs)
5. [Tab 4 — Favorites](#5-tab-4--favorites)
6. [Detail](#6-detail)
7. [Detail (WebView)](#7-detail-webview)
8. [Settings](#8-settings)

---

## ✨ Features

- 🏠 **Home** — Top 10 Articles & Blogs displayed in a horizontal scrollable carousel
- 📰 **Articles** — Full paginated list of aerospace articles with shimmer loading effect
- 🌐 **Blogs** — Full paginated list of space blogs with pull-to-refresh support
- ⭐ **Favorites** — Save & manage your favorite articles and blogs locally (Dynamic Feature Module)
- 🔍 **Detail View** — Full article/blog detail with source, author, summary, and a WebView option
- 🌙 **Dark Mode** — Toggle between light and dark themes via Settings
- 🌏 **Multi-Language** — Change app language from the Settings page
- ✨ **Advanced Animation** — Lottie animations on the Splashscreen and shimmer skeleton loading
- 🔐 **Encrypted Local Database** — Room + SQLCipher for secure local favorites storage
- 📦 **Multi-Module Architecture** — Separated into `:app`, `:core`, and `:favorite` (Dynamic Feature) modules

---

## 🛠️ Tech Stack

| Category | Library / Tool |
|---|---|
| **Language** | Kotlin 2.2.0 |
| **UI** | XML Layouts, ViewBinding, Material Design 3 |
| **Navigation** | Jetpack Navigation Component + Safe Args |
| **DI** | Dagger Hilt 2.57.2 |
| **Networking** | Retrofit 3.0.0, OkHttp 5, Gson Converter |
| **Image Loading** | Coil3, Glide 5 |
| **Async** | Kotlin Coroutines + LiveData |
| **Pagination** | Paging 3 |
| **Local Storage** | Room 2.8.3 + SQLCipher (Encrypted) |
| **Preferences** | Jetpack DataStore |
| **Animation** | Lottie 6.7.1, Facebook Shimmer |
| **Dynamic Feature** | Google Play Feature Delivery |
| **Debugging** | Chucker (Debug), LeakCanary |

---

## 🏗️ Architecture

This project follows **Clean Architecture** with a **multi-module** structure:

```
AeroSpaceFlightNews/
├── app/          → Main application module (Navigation, UI, DI wiring)
├── core/         → Shared module (API, Database, Domain models, Use Cases)
└── favorite/     → Dynamic Feature Module (Favorites screen)
```

> Data flows: `API / Room` → `Repository (core)` → `ViewModel` → `Fragment/Activity`

---

## 📸 Screenshots

### 1. Splashscreen

<p align="center">
  <img src="assets/ss/1.jpeg" width="300" alt="Splashscreen"/>
</p>

> Animated launch screen with Lottie rocket animation, app name, and API attribution.

---

### 2. Tab 1 — Home

<p align="center">
  <img src="assets/ss/2.jpeg" width="300" alt="Home Tab"/>
</p>

> Displays **Top 10 Articles** and **Top 10 Blogs** in a horizontally scrollable card list. Tap the ⚙️ icon to access Settings.

---

### 3. Tab 2 — Articles

<p align="center">
  <img src="assets/ss/3.jpeg" width="300" alt="Articles Tab"/>
</p>

> Full paginated list of aerospace articles fetched from the Spaceflight News API, displayed in a vertical card layout with shimmer loading.

---

### 4. Tab 3 — Blogs

<p align="center">
  <img src="assets/ss/4.jpeg" width="300" alt="Blogs Tab"/>
</p>

> Full paginated list of space blogs with pull-to-refresh support and shimmer skeleton loading.

---

### 5. Tab 4 — Favorites

<p align="center">
  <img src="assets/ss/5.jpeg" width="300" alt="Favorites Tab"/>
</p>

> Saved favorite articles and blogs stored locally in an encrypted Room database (SQLCipher). Includes a floating **Clear All** button to remove all favorites at once.

---

### 6. Detail

<p align="center">
  <img src="assets/ss/6.jpeg" width="300" alt="Detail Screen"/>
</p>

> Article/Blog detail page showing title, published date, updated date, image, source, author, and summary. Includes a ⭐ **Add to Favorites** button and a **"Click here for more details"** button that opens the full article in a WebView.

---

### 7. Detail (WebView)

<p align="center">
  <img src="assets/ss/7.jpeg" width="300" alt="Detail WebView"/>
</p>

> Opens the original article source URL directly inside the app using an in-app WebView, providing a seamless reading experience without leaving the application.

---

### 8. Settings

<p align="center">
  <img src="assets/ss/8.jpeg" width="300" alt="Settings Screen"/>
</p>

> Settings page accessible from the Home toolbar. Options include:
> - **Change Language** — Switch the app language
> - **Change Theme** — Toggle between Light 🌤️ and Dark 🌙 mode

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Meerkat or later
- JDK 11+
- Android device / emulator with API 27+

### Clone & Run

```bash
git clone https://github.com/RachmanForniandi/Aero-Space-Flight-News.git
cd Aero-Space-Flight-News
```

Open the project in Android Studio and run the `:app` module.

---

## 🌐 API Reference

This app uses the free public [Spaceflight News API v4](https://api.spaceflightnewsapi.net/v4/).

| Endpoint | Description |
|---|---|
| `/v4/articles/` | Fetch paginated articles |
| `/v4/blogs/` | Fetch paginated blogs |
| `/v4/articles/{id}` | Fetch article detail |
| `/v4/blogs/{id}` | Fetch blog detail |

---

## 📄 License

```
Copyright 2026 Rachman Forniandi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

---

<p align="center">Made with ❤️ by <strong>Rachman Forniandi</strong> · Supported by <a href="https://www.dicoding.com">Dicoding</a></p>
