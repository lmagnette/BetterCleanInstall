package be.lomagnette.bettercleaninstall.views

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.Action
import javax.swing.ImageIcon
import javax.swing.JComponent
import javax.swing.JPanel

class ImageDialogWrapper(val imagePath: String) : DialogWrapper(true) {

    init {
        init() // initializes the dialog components
        title = "You've Used Mvn Clean Install"
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

    override fun createActions(): Array<Action> {
        // First custom close button
        val closeButton1 = object : DialogWrapperAction("I Solemnly Swear To Use Mvn Verify Next Time") {
            override fun doAction(e: ActionEvent?) {
                close(OK_EXIT_CODE)
            }
        }
        return arrayOf(closeButton1)
    }

    override fun createSouthPanel(): JComponent {
        val panel = JPanel(FlowLayout(FlowLayout.CENTER))
        // Use the built-in helper to create buttons for the actions.
        for (action in createActions()) {
            panel.add(createJButtonForAction(action))
        }
        return panel
    }
}