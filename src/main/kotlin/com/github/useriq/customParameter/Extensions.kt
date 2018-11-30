package com.github.useriq.customParameter

import com.intellij.execution.RunConfigurationExtension
import com.intellij.execution.configurations.JavaParameters
import com.intellij.execution.configurations.RunConfigurationBase
import com.intellij.execution.configurations.RunnerSettings

class IdeaRunConfigurationExtension : RunConfigurationExtension() {
    override fun isApplicableFor(configuration: RunConfigurationBase): Boolean {
        return true
    }

    override fun <T : RunConfigurationBase?> updateJavaParameters(
        configuration: T,
        params: JavaParameters?,
        runnerSettings: RunnerSettings?
    ) {
        params?.vmParametersList?.addNotEmptyProperty("config", System.getProperty("parameter"))
    }
}