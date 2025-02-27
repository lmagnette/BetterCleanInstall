package be.lomagnette.bettercleaninstall.models

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val self: String,
    val git: String,
    val html: String
)
