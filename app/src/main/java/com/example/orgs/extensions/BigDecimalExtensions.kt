package com.example.orgs.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.formatBrazilianCurrency(): String {
    val brazilLocale = Locale.forLanguageTag("pt-br")
    val formatter: NumberFormat = NumberFormat.getCurrencyInstance(brazilLocale)
    return formatter.format(this)
}