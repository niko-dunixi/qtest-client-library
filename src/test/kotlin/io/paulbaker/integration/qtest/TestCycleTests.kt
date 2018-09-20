package io.paulbaker.integration.qtest

import io.paulbaker.integration.getTestProject
import io.paulbaker.integration.randomUUID
import io.paulbaker.integration.testableQTestClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TestCycleTests {

    val testableQTestClient = testableQTestClient()

    @Test
    fun testCreateRootTestCycles() {
        val project = getTestProject()
        val testCycleClient = testableQTestClient.testCycleClient(project.id)
        val rootName = "${randomUUID()}-root-tc-empty"
        val rootTestCycle = testCycleClient.create(rootName)
        assertThat(rootTestCycle.id).isGreaterThan(0)
        assertThat(rootTestCycle.name).isEqualTo(rootName)
    }

    @Test
    fun testCreateNestedTestCycles() {
        val project = getTestProject()
        val testCycleClient = testableQTestClient.testCycleClient(project.id)
        val rootName = "${randomUUID()}-root-tc-with-nested-tc"
        val rootTestCycle = testCycleClient.create(rootName)
        assertThat(rootTestCycle.id).isGreaterThan(0)
        assertThat(rootTestCycle.name).isEqualTo(rootName)

        val nestedName = "${randomUUID()}-tc-nested-under-tc"
        val nestedTestCycle = testCycleClient.create(nestedName, TestCycleParent.TEST_CYCLE, rootTestCycle.id)
        assertThat(nestedTestCycle.id).isGreaterThan(0)
        assertThat(nestedTestCycle.name).isEqualTo(nestedName)

        assertThat(rootTestCycle).isNotEqualTo(nestedTestCycle)
    }

    @Test
    fun testCreateNestedUnderRelease() {
        val project = getTestProject()
        val releaseClient = testableQTestClient.releaseClient(project.id)
        val rootName = "${randomUUID()}-root-release-with-nested-tc"
        val release = releaseClient.create(rootName)
        val testCycleClient = testableQTestClient.testCycleClient(project.id)
        val nestedName = "${randomUUID()}-tc-nested-under-release"
        val testCycle = testCycleClient.create(nestedName, TestCycleParent.RELEASE, release.id)
        assertThat(testCycle.name).isEqualTo(nestedName)
    }

    @Test
    fun testDeleteTestCycles() {
        val project = getTestProject()
        val testCycleClient = testableQTestClient.testCycleClient(project.id)
        val name = randomUUID()
        val createdTestCycle = testCycleClient.create(name)
        assertThat(createdTestCycle.id).isGreaterThan(0)
        assertThat(createdTestCycle.name).isEqualTo(name)
//        assert(testCycleClient.delete(createdTestCycle.id), { "Couldn't delete the test-cycle: ${createdTestCycle.name} - ${createdTestCycle.id}" })
//        assert(!testCycleClient.delete(createdTestCycle.id), { "We shouldn't be able to delete the test-cycle twice: ${createdTestCycle.name} - ${createdTestCycle.id}" })
    }

    @Test
    fun testGetAllTestCycles() {
        val testProject = getTestProject()
        val releaseClient = testableQTestClient.releaseClient(testProject.id)
        val releases = releaseClient.releases()
        assertThat(releases).isNotEmpty
        releases.forEach { release ->
            assertThat(release.id).isGreaterThan(0)
            assertThat(release.name).isNotNull().isNotEmpty()
        }
    }

    @Test
    fun testGetIndividualTestCycles() {
        val testProject = getTestProject()
        val testCycleClient = testableQTestClient.testCycleClient(testProject.id)
        val testCycles = testCycleClient.testCycles()
        assertThat(testCycles).isNotEmpty
        testCycles.forEach { release ->
            val releaseFromId = testCycleClient.fromId(release.id)
            assertThat(releaseFromId).isEqualTo(release)
        }
    }
}