package dev.konnov.feature.datastore.data.serializer

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import dev.konnov.feature.datastore.NewsReportList
import java.io.InputStream
import java.io.OutputStream

object NewsReportListSerializer : Serializer<NewsReportList> {
    override val defaultValue: NewsReportList = NewsReportList.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): NewsReportList {
        try {
            return NewsReportList.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: NewsReportList,
        output: OutputStream
    ) = t.writeTo(output)
}

val Context.newsReportListDataStore: DataStore<NewsReportList> by dataStore(
    fileName = "newsreport.pb",
    serializer = NewsReportListSerializer
)
