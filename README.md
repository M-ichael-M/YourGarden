# YourGarden - Aplikacja na Androida

## Opis
YourGarden to aplikacja na Androida stworzona specjalnie dla mojej dziewczyny. Aplikacja umożliwia odtwarzanie muzyki oraz zarządzanie kuponami na różne okazje, takie jak randki czy niespodzianki. Dodatkowo zawiera ekran główny z licznikiem dni od ważnej daty (22.06.2024) oraz sekundy, które upłynęły od tego momentu. Aplikacja została zaprojektowana z myślą o personalizacji i łatwości użytkowania, oferując intuicyjny interfejs użytkownika oparty na Jetpack Compose.

## Funkcjonalności
1. **Odtwarzacz muzyczny**:
   - Pobieranie utworów z YouTube poprzez serwer (domyślny URL: `http://192.168.1.8:5000`).
   - Odtwarzanie pobranych utworów z lokalnej pamięci urządzenia.
   - Funkcja losowego odtwarzania i przełączania utworów.
   - Wyszukiwanie utworów po tytule lub artyście.
   - Usuwanie pobranych utworów.
   - Interfejs z paskiem postępu i kontrolkami odtwarzania (play/pause, następny/poprzedni).

2. **Zarządzanie kuponami**:
   - Lista kuponów z podziałem na niewykorzystane i zużyte.
   - Możliwość aktywacji kuponów z potwierdzeniem w oknie dialogowym.
   - Powiadomienia e-mailowe po aktywacji kuponu (wysyłane na zdefiniowany adres e-mail).
   - Predefiniowane kupony z opisami i kodami, np. "BEST BIRTHDAY OF YOUR LIFE" czy "SPICY SURPRISE".

3. **Ekran główny**:
   - Licznik dni i sekund od 22.06.2024 20:25.
   - Nawigacja do ekranów muzyki i kuponów.

4. **Ustawienia**:
   - Możliwość zmiany adresu URL serwera do pobierania muzyki.

## Technologie
- **Język programowania**: Kotlin
- **Interfejs użytkownika**: Jetpack Compose
- **Baza danych**: Room (lokalna baza SQLite)
- **Odtwarzacz multimedialny**: ExoPlayer
- **Sieć**: OkHttp do komunikacji z serwerem
- **Wysyłanie e-maili**: JavaMail API
- **Architektura**: MVVM (Model-View-ViewModel)
- **Nawigacja**: Jetpack Navigation
- **Kompilator LaTeX**: Brak (nie dotyczy tej aplikacji)
- **Zależności**:
  - `androidx.room` - do obsługi bazy danych
  - `androidx.media3` - do odtwarzania multimediów
  - `okhttp3` - do żądań sieciowych
  - `kotlinx.coroutines` - do zarządzania asynchronicznymi operacjami
  - `javax.mail` - do wysyłania e-maili

## Struktura projektu
- **com.example.yourgarden.data**:
  - `coupons/`: Zawiera `CouponsDao`, `CouponsEntity` i `Converters` do zarządzania kuponami w bazie danych.
  - `song/`: Zawiera `SongDao` i `SongEntity` do zarządzania utworami w bazie danych.
  - `GardenDatabase`: Abstrakcyjna klasa bazy danych Room, obsługująca tabele `songs` i `coupons` z migracjami.

- **com.example.yourgarden.ui**:
  - `screens/`: Zawiera ekrany aplikacji:
    - `HomeScreen`: Ekran główny z licznikiem dni i nawigacją.
    - `MusicList`: Ekran odtwarzacza muzycznego.
    - `CouponsScreen`: Ekran zarządzania kuponami.
  - `CouponsRepository`: Logika biznesowa dla kuponów.
  - `MusicListRepository`: Logika biznesowa dla muzyki.
  - `GardenApp`: Główny komponent nawigacyjny aplikacji.

- **com.example.yourgarden**:
  - `FileUtils`: Narzędzia do zarządzania plikami (zapisywanie i usuwanie utworów).
  - `MainActivity`: Główna aktywność aplikacji, inicjalizująca ViewModele i interfejs.

## Instalacja
1. Sklonuj repozytorium:
   ```bash
   git clone <adres-repozytorium>
   ```
2. Otwórz projekt w Android Studio.
3. Upewnij się, że masz skonfigurowane środowisko Android SDK z API level 26 lub wyższym.
4. Skompiluj i uruchom aplikację na emulatorze lub fizycznym urządzeniu z systemem Android.

## Konfiguracja
- **Serwer do pobierania muzyki**: Domyślny adres serwera to `http://192.168.1.8:5000`. Można go zmienić w ustawieniach aplikacji.
- **E-mail**: Aplikacja używa konta `yourgardenapp@gmail.com` do wysyłania powiadomień o aktywacji kuponów. Upewnij się, że hasło aplikacji (`lerktpsnljzurqcu`) jest poprawne lub skonfiguruj własne konto e-mail w `CouponsViewModel`.

## Użycie
1. **Muzyka**:
   - Przejdź do ekranu "Muzyka" z ekranu głównego.
   - Wyszukaj utwory lub przeglądaj listę dostępnych/pobranych utworów.
   - Kliknij ikonę pobierania, aby pobrać utwór, lub ikonę odtwarzania, aby odtworzyć pobrany utwór.
   - Użyj kontrolki odtwarzania, aby sterować odtwarzaniem.

2. **Kupony**:
   - Przejdź do ekranu "Kupony" z ekranu głównego.
   - Wybierz kupon z listy niewykorzystanych i kliknij "Aktywuj".
   - Potwierdź aktywację w oknie dialogowym.
   - Po aktywacji kupon zostanie przeniesiony do sekcji "Zużyte kupony", a powiadomienie e-mail zostanie wysłane.

3. **Licznik dni**:
   - Na ekranie głównym zobaczysz, ile dni i sekund upłynęło od 22.06.2024.

## Uwagi
- Aplikacja wymaga działającego serwera do pobierania muzyki z YouTube. Upewnij się, że serwer jest dostępny pod podanym adresem URL.
- W przypadku problemów z wysyłaniem e-maili sprawdź konfigurację konta e-mail i dostęp do internetu.
- Aplikacja jest zoptymalizowana dla urządzeń z Androidem 8.0 (API 26) i nowszych.

## Licencja
Aplikacja stworzona dla prywatnego użytku. Wszelkie prawa zastrzeżone.