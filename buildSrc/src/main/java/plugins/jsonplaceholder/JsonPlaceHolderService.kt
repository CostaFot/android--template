package plugins.jsonplaceholder

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderService {

    @GET("/todos/{id}")
    fun getTodo(@Path(value = "id") todoId: Int): Call<ApiTodo>
}
