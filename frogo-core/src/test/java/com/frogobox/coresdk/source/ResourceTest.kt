package com.frogobox.coresdk.source

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

class ResourceTest {

    @Test
    fun testResourceSuccess() {
        val data = "Sample Data"
        val resource: Resource<String> = Resource.Success(data)
        when (resource) {
            is Resource.Success -> assertEquals(data, resource.result)
            else -> fail("Expected Resource.Success")
        }
    }

    @Test
    fun testResourceError() {
        val resource: Resource<String> = Resource.Error(404, "Not Found")
        when (resource) {
            is Resource.Error -> {
                assertEquals(404, resource.code)
                assertEquals("Not Found", resource.message)
            }
            else -> fail("Expected Resource.Error")
        }
    }

    @Test
    fun testResourceLoading() {
        val resource: Resource<String> = Resource.Loading()
        assertTrue(resource is Resource.Loading)
    }

    @Test
    fun testResourceStateSuccess() {
        val state: ResourceState = ResourceState.Success()
        assertTrue(state is ResourceState.Success)
    }

    @Test
    fun testResourceStateError() {
        val state: ResourceState = ResourceState.Error(500, "Internal Server Error")
        when (state) {
            is ResourceState.Error -> {
                assertEquals(500, state.code)
                assertEquals("Internal Server Error", state.message)
            }
            else -> fail("Expected ResourceState.Error")
        }
    }

    @Test
    fun testResourceStateLoading() {
        val state: ResourceState = ResourceState.Loading()
        assertTrue(state is ResourceState.Loading)
    }

    @Test
    fun testDownloadResourceSuccess() {
        val localPath = "/downloads/file.zip"
        val download: DownloadResource = DownloadResource.Success(localPath)
        when (download) {
            is DownloadResource.Success -> assertEquals(localPath, download.localPath)
            else -> fail("Expected DownloadResource.Success")
        }
    }

    @Test
    fun testDownloadResourceLoading() {
        val progress = 75
        val download: DownloadResource = DownloadResource.Loading(progress)
        when (download) {
            is DownloadResource.Loading -> assertEquals(progress, download.progress)
            else -> fail("Expected DownloadResource.Loading")
        }
    }

    @Test
    fun testDownloadResourceError() {
        val errorMessage = "Disk Full"
        val download: DownloadResource = DownloadResource.Error(errorMessage)
        when (download) {
            is DownloadResource.Error -> assertEquals(errorMessage, download.message)
            else -> fail("Expected DownloadResource.Error")
        }
    }
}
