package com.tisto.helper.core.helper.utils.ext

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import helper.core.helper.generated.resources.Res
import helper.core.helper.generated.resources.detail
import helper.core.helper.generated.resources.tambah

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
