package com.frogobox.coresdk.source

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.http.GET

class FrogoApiClientTest {

    private lateinit var mockWebServer: MockWebServer

    interface TestApiService {
        @GET("test-endpoint")
        suspend fun getSampleData(): SampleResponse
    }

    data class SampleResponse(val status: String, val code: Int)

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testClientCreationAndNetworkRequest() = runBlocking {
        val serverUrl = mockWebServer.url("/").toString()

        // Mocking Retrofit service creation using FrogoApiClient
        val apiService: TestApiService = FrogoApiClient.create(
            url = serverUrl,
            isDebug = true
        )

        assertNotNull(apiService)

        // Queue mock response
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""{"status": "success", "code": 200}""")
        mockWebServer.enqueue(mockResponse)

        // Execute request
        val response = apiService.getSampleData()

        // Assertions
        assertEquals("success", response.status)
        assertEquals(200, response.code)

        // Verify request path
        val recordedRequest = mockWebServer.takeRequest()
        assertEquals("/test-endpoint", recordedRequest.path)
    }

    @Test
    fun testClientCreationWithoutTrailingSlash() = runBlocking {
        // Mock server URL without trailing slash
        val serverUrl = mockWebServer.url("").toString().removeSuffix("/")

        val apiService: TestApiService = FrogoApiClient.create(
            url = serverUrl,
            isDebug = true
        )
        assertNotNull(apiService)

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""{"status": "ok", "code": 200}""")
        mockWebServer.enqueue(mockResponse)

        val response = apiService.getSampleData()
        assertEquals("ok", response.status)
    }

    @Test
    fun testClientWithCustomInterceptor() {
        val client = FrogoApiClient.clientWithInterceptor(timeout = 15L)
        assertNotNull(client)
        assertEquals(15000L, client.readTimeoutMillis.toLong())
        assertEquals(15000L, client.connectTimeoutMillis.toLong())
    }
}
