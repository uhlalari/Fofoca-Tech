package com.example.fofocaoficial.model.db

import androidx.room.TypeConverter
import com.example.fofocaoficial.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source{
        return Source(name, name)
    }
}

//Configuramos e voltamos para o articledatabase