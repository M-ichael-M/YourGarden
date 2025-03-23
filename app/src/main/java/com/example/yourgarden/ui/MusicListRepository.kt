package com.example.yourgarden.ui

import android.content.Context
import com.example.yourgarden.data.GardenDatabase
import com.example.yourgarden.data.song.SongDao
import com.example.yourgarden.data.song.SongEntity
import kotlinx.coroutines.flow.Flow

class MusicListRepository(context: Context) : SongDao {
    private val dao = GardenDatabase.getInstance(context).songDao()

    override suspend fun insertSong(song: SongEntity) = dao.insertSong(song)
    override fun getAllSongs(): Flow<List<SongEntity>> = dao.getAllSongs()
    override fun getDownloadedSongs(): Flow<List<SongEntity>> = dao.getDownloadedSongs()
    override suspend fun updateFilePath(youtubeUrl: String, filePath: String?) = dao.updateFilePath(youtubeUrl, filePath)
    override suspend fun deleteSong(id: Int) = dao.deleteSong(id)
    override suspend fun deleteAllDownloadedSongs() = dao.deleteAllDownloadedSongs()
    override suspend fun insertAll(songs: List<SongEntity>) = dao.insertAll(songs)

    override suspend fun updateDownloadStatus(youtubeUrl: String, status: String?) {
        dao.updateDownloadStatus(youtubeUrl, status)
    }

