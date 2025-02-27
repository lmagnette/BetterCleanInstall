package com.github.lmagnette.bettercleaninstall.services

import com.github.lmagnette.bettercleaninstall.util.HttpClient
import com.github.lmagnette.bettercleaninstall.views.ImageDialogWrapper
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project


@Service
class MavenListenerService {
    fun onMavenCleanInstall(project: Project) {

        val http = HttpClient();
        val result = http.getMvnCleanInstallMeme("https://api.github.com/repos/aalmiray/mvn-clean-install/contents/")
        val imgUrls = result.map { file -> file.download_url }.toList()
        val selectedImg = imgUrls[(0..imgUrls.size-1).random()];
        thisLogger().warn("Mvn clean install finished for $selectedImg")

        val dialog = ImageDialogWrapper(selectedImg);
        dialog.show()

        // This is where you implement your custom action
        // For example, show a notification
        val notification = Notification(
            "com.github.lmagnette.bettercleaninstall",
            "Maven Clean Install Detected",
            imgUrls.toString(),
            NotificationType.INFORMATION
        )
        Notifications.Bus.notify(notification, project)

        // Add your custom logic here
        // For example: run a script, update files, etc.
    }
}