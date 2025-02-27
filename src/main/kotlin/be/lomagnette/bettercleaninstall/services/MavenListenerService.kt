package be.lomagnette.bettercleaninstall.services

import be.lomagnette.bettercleaninstall.util.HttpClient
import be.lomagnette.bettercleaninstall.views.ImageDialogWrapper
import com.intellij.openapi.components.Service


@Service
class MavenListenerService {
    fun onMavenCleanInstall() {

        val http = HttpClient()
        val result = http.getMvnCleanInstallMeme("https://api.github.com/repos/aalmiray/mvn-clean-install/contents/")
        val imgUrls = result.map { file -> file.download_url }.toList()
        val selectedImg = imgUrls[(0..imgUrls.size-1).random()]
        val dialog = ImageDialogWrapper(selectedImg)
        dialog.show()
    }
}