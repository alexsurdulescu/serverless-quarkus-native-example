package com.alexandrusurdulescu.lambda

import com.alexandrusurdulescu.lambda.data.Greeting
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import io.restassured.RestAssured
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

@QuarkusTest
@TestProfile(GoodbyeTestProfile::class)
open class GoodbyeLambdaHandlerTest {
    @Test
    @Throws(Exception::class)
    fun testSimpleLambdaSuccess() {
        // you test your lambdas by invoking on http://localhost:8081
        // this works in dev mode too
        val event = APIGatewayProxyRequestEvent()
        event.body = Json.encodeToString(Greeting("Alex"))

        RestAssured.given()
            .contentType("application/json")
            .accept("application/json")
            .body(event)
            .`when`()
            .post()
            .then()
            .statusCode(200)
            .body(CoreMatchers.containsString("Goodbye Alex"))
    }
}

class GoodbyeTestProfile : QuarkusTestProfile {
    override fun getConfigOverrides(): MutableMap<String, String> {
        return mutableMapOf("quarkus.lambda.handler" to "goodbye")
    }
}