    suspend fun insertSampleSongs() {
        val sampleSongs = listOf(
            SongEntity(
                title = "Say you won't let go",
                artist = "James Arthur",
                duration = 210,
                youtubeUrl = "https://www.youtube.com/watch?v=0yW7w8F2TVA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Saturn",
                artist = "SZA",
                duration = 186,
                youtubeUrl = "https://www.youtube.com/watch?v=V2G8ESoDXm8&list=RDV2G8ESoDXm8&index=2",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "vampire heart",
                artist = "Isak Roen, Key Kelly",
                duration = 144,
                youtubeUrl = "https://www.youtube.com/watch?v=K_RNSpE8yVA&list=RDK_RNSpE8yVA&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Love Potions",
                artist = "BJ Lips, princess paparazzi",
                duration = 173,
                youtubeUrl = "https://www.youtube.com/watch?v=3DnGBRPCnv0&list=RD3DnGBRPCnv0&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Money Pull Up",
                artist = "Blaiz Fayah, Maureen, Dj Glad",
                duration = 128,
                youtubeUrl = "https://www.youtube.com/watch?v=u5mdt2Y_x3o&list=RDu5mdt2Y_x3o&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Blind",
                artist = "SZA",
                duration = 151,
                youtubeUrl = "https://www.youtube.com/watch?v=8UEM0dKbzAA&list=RD8UEM0dKbzAA&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Oscar Winning Tears.",
                artist = "RAYE",
                duration = 184,
                youtubeUrl = "https://www.youtube.com/watch?v=_cLIG8JiOyg&list=RD_cLIG8JiOyg&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Good Days",
                artist = "SZA",
                duration = 278,
                youtubeUrl = "https://www.youtube.com/watch?v=0BdlKkvjEgA&list=RD0BdlKkvjEgA&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Tejano Blue",
                artist = "Cigarettes After Sex",
                duration = 234,
                youtubeUrl = "https://www.youtube.com/watch?v=wWa0pIAbV-w&list=RDwWa0pIAbV-w&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Vidrado Em Você",
                artist = "Dj Guuga, Mc Livinho",
                duration = 135,
                youtubeUrl = "https://www.youtube.com/watch?v=OXMI_YpsSi4&list=RDOXMI_YpsSi4&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Naked",
                artist = "James Arthur",
                duration = 233,
                youtubeUrl = "https://www.youtube.com/watch?v=WXyLdg4mJxo&list=RDWXyLdg4mJxo&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Falling Like The Stars",
                artist = "James Arthur",
                duration = 213,
                youtubeUrl = "https://www.youtube.com/watch?v=PMGY8fLwess&list=RDPMGY8fLwess&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "That's So True",
                artist = "Gracie Abrams",
                duration = 166,
                youtubeUrl = "https://www.youtube.com/watch?v=W_YOJWZIjxo&list=RDW_YOJWZIjxo&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Wasted (feat. Lil Uzi Vert)",
                artist = "Juice WRLD, Lil Uzi Vert",
                duration = 258,
                youtubeUrl = "https://youtu.be/6n4wt6gj7pA?list=RD6n4wt6gj7pA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Can't Tell Me Nothing",
                artist = "Kanye West",
                duration = 272,
                youtubeUrl = "https://youtu.be/Vcljvd4Ef_o?list=RDVcljvd4Ef_o",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Diva",
                artist = "Beyoncé",
                duration = 201,
                youtubeUrl = "https://www.youtube.com/watch?v=3YiOMBAp2jA&list=RD3YiOMBAp2jA&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "The Cut That Always Bleeds",
                artist = "Conan Gray",
                duration = 232,
                youtubeUrl = "https://youtu.be/OuTLKgPyaF0?list=RDOuTLKgPyaF0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Lady Killers II",
                artist = "G-Eazy",
                duration = 298,
                youtubeUrl = "https://youtu.be/i9L_Ew4t1xg?list=RDi9L_Ew4t1xg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Don't Copy My Flow",
                artist = "фрози, Mwizz, George Kipa",
                duration = 172,
                youtubeUrl = "https://www.youtube.com/watch?v=cl_mQplBecI&list=RDcl_mQplBecI&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Desperado",
                artist = "Rihanna",
                duration = 186,
                youtubeUrl = "https://youtu.be/aD6pjhFOjFM?list=RDaD6pjhFOjFM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Islands (kompa pasión)",
                artist = "фрози, Tomo",
                duration = 126,
                youtubeUrl = "https://www.youtube.com/watch?v=iPp7wWVEU58&list=RDiPp7wWVEU58&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Not Like Us",
                artist = "Kendrick Lamar",
                duration = 274,
                youtubeUrl = "https://www.youtube.com/watch?v=T6eK-2OQtew&list=RDT6eK-2OQtew&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "luther (with sza)",
                artist = "Kendrick Lamar, SZA",
                duration = 178,
                youtubeUrl = "https://www.youtube.com/watch?v=HfWLgELllZs&list=RDHfWLgELllZs&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Long Way 2 Go",
                artist = "Cassie",
                duration = 221,
                youtubeUrl = "https://www.youtube.com/watch?v=020cX4wnguI&list=RD020cX4wnguI&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Like Him (feat. Lola Young)",
                artist = "Tyler, The Creator, Lola Young",
                duration = 278,
                youtubeUrl = "https://www.youtube.com/watch?v=dgUHE8wWhiE&list=RDdgUHE8wWhiE&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Empty Out Your Pockets",
                artist = "Juice WRLD",
                duration = 136,
                youtubeUrl = "https://youtu.be/hgYhws0AHcg?list=RDhgYhws0AHcg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Houdini",
                artist = "Eminem",
                duration = 227,
                youtubeUrl = "https://youtu.be/soNLLPokjC4?list=RDsoNLLPokjC4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "A Lesser Man",
                artist = "The Weeknd",
                duration = 299,
                youtubeUrl = "https://www.youtube.com/watch?v=bBqrgxAr_sk&list=RDbBqrgxAr_sk&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Billie Bossa Nova",
                artist = "Billie Eilish",
                duration = 197,
                youtubeUrl = "https://youtu.be/4tZ969oc-yI?list=RD4tZ969oc-yI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Move",
                artist = "Adam Port, Stryv, Keinemusik, Orso, Malachi",
                duration = 178,
                youtubeUrl = "https://youtu.be/si6Ox8IuZeU?list=RDsi6Ox8IuZeU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Often",
                artist = "The Weeknd",
                duration = 249,
                youtubeUrl = "https://youtu.be/JPIhUaONiLU?list=RDJPIhUaONiLU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "White Ferrari",
                artist = "Frank Ocean",
                duration = 249,
                youtubeUrl = "https://youtu.be/Dlz_XHeUUis?list=RDDlz_XHeUUis",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Peppers (feat. Tommy Genesis)",
                artist = "Lana Del Rey, Tommy Genesis",
                duration = 249,
                youtubeUrl = "https://youtu.be/hNFoCOKk7LE?list=RDhNFoCOKk7LE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "White Tee (with NO1-NOAH)",
                artist = "Summer Walker, NO1-NOAH",
                duration = 211,
                youtubeUrl = "https://youtu.be/LgJhcGl_h7Y?list=RDLgJhcGl_h7Y",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Pon de Replay",
                artist = "Rihanna",
                duration = 247,
                youtubeUrl = "https://youtu.be/oEauWw9ZGrA?list=RDoEauWw9ZGrA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "SI NO ES CONTIGO",
                artist = "Cris Mj",
                duration = 156,
                youtubeUrl = "https://youtu.be/vX1fgqKXSsk?list=RDvX1fgqKXSsk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Save Yourself",
                artist = "KALEO",
                duration = 274,
                youtubeUrl = "https://youtu.be/oCi0RHLrauU?list=RDoCi0RHLrauU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Shape Of My Heart",
                artist = "Sting",
                duration = 279,
                youtubeUrl = "https://www.youtube.com/watch?v=NlwIDxCjL-8&list=RDNlwIDxCjL-8&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "National Anthem",
                artist = "Lana Del Rey",
                duration = 231,
                youtubeUrl = "https://youtu.be/DuEEnZk4-48?list=RDDuEEnZk4-48",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Snooze",
                artist = "SZA",
                duration = 202,
                youtubeUrl = "https://youtu.be/Sv5yCzPCkv8?list=RDSv5yCzPCkv8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Habits",
                artist = "Genevieve Stokes",
                duration = 135,
                youtubeUrl = "https://youtu.be/SCjZUxmCK8g?list=RDSCjZUxmCK8g",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Hometown",
                artist = "Twenty One Pilots",
                duration = 235,
                youtubeUrl = "https://youtu.be/pJtlLzsDICo?list=RDpJtlLzsDICo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "EINECEIWSO 008-1",
                artist = "Taco Hemingway",
                duration = 128,
                youtubeUrl = "https://youtu.be/D_s5uT7SJBs?list=RDD_s5uT7SJBs",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "AGATS2 (Insecure) [with Nicki Minaj]",
                artist = "Juice WRLD, Nicki Minaj",
                duration = 199,
                youtubeUrl = "https://youtu.be/lVMN4KSK1-8?list=RDlVMN4KSK1-8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Timeless (feat. Playboi Carti)",
                artist = "The Weeknd, Playboi Carti",
                duration = 256,
                youtubeUrl = "https://youtu.be/5EpyN_6dqyk?list=RD5EpyN_6dqyk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Tak to leciało!",
                artist = "Otscohodzi, Taco Hemingway, lohleg",
                duration = 158,
                youtubeUrl = "https://youtu.be/0c1wRHRJNUs?list=RD0c1wRHRJNUs",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Party Monster",
                artist = "The Weeknd",
                duration = 249,
                youtubeUrl = "https://youtu.be/j9Hije4z6O4?list=RDj9Hije4z6O4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Bel Air",
                artist = "Lana Del Rey",
                duration = 238,
                youtubeUrl = "https://youtu.be/FVKU-NM6sBo?list=RDFVKU-NM6sBo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "São Paulo (feat. Anitta)",
                artist = "The Weeknd, Anitta",
                duration = 302,
                youtubeUrl = "https://youtu.be/AQ5NlI-SJR0?list=RDAQ5NlI-SJR0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Lucid Dreams",
                artist = "Juice WRLD",
                duration = 240,
                youtubeUrl = "https://youtu.be/mzB1VGEGcSU?list=RDmzB1VGEGcSU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Fiji",
                artist = "Taco Hemingway",
                duration = 243,
                youtubeUrl = "https://youtu.be/lk70ee3UMAc?list=RDlk70ee3UMAc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Ginseng Strip 2002",
                artist = "Yung Lean",
                duration = 154,
                youtubeUrl = "https://youtu.be/vrQWhFysPKY?list=RDvrQWhFysPKY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "I Smoked Away My Brain (I'm God x Demons Mashup) (feat. A\$AP Rocky, Imogen Heap, Clams Casino)",
                artist = "A\$AP Rocky, Imogen Heap, Clams Casino",
                duration = 190,
                youtubeUrl = "https://www.youtube.com/watch?v=AT9JoIv2kss&list=RDAT9JoIv2kss&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "My Mom",
                artist = "Eminem",
                duration = 320,
                youtubeUrl = "https://www.youtube.com/watch?v=Buxejhpbois&list=RDBuxejhpbois&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "My Name Is",
                artist = "Eminem",
                duration = 268,
                youtubeUrl = "https://youtu.be/sNPnbI1arSE?list=RDsNPnbI1arSE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Gangsta",
                artist = "Kehlani",
                duration = 177,
                youtubeUrl = "https://youtu.be/LAYgZEMMWxo?list=RDLAYgZEMMWxo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Ivy",
                artist = "Frank Ocean",
                duration = 249,
                youtubeUrl = "https://youtu.be/AE005nZeF-A?list=RDAE005nZeF-A",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "No.1 Party Anthem",
                artist = "Arctic Monkeys",
                duration = 243,
                youtubeUrl = "https://youtu.be/mGUjVbsYG6E?list=RDmGUjVbsYG6E",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Fluorescent Adolescent",
                artist = "Arctic Monkeys",
                duration = 184,
                youtubeUrl = "https://youtu.be/ma9I9VBKPiw?list=RDma9I9VBKPiw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "(Przerywnik)",
                artist = "Taco Hemingway",
                duration = 132,
                youtubeUrl = "https://youtu.be/HEEZOuH7IAc?list=RDHEEZOuH7IAc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Salvatore",
                artist = "Lana Del Rey",
                duration = 281,
                youtubeUrl = "https://youtu.be/GVQON-muEFc?list=RDGVQON-muEFc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "This Charming Man - 2011 Remaster",
                artist = "The Smiths",
                duration = 163,
                youtubeUrl = "https://youtu.be/cJRP3LRcUFg?list=RDcJRP3LRcUFg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Constellations",
                artist = "Jade LeMac",
                duration = 200,
                youtubeUrl = "https://www.youtube.com/watch?v=ZLkCUhmwLdw&list=RDZLkCUhmwLdw&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Sweet Oblivion",
                artist = "David Kushner",
                duration = 135,
                youtubeUrl = "https://youtu.be/UhvoMXc_tN8?list=RDUhvoMXc_tN8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Everyday (feat. Rod Stewart, Miguel & Mark Ronson)",
                artist = "A\$AP Rocky, Rod Stewart, Miguel, Mark Ronson",
                duration = 261,
                youtubeUrl = "https://youtu.be/UtZBA1bVbcs?list=RDUtZBA1bVbcs",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Pretty When You Cry",
                artist = "Lana Del Rey",
                duration = 234,
                youtubeUrl = "https://youtu.be/2CSWw2OVxio?list=RD2CSWw2OVxio",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Sad Girl",
                artist = "Lana Del Rey",
                duration = 318,
                youtubeUrl = "https://youtu.be/jvm6DpqqbLk?list=RDjvm6DpqqbLk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Si No Estás",
                artist = "ińigo quintero",
                duration = 184,
                youtubeUrl = "https://youtu.be/vldYYjCQ7jc?list=RDvldYYjCQ7jc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Kiss Me",
                artist = "Sixpence None The Richer",
                duration = 209,
                youtubeUrl = "https://youtu.be/Jnq9wPDoDKg?list=RDJnq9wPDoDKg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Stop The World I Wanna Get Off With You",
                artist = "Arctic Monkeys",
                duration = 191,
                youtubeUrl = "https://youtu.be/3PyoxMSEHYI?list=RD3PyoxMSEHYI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Little Dark Age",
                artist = "MGMT",
                duration = 300,
                youtubeUrl = "https://youtu.be/rtL5oMyBHPs?list=RDrtL5oMyBHPs",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Vanished",
                artist = "Crystal Castles",
                duration = 243,
                youtubeUrl = "https://youtu.be/6e6Hj7MwWaI?list=RD6e6Hj7MwWaI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "I Love You, I'm Sorry",
                artist = "Gracie Abrams",
                duration = 157,
                youtubeUrl = "https://youtu.be/ZWGt1jMIjBY?list=RDZWGt1jMIjBY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Pour It Up",
                artist = "Rihanna",
                duration = 161,
                youtubeUrl = "https://youtu.be/ehcVomMexkY?list=RDehcVomMexkY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Lloret de Mar",
                artist = "Mata",
                duration = 126,
                youtubeUrl = "https://www.youtube.com/watch?v=6mB-EH98PiA&list=RD6mB-EH98PiA&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Love On The Brain",
                artist = "Rihanna",
                duration = 224, // 224000 ms
                youtubeUrl = "https://www.youtube.com/watch?v=QMP-o8WXSPM&list=RDQMP-o8WXSPM&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Escapism. - Sped Up",
                artist = "RAYE,070 Shake",
                duration = 240, // 239888 ms
                youtubeUrl = "https://www.youtube.com/watch?v=Yw__siIXUTo&list=RDYw__siIXUTo&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Summertime Sadness",
                artist = "Lana Del Rey",
                duration = 265, // 265427 ms
                youtubeUrl = "https://youtu.be/TdrL3QxjyVw?list=RDTdrL3QxjyVw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Video Games",
                artist = "Lana Del Rey",
                duration = 282, // 281960 ms
                youtubeUrl = "https://youtu.be/cE6wxDqdOV0?list=RDcE6wxDqdOV0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "watch",
                artist = "Billie Eilish",
                duration = 178, // 177523 ms
                youtubeUrl = "https://youtu.be/9dobJDxPEzM?list=RD9dobJDxPEzM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Radio",
                artist = "Lana Del Rey",
                duration = 215, // 214573 ms
                youtubeUrl = "https://youtu.be/7leEmq6EMMs?list=RD7leEmq6EMMs",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "It Was A Good Day",
                artist = "Ice Cube",
                duration = 260, // 260000 ms
                youtubeUrl = "https://youtu.be/h4UqMyldS7Q?list=RDh4UqMyldS7Q",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Radioactive",
                artist = "Imagine Dragons",
                duration = 187, // 186813 ms
                youtubeUrl = "https://youtu.be/ktvTqknDobU?list=RDktvTqknDobU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "The Night We Met",
                artist = "Lord Huron",
                duration = 208, // 208211 ms
                youtubeUrl = "https://youtu.be/wGF7PswOENQ?list=RDwGF7PswOENQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "505",
                artist = "Arctic Monkeys",
                duration = 254, // 253586 ms
                youtubeUrl = "https://youtu.be/qU9mHegkTc4?list=RDqU9mHegkTc4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Out of My League",
                artist = "Fitz and The Tantrums",
                duration = 209, // 209386 ms
                youtubeUrl = "https://www.youtube.com/watch?v=Z4mbxaa3XL8&list=RDZ4mbxaa3XL8&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Come A Little Closer",
                artist = "Cage The Elephant",
                duration = 229, // 229346 ms
                youtubeUrl = "https://youtu.be/KVYup3Qwh8Q?list=RDKVYup3Qwh8Q",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Dynasty",
                artist = "MIIA",
                duration = 226, // 225515 ms
                youtubeUrl = "https://youtu.be/pY8oa8XS3ko?list=RDpY8oa8XS3ko",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "the grudge",
                artist = "Olivia Rodrigo",
                duration = 189, // 189386 ms
                youtubeUrl = "https://youtu.be/Qt5wB7KXSaM?list=RDQt5wB7KXSaM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "teenage dream",
                artist = "Olivia Rodrigo",
                duration = 222, // 222358 ms
                youtubeUrl = "https://www.youtube.com/watch?v=F33uCg-3XiY&list=RDF33uCg-3XiY&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "ballad of a homeschooled girl",
                artist = "Olivia Rodrigo",
                duration = 203, // 203369 ms
                youtubeUrl = "https://youtu.be/obSpLSnJ-wI?list=RDobSpLSnJ-wI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Seventeen",
                artist = "MARINA",
                duration = 182, // 182160 ms
                youtubeUrl = "https://youtu.be/TPAZe6bMk2o?list=RDTPAZe6bMk2o",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "W Dobrą Stronę",
                artist = "Dawid Podsiadło",
                duration = 214, // 214416 ms
                youtubeUrl = "https://youtu.be/4ArKq8r7quY?list=RD4ArKq8r7quY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Diet Mountain Dew",
                artist = "Lana Del Rey",
                duration = 223, // 222920 ms
                youtubeUrl = "https://youtu.be/qtBxUoUkG8s?list=RDqtBxUoUkG8s",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Blue Jeans",
                artist = "Lana Del Rey",
                duration = 209, // 209440 ms
                youtubeUrl = "https://youtu.be/9p15MdpVAO0?list=RD9p15MdpVAO0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Goth",
                artist = "Sidewalks and Skeletons",
                duration = 207, // 207120 ms
                youtubeUrl = "https://youtu.be/vOb3id28dVA?list=RDvOb3id28dVA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Chemtrails Over The Country Club",
                artist = "Lana Del Rey",
                duration = 271, // 271176 ms
                youtubeUrl = "https://youtu.be/vBHild0PiTE?list=RDvBHild0PiTE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "making the bed",
                artist = "Olivia Rodrigo",
                duration = 199, // 198866 ms
                youtubeUrl = "https://youtu.be/OuuBnf2aGCc?list=RDOuuBnf2aGCc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Fade Into You",
                artist = "Mazzy Star",
                duration = 296, // 295600 ms
                youtubeUrl = "https://youtu.be/ImKY6TZEyrI?list=RDImKY6TZEyrI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Here With Me",
                artist = "d4vd",
                duration = 242, // 242484 ms
                youtubeUrl = "https://youtu.be/Ip6cw8gfHHI?list=RDIp6cw8gfHHI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Space Song",
                artist = "Beach House",
                duration = 320, // 320466 ms
                youtubeUrl = "https://youtu.be/RBtlPT23PTM?list=RDRBtlPT23PTM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "pretty isn’t pretty",
                artist = "Olivia Rodrigo",
                duration = 199, // 199422 ms
                youtubeUrl = "https://youtu.be/G0R7Z2A0XeY?list=RDG0R7Z2A0XeY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "all-american bitch",
                artist = "Olivia Rodrigo",
                duration = 166, // 165833 ms
                youtubeUrl = "https://youtu.be/n2BnbpjpRdo?list=RDn2BnbpjpRdo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Good Looking",
                artist = "Suki Waterhouse",
                duration = 215, // 214800 ms
                youtubeUrl = "https://youtu.be/RM3Lvhd899I?list=RDRM3Lvhd899I",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Her Way - Sped Up",
                artist = "PARTYNEXTDOOR",
                duration = 182, // 182164 ms
                youtubeUrl = "https://youtu.be/C3_vcflfBiQ?list=RDC3_vcflfBiQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Loveeeeeee Song",
                artist = "Rihanna,Future",
                duration = 256, // 256320 ms
                youtubeUrl = "https://www.youtube.com/watch?v=SybwPySl-fs&list=RDSybwPySl-fs&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Let The Light In (feat. Father John Misty)",
                artist = "Lana Del Rey,Father John Misty",
                duration = 278, // 278126 ms
                youtubeUrl = "https://youtu.be/WJlQ4jt5Fz4?list=RDWJlQ4jt5Fz4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Está Dañada",
                artist = "Ivan Cornejo",
                duration = 214, // 214148 ms
                youtubeUrl = "https://youtu.be/OdDzZvwUmJE?list=RDOdDzZvwUmJE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Somewhere Only We Know",
                artist = "Keane",
                duration = 237, // 237146 ms
                youtubeUrl = "https://youtu.be/Oextk-If8HQ?list=RDOextk-If8HQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Feeling Good",
                artist = "Michael Bublé",
                duration = 237, // 237333 ms
                youtubeUrl = "https://youtu.be/Edwsf-8F3sI?list=RDEdwsf-8F3sI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Another Love",
                artist = "Tom Odell",
                duration = 244, // 244360 ms
                youtubeUrl = "https://youtu.be/MwpMEbgC7DA?list=RDMwpMEbgC7DA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Doubt",
                artist = "Twenty One Pilots",
                duration = 191, // 191493 ms
                youtubeUrl = "https://youtu.be/MEiVnNNpJLA?list=RDMEiVnNNpJLA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Him & I (with Halsey)",
                artist = "G-Eazy,Halsey",
                duration = 269, // 268866 ms
                youtubeUrl = "https://youtu.be/SA7AIQw-7Ms?list=RDSA7AIQw-7Ms",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Unforgettable",
                artist = "French Montana,Swae Lee",
                duration = 234, // 233901 ms
                youtubeUrl = "https://youtu.be/CTFtOOh47oo?list=RDCTFtOOh47oo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Skyfall",
                artist = "Adele",
                duration = 286, // 286480 ms
                youtubeUrl = "https://youtu.be/DeumyOzKqgI?list=RDDeumyOzKqgI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Remember When",
                artist = "Wallows",
                duration = 155, // 155146 ms
                youtubeUrl = "https://youtu.be/lom6I3EgynY?list=RDlom6I3EgynY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "See You Again (feat. Kali Uchis)",
                artist = "Tyler, The Creator,Kali Uchis",
                duration = 180, // 180386 ms
                youtubeUrl = "https://youtu.be/TGgcC5xg9YI?list=RDTGgcC5xg9YI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Apocalypse",
                artist = "Cigarettes After Sex",
                duration = 291, // 290616 ms
                youtubeUrl = "https://youtu.be/sElE_BfQ67s?list=RDsElE_BfQ67s",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Promiscuous",
                artist = "Nelly Furtado,Timbaland",
                duration = 242, // 242293 ms
                youtubeUrl = "https://www.youtube.com/watch?v=0J3vgcE5i2o&list=RD0J3vgcE5i2o&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "S&M",
                artist = "Rihanna",
                duration = 244, // 243533 ms
                youtubeUrl = "https://youtu.be/Ce2_k0LaE7E?list=RDCe2_k0LaE7E",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "I Was Never There",
                artist = "The Weeknd,Gesaffelstein",
                duration = 241, // 241066 ms
                youtubeUrl = "https://youtu.be/OlStmta0Vh4?list=RDOlStmta0Vh4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Margaret (feat. Bleachers)",
                artist = "Lana Del Rey,Bleachers",
                duration = 340, // 339890 ms
                youtubeUrl = "https://youtu.be/2xtKhqbNBoY?list=RD2xtKhqbNBoY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Pink + White",
                artist = "Frank Ocean",
                duration = 185, // 184516 ms
                youtubeUrl = "https://youtu.be/uzS3WG6__G4?list=RDuzS3WG6__G4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Falling In Love",
                artist = "Cigarettes After Sex",
                duration = 246, // 245840 ms
                youtubeUrl = "https://youtu.be/AXupa6hEDYk?list=RDAXupa6hEDYk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Do I Wanna Know?",
                artist = "Arctic Monkeys",
                duration = 272, // 272394 ms
                youtubeUrl = "https://youtu.be/bpOSxM0rNPM?list=RDbpOSxM0rNPM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "No Time To Die",
                artist = "Billie Eilish",
                duration = 242, // 242265 ms
                youtubeUrl = "https://youtu.be/BboMpayJomw?list=RDBboMpayJomw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Friends",
                artist = "Chase Atlantic",
                duration = 230, // 230010 ms
                youtubeUrl = "https://youtu.be/nT8O_mP2x6Y?list=RDnT8O_mP2x6Y",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "I Feel Like I'm Drowning",
                artist = "Two Feet",
                duration = 186, // 185520 ms
                youtubeUrl = "https://youtu.be/i_WTHkBuqbg?list=RDi_WTHkBuqbg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "hostage",
                artist = "Billie Eilish",
                duration = 229, // 229425 ms
                youtubeUrl = "https://www.youtube.com/watch?v=skHbZBsS7hM&list=RDskHbZBsS7hM&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Whatcha Say",
                artist = "Jason Derulo",
                duration = 221, // 221253 ms
                youtubeUrl = "https://youtu.be/pBI3lc18k8Q?list=RDpBI3lc18k8Q",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Stargirl Interlude",
                artist = "The Weeknd,Lana Del Rey",
                duration = 112, // 111626 ms
                youtubeUrl = "https://youtu.be/TkxVOa6u59M?list=RDTkxVOa6u59M",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Waterloo",
                artist = "ABBA",
                duration = 169, // 168960 ms
                youtubeUrl = "https://youtu.be/Sj_9CiNkkn4?list=RDSj_9CiNkkn4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Scared To Live",
                artist = "The Weeknd",
                duration = 191, // 191306 ms
                youtubeUrl = "https://youtu.be/MzsU_sn2aIE?list=RDMzsU_sn2aIE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "SOS",
                artist = "Rihanna",
                duration = 239, // 238920 ms
                youtubeUrl = "https://youtu.be/OFfVQCVzjZU?list=RDOFfVQCVzjZU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Happier Than Ever",
                artist = "Billie Eilish",
                duration = 299, // 298899 ms
                youtubeUrl = "https://www.youtube.com/watch?v=5GJWxDKyk3A&list=RD5GJWxDKyk3A&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Napraw",
                artist = "LemON",
                duration = 192, // 192200 ms
                youtubeUrl = "https://youtu.be/9GQqF8Q7wQY?list=RD9GQqF8Q7wQY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "The Real Slim Shady",
                artist = "Eminem",
                duration = 284, // 284200 ms
                youtubeUrl = "https://youtu.be/1-M4JrFcrNY?list=RD1-M4JrFcrNY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Murder On The Dancefloor",
                artist = "Sophie Ellis-Bextor",
                duration = 230, // 230013 ms
                youtubeUrl = "https://youtu.be/hAx6mYeC6pY?list=RDhAx6mYeC6pY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Fairytale",
                artist = "Alexander Rybak",
                duration = 183, // 183266 ms
                youtubeUrl = "https://youtu.be/1rVY08gRGmA?list=RD1rVY08gRGmA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "This Is The Life - Sped Up Version",
                artist = "Amy Macdonald,Speed Radio",
                duration = 169, // 169107 ms
                youtubeUrl = "https://youtu.be/OLAnL1IocEo?list=RDOLAnL1IocEo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Bohemian Rhapsody",
                artist = "Queen",
                duration = 355, // 354947 ms
                youtubeUrl = "https://youtu.be/fJ9rUzIMcZQ?list=RDfJ9rUzIMcZQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Trofea",
                artist = "Dawid Podsiadło",
                duration = 225, // 224986 ms
                youtubeUrl = "https://youtu.be/MWzb9nXsx3s?list=RDMWzb9nXsx3s",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Freaks",
                artist = "Surf Curse",
                duration = 147, // 147062 ms
                youtubeUrl = "https://youtu.be/i-sm16yMZhg?list=RDi-sm16yMZhg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Blue Hair",
                artist = "TV Girl",
                duration = 217, // 217000 ms
                youtubeUrl = "https://youtu.be/bZ3ogQjiBcc?list=RDbZ3ogQjiBcc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Revenge",
                artist = "XXXTENTACION",
                duration = 120, // 120026 ms
                youtubeUrl = "https://youtu.be/e3U1TKgwoxE?list=RDe3U1TKgwoxE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "#doyalike",
                artist = "mikeeysmind",
                duration = 183, // 183054 ms
                youtubeUrl = "https://youtu.be/DlbvOmhGYAQ?list=RDDlbvOmhGYAQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "i love you",
                artist = "Billie Eilish",
                duration = 292, // 291796 ms
                youtubeUrl = "https://youtu.be/WiinVuzh4DA?list=RDWiinVuzh4DA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Love The Way You Lie",
                artist = "Eminem,Rihanna",
                duration = 263, // 263373 ms
                youtubeUrl = "https://www.youtube.com/watch?v=uelHwf8o7_U&list=RDuelHwf8o7_U&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Die For You",
                artist = "The Weeknd",
                duration = 260, // 260252 ms
                youtubeUrl = "https://youtu.be/QLCpqdqeoII?list=RDQLCpqdqeoII",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Dead Man",
                artist = "David Kushner",
                duration = 275, // 274870 ms
                youtubeUrl = "https://youtu.be/RljwsGVAD-0?list=RDRljwsGVAD-0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "vampire",
                artist = "Olivia Rodrigo",
                duration = 220, // 219724 ms
                youtubeUrl = "https://youtu.be/Fqey8LxQxFU?list=RDFqey8LxQxFU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "ballad of a homeschooled girl",
                artist = "Olivia Rodrigo",
                duration = 203, // 203369 ms
                youtubeUrl = "https://youtu.be/obSpLSnJ-wI?list=RDFqey8LxQxFU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Paper Rings",
                artist = "Taylor Swift",
                duration = 222, // 222400 ms
                youtubeUrl = "https://youtu.be/8zdg-pDF10g?list=RD8zdg-pDF10g",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Mr. Forgettable",
                artist = "David Kushner",
                duration = 187, // 187293 ms
                youtubeUrl = "https://youtu.be/ZA-0Fweuu0g?list=RDZA-0Fweuu0g",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Następna stacja",
                artist = "Taco Hemingway",
                duration = 252, // 251826 ms
                youtubeUrl = "https://youtu.be/TZgBIbqtDnQ?list=RDTZgBIbqtDnQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Reminder",
                artist = "The Weeknd",
                duration = 219, // 218880 ms
                youtubeUrl = "https://youtu.be/JZjAg6fK-BQ?list=RDJZjAg6fK-BQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Why I Love You",
                artist = "JAY-Z,Kanye West,Mr Hudson",
                duration = 201, // 201373 ms
                youtubeUrl = "https://youtu.be/HVD4lnfz0-M?list=RDHVD4lnfz0-M",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Reflections",
                artist = "The Neighbourhood",
                duration = 244, // 244013 ms
                youtubeUrl = "https://youtu.be/x47TgeRJtH0?list=RDx47TgeRJtH0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "If We Have Each Other",
                artist = "Alec Benjamin",
                duration = 182, // 181522 ms
                youtubeUrl = "https://youtu.be/tscMSXk_jaQ?list=RDtscMSXk_jaQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Softcore",
                artist = "The Neighbourhood",
                duration = 206, // 206280 ms
                youtubeUrl = "https://youtu.be/ggG9ySCChYw?list=RDggG9ySCChYw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Superman",
                artist = "Eminem,Dina Rae",
                duration = 350, // 350320 ms
                youtubeUrl = "https://youtu.be/lPlePBCS6Ic?list=RDlPlePBCS6Ic",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Mount Everest",
                artist = "Labrinth",
                duration = 157, // 157497 ms
                youtubeUrl = "https://youtu.be/4yPZs81lK3w?list=RD4yPZs81lK3w",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Save Your Tears",
                artist = "The Weeknd",
                duration = 216, // 215626 ms
                youtubeUrl = "https://youtu.be/XXYlFuWEuKI?list=RDXXYlFuWEuKI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Never Felt So Alone",
                artist = "Labrinth",
                duration = 160, // 160166 ms
                youtubeUrl = "https://www.youtube.com/watch?v=pqCVMRcj_bc&list=RDpqCVMRcj_bc&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "This Could Be Us",
                artist = "Rae Sremmurd",
                duration = 206, // 206306 ms
                youtubeUrl = "https://youtu.be/SYbh6ob_R9M?list=RDSYbh6ob_R9M",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Cigarette Daydreams",
                artist = "Cage The Elephant",
                duration = 209, // 208760 ms
                youtubeUrl = "https://youtu.be/opeETnB8m8w?list=RDopeETnB8m8w",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Can't Hold Us (feat. Ray Dalton)",
                artist = "Macklemore,Ryan Lewis,Macklemore & Ryan Lewis,Ray Dalton",
                duration = 258, // 258342 ms
                youtubeUrl = "https://youtu.be/VG3JsmOmDqw?list=RDVG3JsmOmDqw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Mockingbird",
                artist = "Eminem",
                duration = 251, // 250760 ms
                youtubeUrl = "https://youtu.be/37V3b39_EXw?list=RD37V3b39_EXw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Flashing Lights",
                artist = "Kanye West,Dwele",
                duration = 238, // 237506 ms
                youtubeUrl = "https://youtu.be/ZAz3rnLGthg?list=RDZAz3rnLGthg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Unwritten",
                artist = "Natasha Bedingfield",
                duration = 259, // 259333 ms
                youtubeUrl = "https://youtu.be/b7k0a5hYnSI?list=RDb7k0a5hYnSI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "deja vu",
                artist = "Olivia Rodrigo",
                duration = 216, // 215506 ms
                youtubeUrl = "https://youtu.be/cii6ruuycQA?list=RDcii6ruuycQA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Leave a Light On",
                artist = "Tom Walker",
                duration = 186, // 185863 ms
                youtubeUrl = "https://youtu.be/nqnkBdExjws?list=RDnqnkBdExjws",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Moth To A Flame (with The Weeknd)",
                artist = "Swedish House Mafia,The Weeknd",
                duration = 234, // 234000 ms
                youtubeUrl = "https://youtu.be/u9n7Cw-4_HQ?list=RDu9n7Cw-4_HQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Earned It (Fifty Shades Of Grey)",
                artist = "The Weeknd",
                duration = 278, // 277724 ms
                youtubeUrl = "https://youtu.be/waU75jdUnYw?list=RDwaU75jdUnYw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "drivers license",
                artist = "Olivia Rodrigo",
                duration = 242, // 242013 ms
                youtubeUrl = "https://youtu.be/ZmDBbnmKpqQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "When I Was Your Man",
                artist = "Bruno Mars",
                duration = 214, // 213826 ms
                youtubeUrl = "https://youtu.be/ekzHIouo8Q4?list=RDekzHIouo8Q4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "bad idea right?",
                artist = "Olivia Rodrigo",
                duration = 185, // 184783 ms
                youtubeUrl = "https://youtu.be/Dj9qJsJTsjQ?list=RDDj9qJsJTsjQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Wicked Games",
                artist = "The Weeknd",
                duration = 325, // 325305 ms
                youtubeUrl = "https://youtu.be/O1OTWCd40bc?list=RDO1OTWCd40bc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "As It Was",
                artist = "Harry Styles",
                duration = 167, // 167303 ms
                youtubeUrl = "https://www.youtube.com/watch?v=H5v3kku4y6Q&list=RDH5v3kku4y6Q&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Alleyways",
                artist = "The Neighbourhood",
                duration = 268, // 267853 ms
                youtubeUrl = "https://youtu.be/qVwckL8Q3_Y?list=RDqVwckL8Q3_Y",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Sex, Drugs, Etc.",
                artist = "Beach Weather",
                duration = 197, // 196784 ms
                youtubeUrl = "https://youtu.be/FgS1b2nU8AQ?list=RDFgS1b2nU8AQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Can't Pretend",
                artist = "Tom Odell",
                duration = 218, // 217840 ms
                youtubeUrl = "https://youtu.be/dUmtXzuSGu8?list=RDdUmtXzuSGu8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Hate Yourself",
                artist = "TV Girl",
                duration = 214, // 213760 ms
                youtubeUrl = "https://youtu.be/6-VcVpnaocg?list=RD6-VcVpnaocg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Flawless",
                artist = "The Neighbourhood",
                duration = 246, // 246493 ms
                youtubeUrl = "https://youtu.be/Cs9M0a5qYko?list=RDCs9M0a5qYko",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Why'd You Only Call Me When You're High?",
                artist = "Arctic Monkeys",
                duration = 161, // 161123 ms
                youtubeUrl = "https://youtu.be/3oK22ll4cxw?list=RD3oK22ll4cxw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Boys Will Be Bugs",
                artist = "Cavetown",
                duration = 329, // 329049 ms
                youtubeUrl = "https://youtu.be/uREGk0fT0GQ?list=RDuREGk0fT0GQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Stressed Out",
                artist = "Twenty One Pilots",
                duration = 202, // 202333 ms
                youtubeUrl = "https://youtu.be/pXRviuL6vMY?list=RDpXRviuL6vMY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Love Yourz",
                artist = "J. Cole",
                duration = 211, // 211496 ms
                youtubeUrl = "https://youtu.be/ZPCAvzIFY-s?list=RDZPCAvzIFY-s",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Zadzwoń do mnie",
                artist = "PRO8L3M",
                duration = 153, // 152853 ms
                youtubeUrl = "https://youtu.be/rafCt4jl5ek?list=RDrafCt4jl5ek",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "EA (feat. 21 Savage)",
                artist = "Young Nudy,21 Savage",
                duration = 227, // 227175 ms
                youtubeUrl = "https://youtu.be/piAAJqPbrBU?list=RDpiAAJqPbrBU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Without Me",
                artist = "Eminem",
                duration = 290, // 290320 ms
                youtubeUrl = "https://www.youtube.com/watch?v=YVkUvmDQ3HY&list=RDYVkUvmDQ3HY&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Deszcz na betonie (?)",
                artist = "Taco Hemingway",
                duration = 233, // 233466 ms
                youtubeUrl = "https://youtu.be/PCQs3vSJ6xA?list=RDPCQs3vSJ6xA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Marmur",
                artist = "Taco Hemingway",
                duration = 310, // 310213 ms
                youtubeUrl = "https://youtu.be/LopWRJj0i4k?list=PLOgab_kIovBajMmIKl5N6-bgX2pTNTjqe",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Lost in the Fire (feat. The Weeknd)",
                artist = "Gesaffelstein,The Weeknd",
                duration = 202, // 202093 ms
                youtubeUrl = "https://youtu.be/vP51S9i0HFM?list=RDvP51S9i0HFM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Grenade",
                artist = "Bruno Mars",
                duration = 222, // 222091 ms
                youtubeUrl = "https://youtu.be/4YrzJ9RZ9qY?list=RD4YrzJ9RZ9qY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Where Have You Been",
                artist = "Rihanna",
                duration = 243, // 242680 ms
                youtubeUrl = "https://youtu.be/HBxt_v0WF6Y?list=RDHBxt_v0WF6Y",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Unfaithful",
                artist = "Rihanna",
                duration = 227, // 226973 ms
                youtubeUrl = "https://youtu.be/rp4UwPZfRis?list=RDrp4UwPZfRis",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Me, Myself & I",
                artist = "G-Eazy,Bebe Rexha",
                duration = 251, // 251466 ms
                youtubeUrl = "https://youtu.be/bSfpSOBD30U?list=RDbSfpSOBD30U",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Someone Like You",
                artist = "Adele",
                duration = 285, // 285240 ms
                youtubeUrl = "https://youtu.be/hLQl3WQQoQ0?list=RDhLQl3WQQoQ0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Wild Thoughts (feat. Rihanna & Bryson Tiller)",
                artist = "DJ Khaled,Rihanna,Bryson Tiller",
                duration = 204, // 204173 ms
                youtubeUrl = "https://youtu.be/fyaI4-5849w?list=RDfyaI4-5849w",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "You Get Me So High",
                artist = "The Neighbourhood",
                duration = 153, // 153000 ms
                youtubeUrl = "https://youtu.be/jCSvOtUaI8s?list=RDjCSvOtUaI8s",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Not Allowed",
                artist = "TV Girl",
                duration = 168, // 167864 ms
                youtubeUrl = "https://youtu.be/r0xTqwKMOyk?list=RDr0xTqwKMOyk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Way down We Go",
                artist = "KALEO",
                duration = 214, // 213706 ms
                youtubeUrl = "https://youtu.be/0-7IHOXkiV8?list=RD0-7IHOXkiV8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Heal",
                artist = "Tom Odell",
                duration = 193, // 193080 ms
                youtubeUrl = "https://youtu.be/prsRiVTKqYg?list=RDprsRiVTKqYg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Norman fucking Rockwell",
                artist = "Lana Del Rey",
                duration = 249, // 248934 ms
                youtubeUrl = "https://youtu.be/Dx5JXQescLc?list=RDDx5JXQescLc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "The Other Woman",
                artist = "Lana Del Rey",
                duration = 182, // 181960 ms
                youtubeUrl = "https://www.youtube.com/watch?v=7KzPh_x5w14&list=RD7KzPh_x5w14&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Sunsetz",
                artist = "Cigarettes After Sex",
                duration = 215, // 215150 ms
                youtubeUrl = "https://www.youtube.com/watch?v=5-rbSNzU_b8&list=RD5-rbSNzU_b8&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Strange",
                artist = "Celeste",
                duration = 256, // 255986 ms
                youtubeUrl = "https://youtu.be/A1AJEv50Ld4?list=RDA1AJEv50Ld4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Skin and Bones",
                artist = "David Kushner",
                duration = 215, // 214632 ms
                youtubeUrl = "https://youtu.be/2JEv8AVW8vw?list=RD2JEv8AVW8vw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Teenage Dream",
                artist = "Stephen Dawes",
                duration = 178, // 178000 ms
                youtubeUrl = "https://youtu.be/F33uCg-3XiY?list=RDF33uCg-3XiY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Six Feet Under",
                artist = "Billie Eilish",
                duration = 190, // 189613 ms
                youtubeUrl = "https://youtu.be/FQ0iq10ULNA?list=RDFQ0iq10ULNA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Nie Kłami",
                artist = "Dawid Podsiadło",
                duration = 196, // 195666 ms
                youtubeUrl = "https://youtu.be/PgdKPlayQwE?list=RDPgdKPlayQwE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Bad Habit",
                artist = "Steve Lacy",
                duration = 232, // 232066 ms
                youtubeUrl = "https://youtu.be/VF-FGf_ZZiI?list=RDVF-FGf_ZZiI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Cinnamon Girl",
                artist = "Lana Del Rey",
                duration = 301, // 300683 ms
                youtubeUrl = "https://youtu.be/DCYmJDO2_IE?list=RDDCYmJDO2_IE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "House Of Balloons / Glass Table Girls",
                artist = "The Weeknd",
                duration = 407, // 407315 ms
                youtubeUrl = "https://youtu.be/8ex38L8xtNI?list=RD8ex38L8xtNI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Art Deco",
                artist = "Lana Del Rey",
                duration = 295, // 295066 ms
                youtubeUrl = "https://www.youtube.com/watch?v=QbLGjeR9bvI&list=RDQbLGjeR9bvI&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "favorite crime",
                artist = "Olivia Rodrigo",
                duration = 153, // 152666 ms
                youtubeUrl = "https://youtu.be/AyX_LL9nWSE?list=RDAyX_LL9nWSE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Cigarettes out the Window",
                artist = "TV Girl",
                duration = 199, // 198960 ms
                youtubeUrl = "https://youtu.be/_eACTXi1DTc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Kids",
                artist = "Current Joys",
                duration = 269, // 269142 ms
                youtubeUrl = "https://youtu.be/NWE4ddnlNKA?list=RDNWE4ddnlNKA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Nothing's New",
                artist = "Rio Romeo",
                duration = 211, // 211000 ms
                youtubeUrl = "https://youtu.be/5e4INH1yr9c?list=RD5e4INH1yr9c",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Sign of the Times",
                artist = "Harry Styles",
                duration = 341, // 340706 ms
                youtubeUrl = "https://youtu.be/qN4ooNx77u0?list=RDqN4ooNx77u0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Young And Beautiful",
                artist = "Lana Del Rey",
                duration = 236, // 236053 ms
                youtubeUrl = "https://youtu.be/o_1aF54DO60?list=RDo_1aF54DO60",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Can’t Catch Me Now - from The Hunger Games: The Ballad of Songbirds & Snakes",
                artist = "Olivia Rodrigo",
                duration = 205, // 205483 ms
                youtubeUrl = "https://youtu.be/GlM6lcFbLSg?list=RDGlM6lcFbLSg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Friends",
                artist = "Chase Atlantic",
                duration = 230, // 230010 ms
                youtubeUrl = "https://youtu.be/nT8O_mP2x6Y?list=RDnT8O_mP2x6Y",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "i like the way you kiss me",
                artist = "Artemas",
                duration = 143, // 142514 ms
                youtubeUrl = "https://youtu.be/evJ6gX1lp2o?list=RDevJ6gX1lp2o",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "NDA",
                artist = "Billie Eilish",
                duration = 196, // 195776 ms
                youtubeUrl = "https://youtu.be/OORBa32WFcM?list=RDOORBa32WFcM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "we can't be friends (wait for your love)",
                artist = "Ariana Grande",
                duration = 229, // 228639 ms
                youtubeUrl = "https://youtu.be/UdRAXWNvjzE?list=RDUdRAXWNvjzE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Następna stacja",
                artist = "Taco Hemingway",
                duration = 252, // 251826 ms
                youtubeUrl = "https://youtu.be/TZgBIbqtDnQ?list=RDTZgBIbqtDnQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Starboy",
                artist = "The Weeknd,Daft Punk",
                duration = 230, // 230453 ms
                youtubeUrl = "https://www.youtube.com/watch?v=34Na4j8AVgA&list=RD34Na4j8AVgA&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Strangers",
                artist = "Kenya Grace",
                duration = 173, // 172964 ms
                youtubeUrl = "https://youtu.be/RnyPNdBWwVw?list=RDRnyPNdBWwVw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Jestem Bogiem",
                artist = "Paktofonika",
                duration = 202, // 202306 ms
                youtubeUrl = "https://youtu.be/u3HeJFr01T0?list=RDu3HeJFr01T0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Pocałunki (M. Pawlikowska- Jasnorzewska)",
                artist = "sanah",
                duration = 190, // 190105 ms
                youtubeUrl = "https://youtu.be/avrpaTpS5Kc?list=RDavrpaTpS5Kc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Daddy Issues",
                artist = "The Neighbourhood",
                duration = 260, // 260173 ms
                youtubeUrl = "https://youtu.be/jKIK__j1Nno?list=RDjKIK__j1Nno",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Runaway",
                artist = "Kanye West,Pusha T",
                duration = 548, // 547733 ms
                youtubeUrl = "https://youtu.be/EMnQwBTJnMM?list=RDEMnQwBTJnMM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Where Is My Mind?",
                artist = "Pixies",
                duration = 229, // 229226 ms
                youtubeUrl = "https://youtu.be/OJ62RzJkYUo?list=RDOJ62RzJkYUo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "I Bet on Losing Dogs",
                artist = "Mitski",
                duration = 170, // 170240 ms
                youtubeUrl = "https://youtu.be/XfMBdq5iFnw?list=RDXfMBdq5iFnw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "traitor",
                artist = "Olivia Rodrigo",
                duration = 229, // 229226 ms
                youtubeUrl = "https://youtu.be/CRrf3h9vhp8?list=RDCRrf3h9vhp8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Daylight",
                artist = "Taylor Swift",
                duration = 293, // 293453 ms
                youtubeUrl = "https://youtu.be/u9raS7-NisU?list=RDu9raS7-NisU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "The Hills",
                artist = "The Weeknd",
                duration = 242, // 242253 ms
                youtubeUrl = "https://youtu.be/yzTuBuRdAyA?list=RDyzTuBuRdAyA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Call Out My Name",
                artist = "The Weeknd",
                duration = 228, // 228373 ms
                youtubeUrl = "https://youtu.be/M4ZoCHID9GI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Earned It (Fifty Shades Of Grey)",
                artist = "The Weeknd",
                duration = 278, // 277680 ms
                youtubeUrl = "https://youtu.be/waU75jdUnYw?list=RDwaU75jdUnYw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "After Hours",
                artist = "The Weeknd",
                duration = 361, // 361026 ms
                youtubeUrl = "https://youtu.be/ygTZZpVkmKg?list=RDygTZZpVkmKg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Adi Nowak x Taco Hemingway - Jesteś Wyjątkowa",
                artist = "Taco Hemingway, Adi Nowak",
                duration = 164, // 163631 ms
                youtubeUrl = "https://youtu.be/WrktlD9RcR0?list=RDWrktlD9RcR0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Little Lion Man",
                artist = "Mumford & Sons",
                duration = 245, // 245173 ms
                youtubeUrl = "https://youtu.be/X7bHe--mp1g?list=RDX7bHe--mp1g",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Writing's On The Wall - From Spectre Soundtrack",
                artist = "Sam Smith",
                duration = 279, // 278987 ms
                youtubeUrl = "https://www.youtube.com/watch?v=8jzDnsjYv9A&list=RD8jzDnsjYv9A&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Afraid",
                artist = "The Neighbourhood",
                duration = 251, // 251186 ms
                youtubeUrl = "https://youtu.be/O83tqQpa9xk?list=RDO83tqQpa9xk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Kill Bill",
                artist = "SZA",
                duration = 154, // 153946 ms
                youtubeUrl = "https://youtu.be/SQnc1QibapQ?list=RDSQnc1QibapQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Stan",
                artist = "Eminem,Dido",
                duration = 404, // 404106 ms
                youtubeUrl = "https://youtu.be/gOMhN-hfMtY?list=RDgOMhN-hfMtY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Runaway Baby",
                artist = "Bruno Mars",
                duration = 148, // 148448 ms
                youtubeUrl = "https://youtu.be/HIgvP7B3Hg8?list=RDHIgvP7B3Hg8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Mind Over Matter (Reprise)",
                artist = "Young the Giant",
                duration = 231, // 230866 ms
                youtubeUrl = "https://youtu.be/buvWBOZTfdc?list=RDbuvWBOZTfdc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "everything i wanted",
                artist = "Billie Eilish",
                duration = 245, // 245425 ms
                youtubeUrl = "https://youtu.be/EgBJmlPo8Xw?list=RDEgBJmlPo8Xw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "7 Years",
                artist = "Lukas Graham",
                duration = 237, // 237300 ms
                youtubeUrl = "https://youtu.be/LHCob76kigA?list=RDLHCob76kigA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Riptide",
                artist = "Vance Joy",
                duration = 204, // 204280 ms
                youtubeUrl = "https://youtu.be/uJ_1HMAGb4k?list=RDuJ_1HMAGb4k",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "death bed (coffee for your head)",
                artist = "Powfu,beabadoobee",
                duration = 173, // 173333 ms
                youtubeUrl = "https://youtu.be/jJPMnTXl63E?list=RDjJPMnTXl63E",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Notion",
                artist = "The Rare Occasions",
                duration = 195, // 195120 ms
                youtubeUrl = "https://youtu.be/Co7t6NxsW-4?list=RDCo7t6NxsW-4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "You and I",
                artist = "d4vd",
                duration = 153, // 152551 ms
                youtubeUrl = "https://youtu.be/Qe9BEZmSPDA?list=RDQe9BEZmSPDA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Shut up My Moms Calling (Sped up)",
                artist = "Hotel Ugly",
                duration = 136, // 135529 ms
                youtubeUrl = "https://youtu.be/J4ANQfaf_gc?list=RDJ4ANQfaf_gc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Do Me a Favour",
                artist = "Arctic Monkeys",
                duration = 209, // 209253 ms
                youtubeUrl = "https://youtu.be/MaFEHf34fCQ?list=RDMaFEHf34fCQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Did you know that there's a tunnel under Ocean Blvd",
                artist = "Lana Del Rey",
                duration = 285, // 285050 ms
                youtubeUrl = "https://youtu.be/IuY54A3bOmg?list=RDIuY54A3bOmg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Swimming Pools (Drank)",
                artist = "Kendrick Lamar",
                duration = 248, // 247800 ms
                youtubeUrl = "https://www.youtube.com/watch?v=B5YNiCfWC3A&list=RDB5YNiCfWC3A&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Going Through Changes",
                artist = "Eminem",
                duration = 299, // 298893 ms
                youtubeUrl = "https://youtu.be/T6T_94qjp5g",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Work Out",
                artist = "J. Cole",
                duration = 235, // 235320 ms
                youtubeUrl = "https://youtu.be/W5hSdGt2M8w?list=RDW5hSdGt2M8w",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Liquor Store Blues (feat. Damian Marley)",
                artist = "Bruno Mars,Damian Marley",
                duration = 229, // 229383 ms
                youtubeUrl = "https://youtu.be/1iBm60uJXvs?list=RD1iBm60uJXvs",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Mask Off",
                artist = "Future",
                duration = 205, // 204600 ms
                youtubeUrl = "https://youtu.be/fjUGC8g4GOE?list=RDfjUGC8g4GOE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Black Friday",
                artist = "Tom Odell",
                duration = 222, // 221510 ms
                youtubeUrl = "https://youtu.be/KMIKzWyZPSA?list=RDKMIKzWyZPSA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Our Last Summer",
                artist = "ABBA",
                duration = 260, // 259733 ms
                youtubeUrl = "https://youtu.be/xm3s1sANRDE?list=RDxm3s1sANRDE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Slipping Through My Fingers",
                artist = "ABBA",
                duration = 234, // 233720 ms
                youtubeUrl = "https://youtu.be/hRr7qRb-7k4?list=RDhRr7qRb-7k4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "The 30th",
                artist = "Billie Eilish",
                duration = 216, // 216448 ms
                youtubeUrl = "https://youtu.be/KyJEF0AZ2NU?list=RDKyJEF0AZ2NU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Angeleyes",
                artist = "ABBA",
                duration = 261, // 260893 ms
                youtubeUrl = "https://youtu.be/2LfmJmtm4Xs?list=RD2LfmJmtm4Xs",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Dancing Queen",
                artist = "ABBA",
                duration = 230, // 230400 ms
                youtubeUrl = "https://youtu.be/xFrGuyw1V8s?list=RDxFrGuyw1V8s",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Just the Way You Are",
                artist = "Bruno Mars",
                duration = 221, // 220734 ms
                youtubeUrl = "https://youtu.be/LjhCEhWiKXk?list=RDLjhCEhWiKXk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "golden hour",
                artist = "JVKE",
                duration = 209, // 209259 ms
                youtubeUrl = "https://youtu.be/PEM0Vs8jf1w?list=RDPEM0Vs8jf1w",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Where'd All the Time Go?",
                artist = "Dr. Dog",
                duration = 235, // 234800 ms
                youtubeUrl = "https://youtu.be/z5bzxPVJhbo?list=RDz5bzxPVJhbo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Daylight",
                artist = "David Kushner",
                duration = 213, // 212953 ms
                youtubeUrl = "https://youtu.be/MoN9ql6Yymw?list=RDMoN9ql6Yymw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Heartless",
                artist = "The Weeknd",
                duration = 198, // 198266 ms
                youtubeUrl = "https://youtu.be/1DpH-icPpl0?list=RD1DpH-icPpl0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "nuts",
                artist = "Lil Peep,rainy bear",
                duration = 85, // 85360 ms
                youtubeUrl = "https://youtu.be/osPq9Yb8xm8?list=RDosPq9Yb8xm8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "WORTHLESS",
                artist = "d4vd",
                duration = 163, // 163049 ms
                youtubeUrl = "https://youtu.be/ASubGNey2-4?list=RDASubGNey2-4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Sweater Weather",
                artist = "The Neighbourhood",
                duration = 240, // 240400 ms
                youtubeUrl = "https://youtu.be/GCdwKhTtNNw?list=RDGCdwKhTtNNw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Nervous",
                artist = "The Neighbourhood",
                duration = 245, // 245000 ms
                youtubeUrl = "https://youtu.be/XTDH7gSqwiQ?list=RDXTDH7gSqwiQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Twin Size Mattress",
                artist = "The Front Bottoms",
                duration = 265, // 264676 ms
                youtubeUrl = "https://youtu.be/cWJUk65EnQM?list=RDcWJUk65EnQM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Heartless",
                artist = "Kanye West",
                duration = 211, // 211000 ms
                youtubeUrl = "https://youtu.be/p42ZPTgmrnY?list=RDp42ZPTgmrnY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Lift Me Up - From Black Panther: Wakanda Forever - Music From and Inspired By",
                artist = "Rihanna",
                duration = 197, // 196520 ms
                youtubeUrl = "https://youtu.be/Mx_OexsUI2M?list=RDMx_OexsUI2M",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Heartbeat",
                artist = "Childish Gambino",
                duration = 270, // 269840 ms
                youtubeUrl = "https://youtu.be/dFVxGRekRSg?list=RDdFVxGRekRSg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Lovers Rock",
                artist = "TV Girl",
                duration = 214, // 213920 ms
                youtubeUrl = "https://youtu.be/j_sG_Juncn8?list=RDj_sG_Juncn8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "This Is The Life",
                artist = "Amy Macdonald",
                duration = 184, // 184413 ms
                youtubeUrl = "https://youtu.be/iRYvuS9OxdA?list=RDiRYvuS9OxdA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Danza Kuduro",
                artist = "Don Omar,Lucenzo",
                duration = 198, // 198386 ms
                youtubeUrl = "https://youtu.be/oyFobb9_dlg?list=RDoyFobb9_dlg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Look After You",
                artist = "The Fray",
                duration = 267, // 266773 ms
                youtubeUrl = "https://youtu.be/4lqYdS-Ell8?list=RD4lqYdS-Ell8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "War Of Hearts",
                artist = "Ruelle",
                duration = 226, // 226216 ms
                youtubeUrl = "https://youtu.be/GX7f1Btk1yM?list=RDGX7f1Btk1yM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Tumblr Girls (feat. Christoph Andersson)",
                artist = "G-Eazy,Christoph Andersson",
                duration = 256, // 255680 ms
                youtubeUrl = "https://youtu.be/fu7NR1qe_Mk?list=RDfu7NR1qe_Mk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "It's not fair",
                artist = "Kenya Grace",
                duration = 162, // 162253 ms
                youtubeUrl = "https://www.youtube.com/watch?v=1JU0w5JOBrI&list=RD1JU0w5JOBrI&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "We Are The People",
                artist = "Empire Of The Sun",
                duration = 267, // 267373 ms
                youtubeUrl = "https://youtu.be/hN5X4kGhAtU?list=RDhN5X4kGhAtU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Young Dumb & Broke",
                artist = "Khalid",
                duration = 203, // 202546 ms
                youtubeUrl = "https://youtu.be/IPfJnp1guPc?list=RDIPfJnp1guPc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Arabella",
                artist = "Arctic Monkeys",
                duration = 207, // 207356 ms
                youtubeUrl = "https://youtu.be/Jn6-TItCazo?list=RDJn6-TItCazo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Let Her Go",
                artist = "Passenger",
                duration = 253, // 252866 ms
                youtubeUrl = "https://youtu.be/RBumgq5yVrA?list=RDRBumgq5yVrA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Tag, You're It",
                artist = "Melanie Martinez",
                duration = 189, // 189026 ms
                youtubeUrl = "https://youtu.be/WhQxRa13CX0?list=RDWhQxRa13CX0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "No Role Modelz",
                artist = "J. Cole",
                duration = 293, // 292799 ms
                youtubeUrl = "https://youtu.be/8HBcV0MtAQg?list=RD8HBcV0MtAQg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Creepin' (with The Weeknd & 21 Savage)",
                artist = "Metro Boomin,The Weeknd,21 Savage",
                duration = 222, // 221520 ms
                youtubeUrl = "https://youtu.be/61ymOWwOwuk?list=RD61ymOWwOwuk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Dealer",
                artist = "Lana Del Rey",
                duration = 274, // 274293 ms
                youtubeUrl = "https://youtu.be/Vn86aScbzKk?list=RDVn86aScbzKk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Woo",
                artist = "Rihanna",
                duration = 236, // 235586 ms
                youtubeUrl = "https://youtu.be/i0S40arGfCQ?list=RDi0S40arGfCQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Follow You",
                artist = "Imagine Dragons",
                duration = 176, // 175643 ms
                youtubeUrl = "https://youtu.be/PP2fELH8uTQ?list=RDPP2fELH8uTQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Your Woman",
                artist = "White Town",
                duration = 260, // 259893 ms
                youtubeUrl = "https://youtu.be/3NoIusrv9OA?list=RD3NoIusrv9OA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Mix Sałat",
                artist = "Taco Hemingway,Daria Zawiałow,Zeppy Zep",
                duration = 173, // 172678 ms
                youtubeUrl = "https://youtu.be/s3UQBZLeqbk?list=RDs3UQBZLeqbk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Gelato",
                artist = "Taco Hemingway",
                duration = 196, // 195526 ms
                youtubeUrl = "https://youtu.be/k9HbCQiuJfk?list=RDk9HbCQiuJfk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "ZITTI E BUONI",
                artist = "Måneskin",
                duration = 195, // 194786 ms
                youtubeUrl = "https://youtu.be/QN1odfjtMoo?list=RDQN1odfjtMoo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Beggin'",
                artist = "Måneskin",
                duration = 212, // 211560 ms
                youtubeUrl = "https://youtu.be/W2MpGCL8-9o?list=RDW2MpGCL8-9o",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "L'altra dimensione",
                artist = "Måneskin",
                duration = 127, // 126706 ms
                youtubeUrl = "https://youtu.be/_NolWRUf1P8?list=RD_NolWRUf1P8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "THE LONELIEST",
                artist = "Måneskin",
                duration = 247, // 247067 ms
                youtubeUrl = "https://youtu.be/odWKEfp2QMY?list=RDodWKEfp2QMY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "CORALINE",
                artist = "Måneskin",
                duration = 300, // 300413 ms
                youtubeUrl = "https://youtu.be/CmMO42bOTZ4?list=RDCmMO42bOTZ4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Lose Yourself",
                artist = "Eminem",
                duration = 322, // 322226 ms
                youtubeUrl = "https://youtu.be/xFYQQPAOz7Y?list=RDxFYQQPAOz7Y",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "BLUE",
                artist = "Billie Eilish",
                duration = 343, // 343120 ms
                youtubeUrl = "https://youtu.be/_IjWFq1c5M4?list=RD_IjWFq1c5M4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "BITTERSUITE",
                artist = "Billie Eilish",
                duration = 298, // 298440 ms
                youtubeUrl = "https://youtu.be/LmVw3u3SxoA?list=RDLmVw3u3SxoA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "THE GREATEST",
                artist = "Billie Eilish",
                duration = 294, // 293840 ms
                youtubeUrl = "https://youtu.be/WkdQhfDRBKs?list=RDWkdQhfDRBKs",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "CHIHIRO",
                artist = "Billie Eilish",
                duration = 303, // 303440 ms
                youtubeUrl = "https://youtu.be/e_AZJzYe7CU?list=RDe_AZJzYe7CU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "IF NOT FOR YOU",
                artist = "Måneskin",
                duration = 195, // 194874 ms
                youtubeUrl = "https://youtu.be/_ZyeZqrKXpU?list=RD_ZyeZqrKXpU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Woda Księżycowa",
                artist = "Kubi Producent,bambi,Fukaj,stickxr",
                duration = 191, // 191304 ms
                youtubeUrl = "https://youtu.be/syLU0zUREf4?list=RDsyLU0zUREf4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Lean Wit Me",
                artist = "Juice WRLD",
                duration = 176, // 175755 ms
                youtubeUrl = "https://youtu.be/WsrVxz4pjGs?list=RDWsrVxz4pjGs",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Cry Baby",
                artist = "The Neighbourhood",
                duration = 235, // 235160 ms
                youtubeUrl = "https://youtu.be/r4LGe12tNR8?list=RDr4LGe12tNR8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "There She Goes - Single Version",
                artist = "The La's",
                duration = 149, // 148666 ms
                youtubeUrl = "https://youtu.be/dJ8T5e_FSP8?list=RDdJ8T5e_FSP8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Scary Love",
                artist = "The Neighbourhood",
                duration = 223, // 222666 ms
                youtubeUrl = "https://www.youtube.com/watch?v=zTIoErJmsp4&list=RDzTIoErJmsp4&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Marsz, Marsz",
                artist = "Taco Hemingway",
                duration = 230, // 230427 ms
                youtubeUrl = "https://youtu.be/EP5tnkhXgQU?list=RDEP5tnkhXgQU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "if u think i'm pretty",
                artist = "Artemas",
                duration = 128, // 128205 ms
                youtubeUrl = "https://youtu.be/x3tXq9xx7oI?list=RDx3tXq9xx7oI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Smack That",
                artist = "Akon,Eminem",
                duration = 212, // 212360 ms
                youtubeUrl = "https://youtu.be/RsrR0l76i_4?list=RDRsrR0l76i_4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "so american",
                artist = "Olivia Rodrigo",
                duration = 170, // 169717 ms
                youtubeUrl = "https://youtu.be/W-PGNyhmSKA?list=RDW-PGNyhmSKA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Star Shopping",
                artist = "Lil Peep",
                duration = 142, // 142000 ms
                youtubeUrl = "https://youtu.be/m8QQR-wQA0I?list=RDm8QQR-wQA0I",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "900729",
                artist = "Taco Hemingway",
                duration = 304, // 303988 ms
                youtubeUrl = "https://www.youtube.com/watch?v=3QCId_kbhaI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Szlugi I Kalafiory",
                artist = "Taco Hemingway",
                duration = 186, // 186045 ms
                youtubeUrl = "https://youtu.be/YBaRFsubJNo?list=RDYBaRFsubJNo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "One Of The Girls (with JENNIE, Lily Rose Depp)",
                artist = "The Weeknd,JENNIE,Lily-Rose Depp",
                duration = 245, // 244684 ms
                youtubeUrl = "https://www.youtube.com/watch?v=Mx92lTYxrJQ&list=RDMx92lTYxrJQ&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Pray For Me",
                artist = "The Weeknd,Kendrick Lamar",
                duration = 211, // 211420 ms
                youtubeUrl = "https://youtu.be/XR7Ev14vUh8?list=RDXR7Ev14vUh8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "How to disappear",
                artist = "Lana Del Rey",
                duration = 228, // 228071 ms
                youtubeUrl = "https://youtu.be/WU_EGJOQ69o?list=RDWU_EGJOQ69o",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Diet Mountain Dew",
                artist = "Lana Del Rey",
                duration = 223, // 222920 ms
                youtubeUrl = "https://youtu.be/qtBxUoUkG8s?list=RDqtBxUoUkG8s",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Don't Copy My Flow",
                artist = "фрози,Mwizz,George Kipa",
                duration = 172, // 172382 ms
                youtubeUrl = "https://www.youtube.com/watch?v=cl_mQplBecI&list=RDcl_mQplBecI&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Sweet",
                artist = "Lana Del Rey",
                duration = 215, // 215295 ms
                youtubeUrl = "https://youtu.be/-Fg-DcLJY4s?list=RD-Fg-DcLJY4s",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Can't Feel My Face",
                artist = "The Weeknd",
                duration = 214, // 213520 ms
                youtubeUrl = "https://youtu.be/KEI4qSrkPAs?list=RDKEI4qSrkPAs",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Stargazing",
                artist = "Myles Smith",
                duration = 173, // 172533 ms
                youtubeUrl = "https://youtu.be/tKml80alH3Y?list=RDtKml80alH3Y",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "I Will Always Think of You",
                artist = "Jane Krakowski,Colman Domingo",
                duration = 82, // 82146 ms
                youtubeUrl = "https://youtu.be/96lwAGESRAY?list=RD96lwAGESRAY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Square Dance",
                artist = "Eminem",
                duration = 324, // 323720 ms
                youtubeUrl = "https://youtu.be/6JX8j3vwrIA?list=RD6JX8j3vwrIA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Dile",
                artist = "Don Omar",
                duration = 205, // 204600 ms
                youtubeUrl = "https://youtu.be/bc1RmZ4uQoI?list=RDbc1RmZ4uQoI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Ride",
                artist = "Twenty One Pilots",
                duration = 215, // 214506 ms
                youtubeUrl = "https://youtu.be/Pw-0pbY9JeU?list=RDPw-0pbY9JeU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Całe Lata",
                artist = "Taco Hemingway,Dawid Podsiadło,@atutowy",
                duration = 237, // 237217 ms
                youtubeUrl = "https://youtu.be/nthFATKbsSg?list=RDnthFATKbsSg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Nostalgia",
                artist = "Taco Hemingway",
                duration = 229, // 229186 ms
                youtubeUrl = "https://youtu.be/pVrbA5y96sk?list=RDpVrbA5y96sk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Trójkąt",
                artist = "Taco Hemingway",
                duration = 327, // 326923 ms
                youtubeUrl = "https://youtu.be/5ZMyq7F9ADU?list=RD5ZMyq7F9ADU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Work",
                artist = "Rihanna,Drake",
                duration = 219, // 219320 ms
                youtubeUrl = "https://youtu.be/xizkel_Jrdo?list=RDxizkel_Jrdo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "White Mustang",
                artist = "Lana Del Rey",
                duration = 165, // 164710 ms
                youtubeUrl = "https://youtu.be/F4ELqraXx-U?list=RDF4ELqraXx-U",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Love Again",
                artist = "Dua Lipa",
                duration = 258, // 258004 ms
                youtubeUrl = "https://youtu.be/BC19kwABFwc?list=RDBC19kwABFwc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "My Way",
                artist = "Calvin Harris",
                duration = 219, // 219159 ms
                youtubeUrl = "https://youtu.be/b4Bj7Zb-YD4?list=RDb4Bj7Zb-YD4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Harleys In Hawaii",
                artist = "Katy Perry",
                duration = 186, // 185815 ms
                youtubeUrl = "https://www.youtube.com/watch?v=sQEgklEwhSo&list=RDsQEgklEwhSo&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "I LUV IT (feat. Playboi Carti)",
                artist = "Camila Cabello,Playboi Carti",
                duration = 175, // 174880 ms
                youtubeUrl = "https://youtu.be/rDG-6Ue_Qbw?list=RDrDG-6Ue_Qbw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "SKINNY",
                artist = "Billie Eilish",
                duration = 220, // 219733 ms
                youtubeUrl = "https://youtu.be/g6YSdMnCOCU?list=RDg6YSdMnCOCU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Hot",
                artist = "Cigarettes After Sex",
                duration = 237, // 237480 ms
                youtubeUrl = "https://youtu.be/i39g22RiTqA?list=RDi39g22RiTqA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "SOS",
                artist = "ABBA",
                duration = 202, // 202386 ms
                youtubeUrl = "https://youtu.be/cvChjHcABPA?list=RDcvChjHcABPA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "8 kobiet",
                artist = "TACONAFIDE,Quebonafide,Taco Hemingway",
                duration = 199, // 199093 ms
                youtubeUrl = "https://youtu.be/eAQh6SDsdnA?list=RDeAQh6SDsdnA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "BIRDS OF A FEATHER",
                artist = "Billie Eilish",
                duration = 210, // 210373 ms
                youtubeUrl = "https://youtu.be/geKxhmZL8ao?list=RDgeKxhmZL8ao",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "WILDFLOWER",
                artist = "Billie Eilish",
                duration = 261, // 261466 ms
                youtubeUrl = "https://youtu.be/YL2xYo0GoUI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "because i liked a boy",
                artist = "Sabrina Carpenter",
                duration = 196, // 196458 ms
                youtubeUrl = "https://youtu.be/1YUBbF24H44?list=RD1YUBbF24H44",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "stranger",
                artist = "Olivia Rodrigo",
                duration = 193, // 192771 ms
                youtubeUrl = "https://youtu.be/no0S6UzeRlA",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "scared of my guitar",
                artist = "Olivia Rodrigo",
                duration = 264, // 263928 ms
                youtubeUrl = "https://youtu.be/02Ai_4eG6ug?list=RD02Ai_4eG6ug",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Alibi (with Pabllo Vittar & Yseult)",
                artist = "Sevdaliza,Pabllo Vittar,Yseult",
                duration = 162, // 161655 ms
                youtubeUrl = "https://youtu.be/qVqFuokjRMc?list=RDqVqFuokjRMc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "NIGHTS LIKE THIS",
                artist = "The Kid LAROI",
                duration = 87, // 86983 ms
                youtubeUrl = "https://youtu.be/L3i4WPqOlb0?list=RDL3i4WPqOlb0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Something About You",
                artist = "Eyedress,Dent May",
                duration = 153, // 153312 ms
                youtubeUrl = "https://youtu.be/j9yEL3B5Cvk?list=RDj9yEL3B5Cvk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Popular (with Playboi Carti & Madonna) - From The Idol Vol. 1 (Music from the HBO Original Series)",
                artist = "The Weeknd,Playboi Carti,Madonna",
                duration = 215, // 215466 ms
                youtubeUrl = "https://youtu.be/vt0i6nuqNEo?list=RDvt0i6nuqNEo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "intro (end of the world)",
                artist = "Ariana Grande",
                duration = 92, // 92400 ms
                youtubeUrl = "https://youtu.be/p7jATa6Soag",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Voulez-Vous",
                artist = "ABBA",
                duration = 309, // 309173 ms
                youtubeUrl = "https://youtu.be/XQ45gynAUPg?list=RDXQ45gynAUPg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Looking Out for You",
                artist = "Joy Again",
                duration = 179, // 179499 ms
                youtubeUrl = "https://youtu.be/ZVQDHFgfssM?list=RDZVQDHFgfssM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Pakiet Platinium",
                artist = "Taco Hemingway",
                duration = 196, // 195542 ms
                youtubeUrl = "https://youtu.be/zNMQrSDynfs?list=RDzNMQrSDynfs",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "1-800-OŚWIECENIE",
                artist = "Taco Hemingway",
                duration = 171, // 170839 ms
                youtubeUrl = "https://youtu.be/uXEA9GSZfGg?list=RDuXEA9GSZfGg",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "The Rose Song - From High School Musical: The Musical: The Series (Season 2)",
                artist = "Olivia Rodrigo,Disney",
                duration = 174, // 174380 ms
                youtubeUrl = "https://youtu.be/7yVpzzqA3q0?list=RD7yVpzzqA3q0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "It Will Rain",
                artist = "Bruno Mars",
                duration = 258, // 257720 ms
                youtubeUrl = "https://www.youtube.com/watch?v=W-w3WfgpcGg&list=RDW-w3WfgpcGg&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Loving Machine",
                artist = "TV Girl",
                duration = 227, // 227000 ms
                youtubeUrl = "https://youtu.be/5tpQaCAq6Qc?list=RD5tpQaCAq6Qc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "teen romance",
                artist = "Lil Peep,Lederrick",
                duration = 170, // 170133 ms
                youtubeUrl = "https://youtu.be/yeQ9iurJYrQ?list=RDyeQ9iurJYrQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "NEW MAGIC WAND",
                artist = "Tyler, The Creator",
                duration = 195, // 195320 ms
                youtubeUrl = "https://youtu.be/mdCyzJT59nw?list=RDmdCyzJT59nw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "All The Stars (with SZA)",
                artist = "Kendrick Lamar,SZA",
                duration = 232, // 232186 ms
                youtubeUrl = "https://youtu.be/JQbjS0_ZfJ0?list=RDJQbjS0_ZfJ0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Can't Help Falling in Love",
                artist = "Elvis Presley",
                duration = 182, // 182360 ms
                youtubeUrl = "https://youtu.be/vGJTaP6anOU?list=RDvGJTaP6anOU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "8 kobiet - Remix",
                artist = "TACONAFIDE,Quebonafide,Taco Hemingway,Bedoes 2115",
                duration = 271, // 270813 ms
                youtubeUrl = "https://youtu.be/7kHsddf-6es?list=RD7kHsddf-6es",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Lose Control",
                artist = "Teddy Swims",
                duration = 211, // 210688 ms
                youtubeUrl = "https://youtu.be/GF7hSFOJ874?list=RDGF7hSFOJ874",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Roi - Instrumental",
                artist = "Mckyyy",
                duration = 83, // 83333 ms
                youtubeUrl = "https://youtu.be/rUuvZyfbnAU?list=RDrUuvZyfbnAU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Again (feat. XXXTENTACION)",
                artist = "Noah Cyrus,XXXTENTACION",
                duration = 194, // 193961 ms
                youtubeUrl = "https://youtu.be/G_SjnHwxWMU",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "CZŁOWIEK Z DZIURĄ ZAMIAST KRTANI",
                artist = "Taco Hemingway",
                duration = 248, // 248400 ms
                youtubeUrl = "https://youtu.be/bv-Ojy6UZG4?list=RDbv-Ojy6UZG4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "KEHLANI",
                artist = "Jordan Adetunji",
                duration = 122, // 122360 ms
                youtubeUrl = "https://youtu.be/ULCb3krc5LI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Mirror",
                artist = "Lil Wayne,Bruno Mars",
                duration = 228, // 228093 ms
                youtubeUrl = "https://youtu.be/OZLUa8JUR18?list=RDOZLUa8JUR18",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Billionaire (feat. Bruno Mars)",
                artist = "Travie McCoy,Bruno Mars",
                duration = 211, // 211160 ms
                youtubeUrl = "https://youtu.be/t6Q6xNlakJQ?list=RDt6Q6xNlakJQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Treasure",
                artist = "Bruno Mars",
                duration = 179, // 178560 ms
                youtubeUrl = "https://youtu.be/nPvuNsRccVw?list=RDnPvuNsRccVw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Sure Thing",
                artist = "Miguel",
                duration = 195, // 195373 ms
                youtubeUrl = "https://youtu.be/q4GJVOMjCC4?list=RDq4GJVOMjCC4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Polskie Tango",
                artist = "Taco Hemingway,Lanek",
                duration = 183, // 183253 ms
                youtubeUrl = "https://youtu.be/i84L16VL6c8?list=RDi84L16VL6c8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Die With A Smile",
                artist = "Lady Gaga,Bruno Mars",
                duration = 252, // 251667 ms
                youtubeUrl = "https://www.youtube.com/watch?v=kPa7bsKwL-c&list=RDkPa7bsKwL-c&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Back to the Old House - 2011 Remaster",
                artist = "The Smiths",
                duration = 187, // 186626 ms
                youtubeUrl = "https://youtu.be/laXY5e5JaV0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "365",
                artist = "Charli xcx",
                duration = 204, // 203618 ms
                youtubeUrl = "https://youtu.be/Ol9CCM240Ag?list=RDOl9CCM240Ag",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Symphonia IX",
                artist = "Current Joys",
                duration = 181, // 181360 ms
                youtubeUrl = "https://youtu.be/b-vHb4Z7yOY?list=RDb-vHb4Z7yOY",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "There Is a Light That Never Goes Out - 2011 Remaster",
                artist = "The Smiths",
                duration = 245, // 244586 ms
                youtubeUrl = "https://youtu.be/3r-qDvD3F3c?list=RD3r-qDvD3F3c",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Cemetry Gates - 2011 Remaster",
                artist = "The Smiths",
                duration = 161, // 161040 ms
                youtubeUrl = "https://youtu.be/LVSaTtMU72Q?list=RDLVSaTtMU72Q",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Heaven Knows I'm Miserable Now - 2011 Remaster",
                artist = "The Smiths",
                duration = 216, // 215760 ms
                youtubeUrl = "https://youtu.be/TjPhzgxe3L0?list=RDTjPhzgxe3L0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Angel By The Wings",
                artist = "Sia",
                duration = 319, // 319200 ms
                youtubeUrl = "https://youtu.be/M_3yoOU3-eQ?list=RDM_3yoOU3-eQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "All of Me",
                artist = "John Legend",
                duration = 270, // 269560 ms
                youtubeUrl = "https://youtu.be/ngq5Aw0Q6rQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Bound 2",
                artist = "Kanye West",
                duration = 229, // 229146 ms
                youtubeUrl = "https://youtu.be/wVRF3SqLUi0?list=RDwVRF3SqLUi0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Małomiasteczkowy",
                artist = "Dawid Podsiadło",
                duration = 203, // 203253 ms
                youtubeUrl = "https://youtu.be/X2XWBcd5jn0?list=RDX2XWBcd5jn0",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Young, Wild & Free (feat. Bruno Mars)",
                artist = "Snoop Dogg,Wiz Khalifa,Bruno Mars",
                duration = 207, // 207333 ms
                youtubeUrl = "https://youtu.be/4A9UutuFLEw?list=RD4A9UutuFLEw",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "L’AMOUR DE MA VIE",
                artist = "Billie Eilish",
                duration = 334, // 333986 ms
                youtubeUrl = "https://youtu.be/am5FI9DkO80?list=RDam5FI9DkO80",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Love Me Harder",
                artist = "Ariana Grande,The Weeknd",
                duration = 236, // 236133 ms
                youtubeUrl = "https://youtu.be/rj0tLcutBZQ?list=RDrj0tLcutBZQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Rodeo (Remix)",
                artist = "Lah Pat,Flo Milli",
                duration = 246, // 245959 ms
                youtubeUrl = "https://www.youtube.com/watch?v=5qxzWNuoG18&list=RD5qxzWNuoG18&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "4 Morant (Better Luck Next Time)",
                artist = "Com Truise",
                duration = 171, // 171000 ms
                youtubeUrl = "https://youtu.be/5qNgbIGsrtM",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "On Top Of The World",
                artist = "Imagine Dragons",
                duration = 192, // 192280 ms
                youtubeUrl = "https://youtu.be/cxmMD5OvYRQ",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Warriors",
                artist = "Imagine Dragons",
                duration = 170, // 170066 ms
                youtubeUrl = "https://youtu.be/1uBPOu3si5w?list=RD1uBPOu3si5w",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Chłopaki nie płaczą",
                artist = "Bedoes 2115,Kubi Producent,Taco Hemingway",
                duration = 212, // 211800 ms
                youtubeUrl = "https://youtu.be/wakbJpklQtI?list=RDwakbJpklQtI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Vampire Empire",
                artist = "Big Thief",
                duration = 192, // 192127 ms
                youtubeUrl = "https://youtu.be/Ka1vNzmD6JE?list=RDKa1vNzmD6JE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Somebody Save Me",
                artist = "Eminem,Jelly Roll",
                duration = 230, // 230199 ms
                youtubeUrl = "https://youtu.be/Vwa0HenQMi4?list=RDVwa0HenQMi4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Sailor Song",
                artist = "Gigi Perez",
                duration = 212, // 211978 ms
                youtubeUrl = "https://youtu.be/m0NZ-aH0G1g",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Hey Lover",
                artist = "The Daughters Of Eve",
                duration = 144, // 144000 ms
                youtubeUrl = "https://youtu.be/KbHaIbDKQMc?list=RDKbHaIbDKQMc",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "New Flesh",
                artist = "Current Joys",
                duration = 168, // 167986 ms
                youtubeUrl = "https://youtu.be/RaNnztBVD7w?list=RDRaNnztBVD7w",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Wszystko Jedno",
                artist = "Taco Hemingway",
                duration = 310, // 310152 ms
                youtubeUrl = "https://youtu.be/U_1JvhG5NcE?list=RDU_1JvhG5NcE",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Or Nah (feat. The Weeknd, Wiz Khalifa & DJ Mustard) - Remix",
                artist = "Ty Dolla \$ign,The Weeknd,Wiz Khalifa,Mustard",
                duration = 243, // 242983 ms
                youtubeUrl = "https://youtu.be/ZYVQAsQ6QmI?list=RDZYVQAsQ6QmI",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Please, Please, Please, Let Me Get What I Want - 2011 Remaster",
                artist = "The Smiths",
                duration = 113, // 112706 ms
                youtubeUrl = "https://youtu.be/jFVBxtSQXYk?list=RDjFVBxtSQXYk",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Yeah! (feat. Lil Jon & Ludacris)",
                artist = "USHER,Lil Jon,Ludacris",
                duration = 250, // 250373 ms
                youtubeUrl = "https://youtu.be/GxBSyx85Kp8?list=RDGxBSyx85Kp8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "AK47",
                artist = "Young Multi,marceli",
                duration = 216, // 216296 ms
                youtubeUrl = "https://youtu.be/sL9sq_7-dF8?list=RDsL9sq_7-dF8",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Wake Up in the Sky",
                artist = "Gucci Mane,Bruno Mars,Kodak Black",
                duration = 203, // 203161 ms
                youtubeUrl = "https://youtu.be/U68MJz9DrI4?list=RDU68MJz9DrI4",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Mięso",
                artist = "Taco Hemingway,Bebun",
                duration = 258, // 257672 ms
                youtubeUrl = "https://youtu.be/fTqlOqXtNDo?list=RDfTqlOqXtNDo",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "We Are Young (feat. Janelle Monáe)",
                artist = "fun.,Janelle Monáe",
                duration = 251, // 250626 ms
                youtubeUrl = "https://youtu.be/Sv6dMFF_yts?list=RDSv6dMFF_yts",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Hometown Glory",
                artist = "Adele",
                duration = 271,
                youtubeUrl = "https://www.youtube.com/watch?v=BW9Fzwuf43c&list=RDBW9Fzwuf43c&start_radio=1",
                filePath = null,
                downloadStatus = null
            ),
            SongEntity(
                title = "Self Esteem (featuring NLE Choppa)",
                artist = "Lambo4oe, NLE Choppa",
                duration = 170,
                youtubeUrl = "https://youtu.be/hElerA-8kbU?list=RDhElerA-8kbU",
                filePath = null,
                downloadStatus = null
            )
        )
        dao.insertAll(sampleSongs)
    }
}