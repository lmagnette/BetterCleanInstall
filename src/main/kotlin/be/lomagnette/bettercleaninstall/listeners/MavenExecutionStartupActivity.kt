package be.lomagnette.bettercleaninstall.listeners

import com.intellij.execution.ExecutionListener
import com.intellij.execution.ExecutionManager
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import org.jetbrains.idea.maven.execution.MavenRunnerParameters
import be.lomagnette.bettercleaninstall.services.MavenListenerService
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger

class MavenExecutionStartupActivity : StartupActivity {
    override fun runActivity(project: Project) {
        val connection = project.messageBus.connect()
        connection.subscribe(ExecutionManager.EXECUTION_TOPIC, object : ExecutionListener {
            override fun processStarted(executorId: String, env: ExecutionEnvironment, handler: ProcessHandler) {
                val runProfile = env.runProfile
                if (runProfile.javaClass.name.contains("MavenRunConfiguration")) {
                    // Use reflection to safely access the parameters
                    try {
                        val method = runProfile.javaClass.getDeclaredMethod("getRunnerParameters")
                        method.isAccessible = true
                        val parameters = method.invoke(runProfile) as? MavenRunnerParameters
                        val goals = parameters?.goals ?: return

                        if (goals.contains("clean") && goals.contains("install")) {
                            // The user is running "mvn clean install"
                            val service = project.service<MavenListenerService>()
                            service.onMavenCleanInstall()
                            println("Maven Clean Installed")
                        }
                    } catch (e: Exception) {
                        thisLogger().error(e.message, e)
                    }
                }
            }
        })
    }
}