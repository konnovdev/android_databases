package dev.konnov.common.dataset.newsreports

data class NewsReport(val title: Title, val description: String)

@JvmInline
value class Title(val title: String)