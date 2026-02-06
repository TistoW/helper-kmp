package com.tisto.helper.core.helper.utils.ext

const val dateFormat = "yyyy-MM-dd"

fun logs(message: String? = "message") {
    println(message.def())
}

fun logs(vararg str: String) {
    var message = ""
    for ((i, s) in str.withIndex()) {
        message += if (i == str.size - 1) s else "$s - "
    }
    println(message)
}

fun checkPrefix(mPhone: String): String {
    when (mPhone.substring(0, 4)) {
        "0831", "0832", "0833", "0835", "0836", "0837", "0838", "0839" -> {
            return "Axis"
        }

        "0817", "0818", "0819", "0859", "0877", "0878", "0879" -> {
            return "XL"
        }

        "0811", "0812", "0813", "0821", "0822", "0823", "0851", "0852", "0853", "0854" -> {
            return "Telkomsel"
        }

        "0814", "0815", "0816", "0855", "0856", "0857", "0858" -> {
            return "Indosat"
        }

        "0895", "0896", "0897", "0898", "0899" -> {
            return "Tri"
        }

        "0881", "0882", "0883", "0884", "0885", "0886", "0887", "0888", "0889" -> {
            return "Smartfren"
        }

        "0828" -> {
            return "Ceria"
        }

        else -> {
            return "non"
        }
    }
}

fun Int?.def(v: Int = 0): Int {
    return this ?: v
}

fun String?.def(v: String = ""): String {
    return this ?: v
}

fun Double?.def(v: Double = 0.0): Double {
    return this ?: v
}

fun Long?.def(v: Long = 0L): Long {
    return this ?: v
}

fun <T> List<T>.def(value: List<T> = listOf()): List<T> {
    return value
}


fun String?.defaultError(): String = if (this.isNullOrEmpty()) "Server Error" else this

fun String?.addZeroUpFront(digit: Int = 2): String {
    var zero = ""
    val length = this?.length.def(1)
    if (length < digit) {
        val additionalZero = digit - length
        for (i in 1..additionalZero) {
            zero += "0"
        }
    }
    return zero + this
}

fun <T> List<T>.insertAt(value: T, index: Int = 0): List<T> {
    return when {
        index <= 0 -> listOf(value) + this
        index >= size -> this + value
        else -> take(index) + value + drop(index)
    }
}

fun <T> T?.isNull(): Boolean {
    return this == null
}

fun <T> T?.isNotNull(): Boolean {
    return this != null
}