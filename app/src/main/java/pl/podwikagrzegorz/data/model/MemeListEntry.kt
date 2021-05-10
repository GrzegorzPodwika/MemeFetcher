package pl.podwikagrzegorz.data.model

import pl.podwikagrzegorz.data.remote.response.Meme

data class MemeListEntry(
    val id: String,
    val name: String,
    val url: String,
)

fun List<Meme>.toListEntries() : List<MemeListEntry> {
    return map {
        MemeListEntry(
            id = it.id,
            name = it.name,
            url = it.url
        )
    }
}