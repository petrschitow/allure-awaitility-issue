package config

import io.qameta.allure.listener.StepLifecycleListener
import io.qameta.allure.model.StepResult

class StepsListener : StepLifecycleListener, TestConfiguration() {

    override fun beforeStepStart(result: StepResult) {
        log.info(result.name)
    }
}