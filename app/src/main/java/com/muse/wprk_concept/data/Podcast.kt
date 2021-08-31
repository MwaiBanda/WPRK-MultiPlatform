package com.muse.wprk_concept.data

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Podcast (
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val featuredImage: String? = null,
    @ServerTimestamp
    val releasedDate: Date? = null

)
