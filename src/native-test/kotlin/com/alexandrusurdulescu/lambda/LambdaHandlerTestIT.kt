package com.alexandrusurdulescu.lambda

import io.quarkus.test.junit.NativeImageTest

@NativeImageTest
class LambdaHandlerTestIT : HelloLambdaHandlerTest() {
    // Execute the same tests but in native mode.
}
