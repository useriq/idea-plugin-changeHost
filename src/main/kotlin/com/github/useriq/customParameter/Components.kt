package com.github.useriq.customParameter

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.MessageType
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.wm.WindowManager
import com.intellij.ui.awt.RelativePoint


class MyPluginRegistration : ApplicationComponent {
    // Returns the component name (any unique string value).

    // If you register the MyPluginRegistration class in the <application-components> section of
    // the plugin.xml file, this method is called on IDEA start-up.
    override fun initComponent() {
        val am = ActionManager.getInstance()

        val actionToolBar = SelectHost()

        am.registerAction("SelectHostsAction", actionToolBar)

        val toolBar = am.getAction("MainToolBar") as DefaultActionGroup

        toolBar.addSeparator()
        toolBar.add(actionToolBar)
    }

    // Disposes system resources.
    override fun disposeComponent() {}
}

class HostsComponent(val project: Project) : ProjectComponent {
    var parameter = "int1"
    val parameters = mutableMapOf("schema" to "int1")
    override fun projectOpened() {
    }

    fun showMessage(htmlText: String) {
        val statusBar = WindowManager.getInstance().getStatusBar(project)
        JBPopupFactory.getInstance()
            .createHtmlTextBalloonBuilder(htmlText, MessageType.INFO, null)
            .setFadeoutTime(3500)
            .createBalloon()
            .show(RelativePoint.getCenterOf(statusBar.component), Balloon.Position.atRight)
    }
}