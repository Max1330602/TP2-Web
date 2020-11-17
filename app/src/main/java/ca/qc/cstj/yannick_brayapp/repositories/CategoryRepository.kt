package ca.qc.cstj.yannick_brayapp.repositories

import ca.qc.cstj.yannick_brayapp.helpers.RepositoryResult
import ca.qc.cstj.yannick_brayapp.helpers.Services
import ca.qc.cstj.yannick_brayapp.models.Category
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object CategoryRepository {
    suspend fun getCategories(): RepositoryResult<List<Category>> {

        //AJAX Get
        return withContext(Dispatchers.IO) {
            //Dans un autre thread
            val (_, _, result) = Services.CATEGORY_SERVICE.httpGet().responseJson()

            when (result) {
                is Result.Success -> {
                    //TODO: Transformer la string en List<Category> --> Déserialiser (DéSpécialK)
                    RepositoryResult.Success(
                        Json { ignoreUnknownKeys = true }.decodeFromString(
                            result.value.content
                        )
                    )
                }
                is Result.Failure -> {
                    RepositoryResult.Error(result.getException())
                }

            }
        }
    }
}