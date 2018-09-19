package io.paulbaker.integration.qtest

import io.kotlintest.provided.testableQTestClient
import io.paulbaker.qtest.FieldParent
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.empty
import org.junit.jupiter.api.Test

class FieldTests {
    private val testableQTestClient = testableQTestClient()

    @Test
    fun testGetAll() {
        val fieldClient = testableQTestClient.fieldClient(49099)
        val fields = fieldClient.fields(FieldParent.TEST_CASE)
        assertThat(fields, not(empty()))
    }
}