package com.github.lmagnette.bettercleaninstall.util

import com.github.lmagnette.bettercleaninstall.models.GitHubFile
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpClient
import kotlinx.serialization.json.Json

class HttpClient {

    fun getMvnCleanInstallMeme(url: String): List<GitHubFile> {
        val client = HttpClient.newBuilder().build()

        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val jsonResponse = response.body()

        val files: List<GitHubFile> = Json.decodeFromString(jsonResponse)

        return files;
    }
}