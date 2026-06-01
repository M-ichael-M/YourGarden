# YourGarden 🌿

A personal Android app combining music playback and coupon management, built as a gift. Powered by a lightweight Python/Flask backend for YouTube audio downloads.

> **Note:** This is a personal project — some features (e.g. the days counter, coupon email notifications) are hardcoded for a specific use case, but the architecture is fully adaptable.

---

## Features

**Music Player**
- Download audio from YouTube URLs via a Flask + yt-dlp backend
- Play, pause, skip, and shuffle downloaded tracks
- Add songs by title, artist, and URL; delete them from local storage

**Coupon Manager**
- Browse available and used coupons with titles, codes, and descriptions
- Activate coupons with a confirmation dialog
- Email notification sent automatically on activation

**Home Screen**
- Days-since counter with a live seconds ticker (counted from a configurable start date)
- Quick navigation to Music and Coupons screens

**Virtual Valentine's Day invitation**
- Interactive experience using hardware (flashlight, vibration, screen brightness, position sensor, camera, microphone)
- Photo gallery
- Valentine's Day date survey + answers sent via email

---

## Tech Stack

| Layer | Technology |
|---|---|
| Android UI | Jetpack Compose |
| Local storage | Room (SQLite) |
| State management | ViewModel + StateFlow |
| Backend | Python 3, Flask |
| Audio downloader | yt-dlp |
| Audio processing | FFmpeg |

---

## Project Structure

```
yourgarden/
├── app/                  # Android (Kotlin + Jetpack Compose)
└── python/
    └── YourGarden.py     # Flask server (download + file serving)
```

---

## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (Hedgehog or newer recommended)
- Python 3.8+
- FFmpeg

### 1. Clone the repository

```sh
git clone https://github.com/M-ichael-M/YourGarden.git
cd yourgarden
```

### 2. Set up the Android app

Open the `app/` folder in Android Studio, sync Gradle, and build the project.

### 3. Set up the Python server

```sh
cd python
pip install flask yt_dlp
python YourGarden.py
```

The server starts at `http://0.0.0.0:5000` by default.


### 4. Connect the app to the server

Launch the app and tap the settings icon to enter your server's local IP address.

Default: `http://192.168.1.8:5000` — change this to match your machine.

---

## Usage

| Screen | What you can do |
|---|---|
| **Home** | See the days counter and navigate to other screens |
| **Music** | View downloaded songs, add new ones by URL, play/skip/shuffle/delete |
| **Coupons** | Browse available coupons, activate them, view used coupons with dates |
| **Virtual Valentine's Day invitation** | Delight your partner with an invitation to Valentine's Day |

---


## License

[MIT](LICENSE)