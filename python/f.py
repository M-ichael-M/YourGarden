import csv

# Ścieżka do pliku CSV
input_file = "songs.csv"  # Podmień na rzeczywistą nazwę pliku

# Otwórz plik CSV i wczytaj dane
with open(input_file, newline='', encoding='utf-8') as csvfile:
    reader = csv.DictReader(csvfile)  # DictReader pozwala używać nazw kolumn
    kotlin_code = []

    for row in reader:
        try:
            title = row["Track Name"].strip().replace('"', '\\"')
            artist = row["Artist Name(s)"].strip().replace('"', '\\"')
            duration_ms = row["Duration (ms)"].strip()
            duration_sec = int(round(int(duration_ms) / 1000))  # Konwersja na sekundy

            # Generowanie kodu Kotlin
            kotlin_code.append(f'''    SongEntity(
        title = "{title}",
        artist = "{artist}",
        duration = {duration_sec}, // {duration_ms} ms
        youtubeUrl = null,
        filePath = null,
        downloadStatus = null
    ),''')

        except (ValueError, KeyError) as e:
            print(f"Niepoprawne dane w wierszu: {row} | Błąd: {e}")

# Zapisz wynik do pliku Kotlin
output_file = "SongsList.kt"
with open(output_file, "w", encoding="utf-8") as out:
    out.write("val songs = listOf(\n")
    out.write("\n".join(kotlin_code))
    out.write("\n)")

print(f"Plik {output_file} został wygenerowany.")
