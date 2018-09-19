package io.paulbaker.integration.qtest

import io.paulbaker.integration.testableQTestClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProjectTests {

    private val testableQTestClient = testableQTestClient()

    @Test
    fun testGetAll() {
        val projectClient = testableQTestClient.projectClient()
        val projects = projectClient.projects()
        assertThat(projects).isNotEmpty
        projects.forEach({ project ->
            assertThat(project.id).isGreaterThan(0L)
            assertThat(project.name).isNotNull().isNotEmpty()
        })
    }

    @Test
    fun testGetAllIndividually() {
        val projectClient = testableQTestClient.projectClient()
        val projects = projectClient.projects()
        assertThat(projects).isNotEmpty
        projects.forEach({ project ->
            val projectFromId = projectClient.fromId(project.id)
            assertThat(project).isEqualTo(projectFromId)
        })
    }

//    @Test
//    fun testCreateSingleProject() {
//        val projectClient = testableQTestClient.projectClient()
//        val project = projectClient.create("paulbaker-unit-test-${System.currentTimeMillis()}")
//        assert(projectClient.delete(project.id), { "Couldn't delete project" })
//    }
}
