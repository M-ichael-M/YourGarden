from flask import Flask, request, send_file
import yt_dlp
import os
import shutil
import time

app = Flask(__name__)

DOWNLOAD_DIR = "downloads"

# Konfiguracja yt-dlp bez konwersji
ydl_opts = {
    'format': 'bestaudio/best',
    'outtmpl': os.path.join(DOWNLOAD_DIR, '%(title)s.%(ext)s'),
    'noplaylist': True  # Zapobiega pobieraniu całej playlisty

}


def download_song(url):
    if not os.path.exists(DOWNLOAD_DIR):
        os.makedirs(DOWNLOAD_DIR)

    with yt_dlp.YoutubeDL(ydl_opts) as ydl:
        info = ydl.extract_info(url, download=True)
        filename = ydl.prepare_filename(info)
        return filename


@app.route('/download', methods=['POST'])
def download():
    data = request.get_json()
    youtube_url = data.get('youtube_url')

    if not youtube_url:
        return {"error": "No URL provided"}, 400

    file_path = None
    try:
        file_path = download_song(youtube_url)
        response = send_file(file_path, mimetype='audio/webm', as_attachment=True)
        return response
    except Exception as e:
        return {"error": str(e)}, 500
    finally:
        # Poczekaj chwilę, aby upewnić się, że plik jest zwolniony
        if file_path:
            time.sleep(1)  # Krótkie opóźnienie
            try:
                if os.path.exists(DOWNLOAD_DIR):
                    shutil.rmtree(DOWNLOAD_DIR)
            except PermissionError:
                print(f"Nie udało się usunąć {DOWNLOAD_DIR} - plik w użyciu. Spróbuj ponownie później.")
            except Exception as e:
                print(f"Błąd podczas usuwania {DOWNLOAD_DIR}: {e}")


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
