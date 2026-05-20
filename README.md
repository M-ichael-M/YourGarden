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
│   ├── ui/               # Compose screens (Home, Music, Coupons)
│   ├── viewmodel/        # ViewModels for each screen
│   └── data/             # Room entities, DAOs, repositories
└── server/
    └── YourGarden.py     # Flask server (download + file serving)
```

---

## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (Hedgehog or newer recommended)
- Python 3.8+
- FFmpeg — place the binary in `server/bin/`

### 1. Clone the repository

```sh
git clone https://github.com/yourusername/yourgarden.git
cd yourgarden
```

### 2. Set up the Android app

Open the `app/` folder in Android Studio, sync Gradle, and build the project.

### 3. Set up the Python server

```sh
cd server
pip install flask yt_dlp
python YourGarden.py
```

The server starts at `http://0.0.0.0:5000` by default.

**Optional — build a standalone executable:**
```sh
pyinstaller --onefile YourGarden.py
```

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

---

## Contributing

This is a personal project, but PRs and suggestions are welcome. Feel free to fork and adapt it for your own use case.

---

## License

[MIT](LICENSE)