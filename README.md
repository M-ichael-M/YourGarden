# YourGarden App

A personalized Android application crafted with love for my girlfriend, blending music playback and coupon management into a unique experience.

## Table of Contents

- Features
- Setup
- Usage
- Architecture
- Contributing

## Features

- **Music Playback:**

  - Download songs directly from YouTube URLs.
  - Play, pause, skip, and manage downloaded songs with a sleek interface.
  - Random playback option and song deletion functionality.

- **Coupon Management:**

  - Browse available and used coupons.
  - Activate coupons with a confirmation prompt.
  - Receive email notifications upon coupon activation.

- **Home Screen:**

  - Displays the number of days since our special date (June 22, 2024).

## Setup

### Prerequisites

- **Android Studio** (for app development and emulation)
- **Python 3.x** (for the server)
- **Python Packages:** `flask`, `yt_dlp`
- **FFmpeg** (for audio processing on the server)

### Installation

1. **Clone the Repository:**

   ```sh
   git clone https://github.com/yourusername/yourgarden.git
   ```

2. **Set Up the Android Project:**

   - Open the project in Android Studio.
   - Sync Gradle and build the project.

3. **Set Up the Python Server:**

   - Navigate to the server directory in the cloned repository.
   - Install dependencies:

     ```sh
     pip install flask yt_dlp
     ```
   - Ensure FFmpeg is placed in the `bin/` directory relative to `YourGarden.py`.
   - Run the server:

     ```sh
     python YourGarden.py
     ```
   - (Optional) Convert to executable using PyInstaller or Nuitka:

     ```sh
     pyinstaller --onefile YourGarden.py
     ```

4. **Configure the Server URL in the App:**

   - Launch the app and use the settings icon to set the server URL.
   - Default URL: `http://192.168.1.8:5000` (adjust based on your server's IP).

## Usage

### Home Screen

- **Days Counter:** Shows the number of days since June 22, 2024, with a live seconds ticker.
- **Navigation:** Access Music and Coupons screens via buttons.

### Music Screen

- **Song List:** View downloaded songs and available songs to download.
- **Controls:** Play, pause, skip next/previous, and toggle random playback.
- **Add Songs:** Enter a song title, artist, and YouTube URL to queue for download.
- **Delete Songs:** Remove downloaded songs from storage.

### Coupons Screen

- **Available Coupons:** Lists unused coupons with titles, codes, and descriptions.
- **Used Coupons:** Displays activated coupons with usage dates.
- **Activation:** Activate a coupon with a confirmation dialog; an email is sent upon activation.

## Architecture

- **Android App:**

  - **UI:** Built with Jetpack Compose for a modern, responsive interface.
  - **Database:** Room persists songs and coupons locally.
  - **ViewModels:** Manage UI data and business logic.

- **Python Server:**

  - **Framework:** Flask handles HTTP requests.
  - **Downloader:** `yt_dlp` fetches audio from YouTube URLs.
  - **File Handling:** Serves audio files and cleans up post-delivery.

## Contributing

This is a personal project, but feel free to fork and suggest improvements via pull requests!