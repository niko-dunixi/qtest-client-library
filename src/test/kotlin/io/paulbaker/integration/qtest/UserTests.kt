package io.paulbaker.integration.qtest

import io.paulbaker.integration.getTestProject
import io.paulbaker.integration.testableQTestClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTests {

    @Test
    fun testGetUsers() {
        val testableQTestClient = testableQTestClient()
        val projectId = getTestProject().id
        val projectClient = testableQTestClient.projectClient()
        val users = projectClient.users(projectId)
        assertThat(users).isNotEmpty

        val userClient = testableQTestClient.userClient()
        users.forEach { user ->
            assertThat(user.id).isGreaterThan(0)
            assertThat(user.username).isNotNull().isNotEmpty()
            val fromId = userClient.fromId(user.id)
            assertThat(user).isEqualTo(fromId)
        }
    }
}