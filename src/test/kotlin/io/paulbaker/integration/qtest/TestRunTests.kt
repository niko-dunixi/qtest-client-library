package io.paulbaker.integration.qtest

import io.paulbaker.integration.getTestProject
import io.paulbaker.integration.randomUUID
import io.paulbaker.integration.testableQTestClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

class TestRunTests {

    private val testableQTestClient = testableQTestClient()

    @Test
    fun testCreateRootTestRun() {
        val projectId = getTestProject().id
        val testRunClient = testableQTestClient.testRunClient(projectId)
        val name = "${randomUUID()}-testrun-root"
        val testRun = testRunClient.create(name, 23818835)
        assertThat(testRun.id).isGreaterThan(0L)
        assertThat(testRun.name).isEqualTo(name)
    }

    @Test
    fun testDeleteTestRun() {
        val projectId = getTestProject().id
        val testRunClient = testableQTestClient.testRunClient(projectId)
        val name = "${randomUUID()}-testrun-deleteme"
        val testRun = testRunClient.create(name, 23818835)
        assertThat(testRun.id).isGreaterThan(0L)
        assertThat(testRun.name).isEqualTo(name)
        testRunClient.delete(testRun.id)
    }

    @Test
    fun testCreateTestRunNestedInTestCycle() {
        val projectId = getTestProject().id
        val testCycleClient = testableQTestClient.testCycleClient(projectId)
        val testCycle = testCycleClient.create("${randomUUID()}-testcycle-root")

        val testRunClient = testableQTestClient.testRunClient(projectId)
        val name = "${randomUUID()}-testrun-nested-in-testcycle"
        val testRun = testRunClient.create(name, 23818835, TestRunParent.TEST_CYCLE, testCycle.id)
        assertThat(testRun.id).isGreaterThan(0L)
        assertThat(testRun.name).isEqualTo(name)
    }

    @Test
    fun testSubmitTestResultsPassed() {
        val projectId = getTestProject().id
        val testRunClient = testableQTestClient.testRunClient(projectId)
        val testRunShouldPass = testRunClient.create("marked-passed", 23818835)
        val now = ZonedDateTime.now()
        val passedResults = TestResult("PASS", now, now, attachments = mutableListOf(TextAttachment("arbitrary.txt", "hello")))
        val submitTestResults = testRunClient.submitTestResults(testRunShouldPass.id, passedResults)
        println(submitTestResults)
    }

    @Test
    fun testSubmitTestResultsFailed() {
        val projectId = getTestProject().id
        val testRunClient = testableQTestClient.testRunClient(projectId)
        val testRunShouldPass = testRunClient.create("marked-failed", 23818835)
        val now = ZonedDateTime.now()
        val passedResults = TestResult("FAIL", now, now, attachments = mutableListOf(TextAttachment("arbitrary.txt", "hello")))
        val submitTestResults = testRunClient.submitTestResults(testRunShouldPass.id, passedResults)
        println(submitTestResults)
    }
}