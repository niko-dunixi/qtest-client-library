package io.paulbaker.integration.qtest

import io.paulbaker.integration.testableQTestClient
import io.paulbaker.qtest.FieldParent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FieldTests {

    @Test
    fun testGetAll() {
        val testableQTestClient = testableQTestClient()

        val fieldClient = testableQTestClient.fieldClient(49099)
        val fields = fieldClient.fields(FieldParent.TEST_CASE)
        assertThat(fields).isNotEmpty
    }
}