package com.github.useriq.customParameter

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.openapi.actionSystem.ex.ComboBoxAction
import com.intellij.ui.SizedIcon
import com.intellij.util.ui.JBUI
import javax.swing.JButton
import javax.swing.JComponent


private val EDIT_ICON = JBUI.scale(SizedIcon(AllIcons.General.Add, 16, 16))

class SelectHost : ComboBoxAction() {

    var hosts = mutableListOf("int1", "int2", "int3", "int4", "int5")

    override fun createComboBoxButton(presentation: Presentation?): ComboBoxButton {
        presentation?.text =  "select parameter"
        return super.createComboBoxButton(presentation)
    }

    override fun createPopupActionGroup(button: JComponent?): DefaultActionGroup {
        val group = DefaultActionGroup()

        hosts.forEach {
            group.add(object : AnAction(it) {
                override fun actionPerformed(e: AnActionEvent) {
                    val project = e.project ?: return
                    val hostsComponent = project.getComponent(HostsComponent::class.java)

                    hostsComponent?.parameter = it

                    System.setProperty("parameter", it)

                    hostsComponent?.showMessage(it)

                    (button as JButton).text = it

                    button.updateUI()
                }
            })
        }

        group.addSeparator()
        group.add(object : AnAction("addHost", "addYourHost", EDIT_ICON) {
            override fun actionPerformed(e: AnActionEvent) {
            }
        })
        return group
    }

    override fun actionPerformed(e: AnActionEvent) {

    }


}
