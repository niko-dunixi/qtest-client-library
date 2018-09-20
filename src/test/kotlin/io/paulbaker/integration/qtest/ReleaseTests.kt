package io.paulbaker.integration.qtest

import io.paulbaker.integration.getTestProject
import io.paulbaker.integration.randomUUID
import io.paulbaker.integration.testableQTestClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReleaseTests {

    @Test
    fun testCreateRelease() {
        val testProject = getTestProject()
        val releaseClient = testableQTestClient().releaseClient(testProject.id)
        val releaseName = randomUUID()
        val release = releaseClient.create(releaseName)
        assertThat(release.id).isGreaterThan(0)
        assertThat(release.name).isEqualTo(releaseName)
    }

    @Test
    fun testDeleteRelease() {
        val testProject = getTestProject()
        val releaseClient = testableQTestClient().releaseClient(testProject.id)
        val releaseName = randomUUID()
        val release = releaseClient.create(releaseName)
//        assert(releaseClient.delete(release.id), { "Couldn't delete release." })
//        assert(!releaseClient.delete(release.id), { "Shouldn't be able to delete twice." })
    }

    @Test
    fun testGetAllRequirements() {
        val testProject = getTestProject()
        val releaseClient = testableQTestClient().releaseClient(testProject.id)
        val releases = releaseClient.releases()
        assertThat(releases).isNotEmpty
        releases.forEach { release ->
            assertThat(release.id).isGreaterThan(0)
            assertThat(release.name).isNotNull().isNotEmpty()
        }
    }

    @Test
    fun testGetSingleRequirement() {
        val testProject = getTestProject()
        val releaseClient = testableQTestClient().releaseClient(testProject.id)
        val releases = releaseClient.releases()
        assertThat(releases).isNotEmpty
        releases.forEach { release ->
            assertThat(release.id).isGreaterThan(0)
            assertThat(release.name).isNotNull().isNotEmpty()
            val releaseFromId = releaseClient.fromId(release.id)
            assertThat(release).isEqualTo(releaseFromId)
        }
    }
}