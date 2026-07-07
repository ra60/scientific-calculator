# Scientific Calculator

<div align="center">

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-blue.svg)](https://developer.android.com/jetpack/compose)
[![Material 3](https://img.shields.io/badge/Design-Material%203-orange.svg)](https://m3.material.io)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

**A powerful, bilingual scientific calculator for Android**

[English](#-features) | [Bahasa Indonesia](#-fitur)

</div>

---

## 📱 Screenshots

<div align="center">
<img src="screenshots/light_mode_basic.png" width="200" alt="Basic Calculator Light Mode">
<img src="screenshots/dark_mode_scientific.png" width="200" alt="Scientific Calculator Dark Mode">
<img src="screenshots/language_switch.png" width="200" alt="Language Selection">
</div>

> *Screenshots coming soon — build and run the app to see it in action!*

---

## ✨ Features

### 🇬🇧 English

- **Dual Mode Calculator**
  - Basic mode for everyday calculations (+, −, ×, ÷, %, ±)
  - Scientific mode with advanced functions

- **Bilingual Support**
  - English and Bahasa Indonesia
  - Seamless language switching via Settings

- **Modern Design**
  - Material Design 3 interface
  - Dark and Light themes
  - System default theme (follows device settings)

- **Advanced Functions**
  - Trigonometry: sin, cos, tan
  - Logarithms: log (base 10), ln (natural)
  - Powers: x², xⁿ, √
  - Constants: π, e
  - Factorial: n!
  - Parentheses support

- **User Experience**
  - Intuitive button layout
  - Copy results to clipboard
  - Responsive design for all screen sizes
  - Lightweight and battery efficient

### 🇮🇩 Bahasa Indonesia

- **Kalkulator Mode Ganda**
  - Mode biasa untuk perhitungan sehari-hari (+, −, ×, ÷, %, ±)
  - Mode ilmiah dengan fungsi lanjutan

- **Dukungan Dwibahasa**
  - Bahasa Inggris dan Bahasa Indonesia
  - Perpindahan bahasa yang mulus melalui Pengaturan

- **Desain Modern**
  - Antarmuka Material Design 3
  - Tema Gelap dan Terang
  - Tema default sistem (mengikuti pengaturan perangkat)

- **Fungsi Lanjutan**
  - Trigonometri: sin, cos, tan
  - Logaritma: log (basis 10), ln (natural)
  - Pangkat: x², xⁿ, √
  - Konstanta: π, e
  - Faktorial: n!
  - Dukungan tanda kurung

- **Pengalaman Pengguna**
  - Tata letak tombol intuitif
  - Salin hasil ke clipboard
  - Desain responsif untuk semua ukuran layar
  - Ringan dan hemat baterai

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- JDK 17
- Android SDK 24+ (Android 7.0)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/RizalAinurRofiq/scientific-calculator.git
   cd scientific-calculator
   ```

2. **Open in Android Studio**
   - File → Open → Select the project folder
   - Wait for Gradle sync to complete

3. **Build and Run**
   - Connect your Android device or start an emulator (API 24+)
   - Click ▶️ Run or press Shift+F10

### Build from Command Line

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease
```

---

## 🛠️ Tech Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| Language | [Kotlin](https://kotlinlang.org) | 1.9.22 |
| UI | [Jetpack Compose](https://developer.android.com/jetpack/compose) | BOM 2024.01.00 |
| Design | [Material Design 3](https://m3.material.io) | — |
| Architecture | MVVM | — |
| Navigation | [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) | 2.7.6 |
| Storage | [DataStore Preferences](https://developer.android.com/topic/libraries/architecture/datastore) | 1.0.0 |
| Build | [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) | 8.2.2 |
| Min SDK | 24 (Android 7.0) | — |
| Target SDK | 34 (Android 14) | — |

---

## 📁 Project Structure

```
app/src/main/java/com/calculator/scientific/
├── MainActivity.kt                    # Entry point
├── data/
│   └── SettingsDataStore.kt           # Preferences storage (DataStore)
├── engine/
│   └── CalculatorEngine.kt            # Expression parser (recursive descent)
├── navigation/
│   └── NavGraph.kt                    # Navigation routes
├── ui/
│   ├── theme/
│   │   ├── Color.kt                   # Color definitions
│   │   ├── Theme.kt                   # Material 3 theme
│   │   └── Type.kt                    # Typography
│   ├── screens/
│   │   ├── MainScreen.kt              # Calculator screen
│   │   └── SettingsScreen.kt          # Settings screen
│   └── components/
│       ├── CalculatorButton.kt        # Reusable button component
│       ├── DisplayPanel.kt            # Expression & result display
│       ├── BasicKeypad.kt             # Basic mode layout
│       ├── ScientificKeypad.kt        # Scientific mode layout
│       └── ModeToggle.kt              # Mode switcher
└── viewmodel/
    ├── CalculatorViewModel.kt         # Calculator logic (MVVM)
    └── SettingsViewModel.kt           # Settings logic (MVVM)
```

---

## 🧮 Calculator Engine

The `CalculatorEngine` uses a **recursive descent parser** to evaluate mathematical expressions with proper operator precedence:

| Feature | Support |
|---------|---------|
| Operators | +, −, ×, ÷, %, ^ |
| Functions | sin, cos, tan, log, ln, √ |
| Factorial | n! (integer values) |
| Constants | π, e |
| Parentheses | Full nested expression support |
| Precedence | Correct operator precedence (PEMDAS) |

---

## ⚙️ Configuration

### Language Settings
Navigate to **Settings** to switch between:
- English (default)
- Bahasa Indonesia

### Theme Settings
Choose from:
- **Light Mode** — Clear daytime visibility
- **Dark Mode** — Comfortable night-time use
- **System Default** — Follows device theme settings

---

## 🤝 Contributing

Contributions are welcome! Here's how:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'feat: add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style
- Follow Kotlin coding conventions
- Use meaningful variable/function names
- Add KDoc comments for public APIs
- Write unit tests for new features

---

## 📄 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Rizal Ainur Rofiq**
- GitHub: [@RizalAinurRofiq](https://github.com/RizalAinurRofiq)

---

## 🙏 Acknowledgments

- [Material Design 3](https://m3.material.io) for the beautiful design system
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for modern declarative UI
- [Android Jetpack](https://developer.android.com/jetpack) libraries

---

<div align="center">

**⭐ Star this repository if you found it helpful!**

</div>
