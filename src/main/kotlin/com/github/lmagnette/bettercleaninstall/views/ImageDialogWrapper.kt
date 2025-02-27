package com.github.lmagnette.bettercleaninstall.views

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import java.awt.BorderLayout
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JComponent
import javax.swing.JPanel

class ImageDialogWrapper(val imagePath: String) : DialogWrapper(true) {

    init {
        init() // initializes the dialog components
        title = "For the love of god please use mvn verify"
    }

    override fun createCenterPanel(): JComponent? {
        val panel = JPanel(BorderLayout())

        val url = URL(imagePath)
        val bufferedImage = ImageIO.read(url)
        val icon = ImageIcon(bufferedImage)
        // Load the image resource; ensure the image is in your resources folder.
        val label = JBLabel(icon)
        panel.add(label, BorderLayout.CENTER)
        return panel
    }
}