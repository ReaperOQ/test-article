package ru.reaperoq.test_article.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.encodeURLPath
import io.ktor.http.path
import ru.reaperoq.test_article.api.models.Announcement
import ru.reaperoq.test_article.api.models.AnnouncementsResponse
import ru.reaperoq.test_article.api.models.ApiResponse

class AnnouncementsRemoteDataSource(
    private val httpClient: HttpClient
) {
    data class FetchErrorException(val code: Int, override val message: String) : Exception()

    suspend fun fetchLatestAnnouncements(limit: Int = 10): Result<List<Announcement>> {
        return try {
            val response = httpClient.get {
                url {
                    path("v5", "announcements", "index")
                    parameter("locale", "en-US")
                    parameter("limit", limit)
                }
            }

            if (response.status.value in 200..301) {
                val body = response.body<ApiResponse<AnnouncementsResponse>>()
                if (body.code == 0) {
                    Result.success(body.result!!.list)
                } else {
                    Result.failure(FetchErrorException(body.code, body.message))
                }
            } else {
                Result.failure(FetchErrorException(-1, "Unknown exception"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}