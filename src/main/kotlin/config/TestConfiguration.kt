package config

import io.qameta.allure.awaitility.AllureAwaitilityListener
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.ObjectMapperConfig
import io.restassured.mapper.ObjectMapperType
import org.awaitility.Awaitility
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

open class TestConfiguration {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    companion object {

        init {
            //RestAssured configuration
            RestAssured.config = RestAssured.config().objectMapperConfig(ObjectMapperConfig(ObjectMapperType.GSON))
            RestAssured.requestSpecification =
                RequestSpecBuilder().setContentType(io.restassured.http.ContentType.JSON).build()

            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()


            //Awaitility configuration
            Awaitility.setDefaultPollInterval(1, TimeUnit.SECONDS)
            Awaitility.setDefaultTimeout(30, TimeUnit.SECONDS)
            Awaitility.ignoreExceptionsByDefault()
            Awaitility.setDefaultConditionEvaluationListener(
                AllureAwaitilityListener()
            )
        }
    }

}