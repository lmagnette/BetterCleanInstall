package be.lomagnette.bettercleaninstall.models


import kotlinx.serialization.Serializable

@Serializable
data class GitHubFile(
    val name: String,
    val path: String,
    val sha: String,
    val size: Int,
    val url: String,
    val html_url: String,
    val git_url: String,
    val download_url: String,
    val type: String,
    val _links: Links
)
