# I'm Bored

An Android application that suggests random activities to cure your boredom. Built to learn REST API integration in Android using Retrofit2 with the [Bored API](https://bored-api.appbrewery.com/), following the MVVM architecture pattern.

## Features

- Fetch a random activity suggestion from the Bored API
- Save completed activities to a local Room database
- View history of recently completed activities
- Swipe through suggestions with a ViewPager

## Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM (ViewModel, LiveData, Repository)
- **Networking:** Retrofit2 + Gson
- **Local Storage:** Room Persistence Library
- **Async:** Kotlin Coroutines
- **UI:** ViewBinding, ConstraintLayout, ViewPager2

## Project Structure

```
app/src/main/java/com/imbored/
├── apicalls/                  # Retrofit service, builder & API response model
├── database/                  # Room entity, DAO & database
├── BridgeModel.kt             # ViewModel bridging UI and Repository
├── ResponseRepo.kt            # Repository (single source of truth)
├── LoaderPage.kt              # Splash/loader screen (launcher)
├── GreetingPage.kt            # Welcome screen
├── DoActivity.kt              # Shows a random suggested activity
├── Recent.kt                  # Recent activities screen
├── RecentRecyclerView*.kt     # RecyclerView for activity history
└── ProfileSetting.kt          # Profile settings screen
```

## Getting Started

### Prerequisites

- Android Studio (Hedgehog or newer recommended)
- JDK 8+
- Android SDK 34 (minSdk 21)

### Build & Run

```bash
./gradlew assembleDebug
```

Or open the project in Android Studio and press **Run**. No API key is required — the Bored API is public and keyless.

## Security Audit

### Credential Audit — No Leaks Found

A full audit of the codebase and git history (all commits) was performed. **No credentials, API keys, tokens, secrets, or passwords were found hardcoded or leaked** — therefore nothing required rotation. The only network endpoint used is the public, keyless Bored API (`https://bored-api.appbrewery.com/`) over HTTPS.

### Issues Found & Remediated

- [x] **Cleartext traffic enabled (CWE-319)** — `android:usesCleartextTraffic="true"` in `AndroidManifest.xml` allowed plaintext HTTP. **Fixed:** set to `false` (the API is HTTPS-only, no breakage).
- [x] **Unnecessarily exported activity** — `DoActivity` had `android:exported="true"` with no intent-filter, exposing it to other apps. **Fixed:** set to `false`.
- [x] **IDE files committed** — `.idea/` directory was tracked in git. **Fixed:** added `.idea/` to `.gitignore` (along with `*.keystore`, `*.jks`, `google-services.json`, `secrets.properties` to prevent future leaks).

### TODO

- [ ] Untrack already-committed `.idea/` files from git index: `git rm -r --cached .idea`
- [ ] If a keyed/paid API is adopted in the future, store the key in `local.properties` (gitignored) or via Android Keystore — never hardcode it in source or resources
- [ ] Consider enabling ProGuard/R8 minification for release builds (`isMinifyEnabled = false` currently)
- [ ] Personal email visible in git author history — consider rewriting history with a GitHub noreply email if privacy is a concern

## License

This project is for learning purposes.
