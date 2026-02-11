package com.tisto.helper.core.helper.utils.ext

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import helper.core.helper.generated.resources.Res
import helper.core.helper.generated.resources.detail
import helper.core.helper.generated.resources.tambah
import kotlin.random.Random
import kotlin.time.Clock

fun String?.shorten(maxLength: Int = 10): String {
    val displaySearch = if (this?.length.def(0) > maxLength) {
        this?.take(10) + "..."
    } else {
        this.def()
    }
    return displaySearch
}

fun String.ellipsis(maxLength: Int = 10) = shorten(maxLength)

fun String?.startWithZeroPhone(): String? {
    if (this.isNullOrEmpty()) return this
    return if (this.startsWith("62")) {
        "0${this.substring(2)}"
    } else {
        this
    }
}

fun String.removeTrailingCommaZero(): String {
    return if (endsWith(",0") || endsWith(".0")) {
        dropLast(2)
    } else {
        this
    }
}

fun String?.ifZero(default: String = ""): String {
    return if (this == "0" || this == "0.0" || this == "0,0" || this == "null" || this == null) default
    else this.removeTrailingCommaZero()
}

fun Double?.ifZero(default: String = ""): String {
    return this.toString().ifZero(default)
}

fun Int?.ifZero(default: String = ""): String {
    return this.toString().ifZero(default)
}

inline fun <T> T.ifCondition(condition: (T) -> Boolean, ifTrue: () -> T): T {
    return if (this != null && condition(this)) ifTrue() else this
}


@Composable
fun String.title(isDataAvailable: Boolean): String {
    val title = "${
        if (isDataAvailable) "${stringResource(Res.string.detail)} "
        else "${stringResource(Res.string.tambah)} "
    }$this"
    return title
}

private val firstNames = listOf(
    "Agus", "Budi", "Citra", "Dedi", "Eko", "Fitri", "Gita", "Hari", "Indra", "Joko",
    "Kiki", "Lina", "Maya", "Nina", "Oka", "Putu", "Rian", "Susi", "Taufik", "Umar",
    "Vina", "Wahyu", "Yusuf", "Zahra", "Andi", "Bella", "Candra", "Dewi", "Edi", "Farhan",
    "Gilang", "Hendra", "Ika", "Joni", "Kurniawan", "Linda", "Meli", "Nadia", "Oni", "Pandu",
    "Rina", "Siti", "Toni", "Usman", "Viona", "Wira", "Yuli", "Zaki", "Adi", "Benny",
    "Cici", "Dian", "Endang", "Feri", "Gusman", "Hani", "Irfan", "Jaka", "Kartika", "Lutfi",
    "Mira", "Nino", "Olga", "Via", "Rendi", "Sandy", "Tirta", "Uli", "Vega", "Wulan",
    "Yogi", "Zulfi", "Asep", "Beni", "Dika", "Eva", "Faisal", "Guntur", "Heryanto", "Iwan",
    "Krisna", "Lala", "Oky", "Rani", "Sigit", "Tina", "Utami", "Vino", "Yulia", "Zainal"
)

private val lastNames = listOf(
    "Pratama", "Santoso", "Dewi", "Kusuma", "Saputra", "Ayu", "Sari", "Susanto", "Setiawan", "Riyadi",
    "Ananda", "Permata", "Puspita", "Wirawan", "Syahputra", "Susanti", "Hidayat", "Bakri", "Melinda", "Nugroho",
    "Mansur", "Putri", "Siregar", "Wijaya", "Lestari", "Supriyadi", "Ridwan", "Mahendra", "Gunawan", "Ramadhan",
    "Utami", "Maharani", "Amelia", "Andika", "Purnama", "Hakim", "Aditya", "Hartono", "Rahman", "Anggraini",
    "Akbar", "Widodo", "Septiani", "Hartati", "Abidin", "Fadhilah"
)

fun randomInt(from: Int, to: Int): Int {
    require(from < to) { "from must be < to" }
    return Random.nextInt(from, to) // to exclusive
}

fun generateRandomName(withNumber: Boolean = false): String {
    val first = firstNames.random()
    val last = lastNames.random()
    val suffix = if (withNumber) randomInt(10, 100).toString() else "" // 10..99
    return "$first $last$suffix"
}