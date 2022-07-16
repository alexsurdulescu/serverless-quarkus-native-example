package com.alexandrusurdulescu.lambda

import com.alexandrusurdulescu.lambda.data.Greeting
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import javax.inject.Named
import kotlinx.serialization.json.Json

@Named("hello")
class HelloLambda : RequestHandler<APIGatewayProxyRequestEvent, String> {
    override fun handleRequest(input: APIGatewayProxyRequestEvent, context: Context?): String {
        val greeting = Json.decodeFromString(Greeting.serializer(), input.body)
        return "Hello ${greeting.name}"
    }
}
