package dev.konnov.feature.datastore.data.serializer

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import dev.konnov.feature.datastore.WeatherLogList
import java.io.InputStream
import java.io.OutputStream

object WeatherLogListSerializer : Serializer<WeatherLogList> {
    override val defaultValue: WeatherLogList = WeatherLogList.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): WeatherLogList {
        try {
            return WeatherLogList.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: WeatherLogList,
        output: OutputStream
    ) = t.writeTo(output)
}

val Context.weatherLogListDataStore: DataStore<WeatherLogList> by dataStore(
    fileName = "weatherlog.pb",
    serializer = WeatherLogListSerializer
)
