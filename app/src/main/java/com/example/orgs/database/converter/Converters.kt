package com.example.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun double(valor: Double?): BigDecimal? {
        return valor?.let { BigDecimal(valor.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun bigDecimaltoDouble(valor: BigDecimal?): Double? {
        return valor?.let { valor.toDouble() }
    }
}