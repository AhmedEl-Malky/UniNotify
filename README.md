# UniNotify – Campus Events App

<!--![uninotify_mockup](https://github.com/user-attachments/assets/PUT_YOUR_IMAGE_HERE) -->

## 📌 Project Overview

**UniNotify** is a modern Android application designed to help university students stay updated with campus events such as **seminars, exams, fests, and important notices**.  
The app delivers a smooth and reliable experience by following an **offline-first architecture**, ensuring data availability even without an internet connection.

---

## 🚀 Features

- **Campus Events Feed:** Browse all upcoming campus events in one place.
- **Event Categories:** Filter events by seminars, exams, fests, and notices.
- **Event Details:** View full event information including date, time, and venue.
- **Saved Events:** Bookmark events for later access.
- **Authentication:** Login using Email/Password or Google Sign-In.
- **Offline First Architecture:** Cached data remains accessible without internet.
- **Modern UI:** Clean and intuitive interface following Material Design 3.
- **Planned Features:**
  - Local Notifications for event reminders.
  - Push Notifications for real-time campus alerts.

---

## 🛠️ Architecture

UniNotify follows a **Clean Architecture** approach with an **Offline-First strategy** to ensure scalability, maintainability, and a robust user experience.

### 🔹 Presentation Layer
Responsible for UI rendering and user interactions using **Jetpack Compose**.

### 🔹 Domain Layer
Contains business logic and application use cases, independent of frameworks.

### 🔹 Data Layer
Manages application data from both local and remote sources:
- **Firebase SDK** for remote data and authentication.
- **Room Database** for local caching and offline access.

---

## 🔧 Tech Stack

- **Kotlin** – Primary programming language  
- **Jetpack Compose** – Declarative UI framework  
- **Material Design 3** – Modern UI system  
- **Room** – Local database for offline-first caching  
- **Firebase Authentication** – User authentication  
- **Firebase Firestore** – Remote data source  
- **MVVM Architecture** – Clean separation of concerns  
- **Coroutines & Flow** – Asynchronous and reactive programming  

---
```
## 📁 Project Structure
UniNotify/
├── App/
│   ├── Navigation/            # Navigation graph and routes
│   ├── Di/                    # Dependency Injection modules
│   └── UniNotifyApp.kt        # Application entry point
│
├── Data/
│   ├── Local/                 # Local data source (Room)
│   ├── Remote/                # Remote data source (Firebase SDK)
│   ├── Repositories/            # Repository implementations
│   ├── Mappers/                # Data ↔ Domain mappers
│   └── Utils/                 # Data-layer helpers and constants
│
├── Domain/
│
├── presentation/
│   ├── Screens/               # App screens (Compose)
│   ├── Components/            # Reusable Compose UI components
│   ├── Theme/                 # App theme, colors, typography
│   └── Utils/                 # UI helpers and extensions
│
└── Utils/                     # General utilities shared across the app
```
<!--
---

## 🎥 Demo
*(Add screenshots or demo video link here)*

---
-->

## 📌 Future Improvements

- Implement Local Notifications for event reminders.
- Add Push Notifications using Firebase Cloud Messaging (FCM).
- Support Dark Mode.
- Improve accessibility and UI animations.

---

## 👨‍💻 Author

**Ahmed El-Malky**  
Android Developer

