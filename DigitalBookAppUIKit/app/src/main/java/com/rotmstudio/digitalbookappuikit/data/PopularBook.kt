package com.rotmstudio.digitalbookappuikit.data

import com.rotmstudio.digitalbookappuikit.R

data class PopularBook(
    val id: Long,
    val title: String,
    val author: String,
    val description: String,
    val rating: Double,
    val numberOfPage: Int,
    val language: String,
    val image: Int
)

val popularBooks = listOf(
    PopularBook(
        id = 1L,
        title = "Enchantment",
        author = "Guy Kawasaki",
        description = "Enchantment, as defined by bestselling business guru Guy Kawasaki, is not about manipulating people. It transforms situations and relationships. It converts hostility into civility and civility into affinity. It changes skeptics and cynics into believers and the undecided into the loyal.",
        rating = 4.9,
        numberOfPage = 180,
        language = "ENG",
        image = R.drawable.enchantment_image
    ),
    PopularBook(
        id = 2L,
        title = "The World Of Lore",
        author = "Aaron Mahnke",
        description = "Sometimes you walk into a room, a building, even a town, and you feel it. Something seems off—an atmosphere that leaves you oddly unsettled, a sense of lingering darkness. Now Aaron Mahnke, the host of the popular podcast Lore, explores some of these dreadful places and the history that seems to haunt them.",
        rating = 4.5,
        numberOfPage = 330,
        language = "ENG",
        image = R.drawable.the_world_of_lore
    ),
    PopularBook(
        id = 3L,
        title = "Think and Grow Rich",
        author = "Napoleon Hill",
        description = "Think and Grow Rich adalah karya fundamental sang penulis buku sukses legendaris, Napoleon Hill. Buku yang pertama kali diterbitkan tahun 1937 ini sekarang tetap sama ampuhnya—dan akan mengubah kehidupan Anda selamanya.",
        rating = 4.2,
        numberOfPage = 406,
        language = "ID",
        image = R.drawable.think_and_grow_rich
    )
)