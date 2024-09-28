package pl.inpost.recruitmenttask.remotedata

import java.io.Serializable

sealed class RemoteData<out Error, out Data> : Serializable {

    object NotAsked : RemoteData<Nothing, Nothing>()
    object Loading : RemoteData<Nothing, Nothing>()
    data class Failure<Error>(val error: Error) : RemoteData<Error, Nothing>()
    data class Success<Data>(val data: Data) : RemoteData<Nothing, Data>()
}

fun <Error, A, B> RemoteData<Error, A>.map(f: (A) -> B): RemoteData<Error, B> = when (this) {
    RemoteData.NotAsked -> RemoteData.NotAsked
    RemoteData.Loading -> RemoteData.Loading
    is RemoteData.Failure -> this
    is RemoteData.Success -> RemoteData.Success(f(data))
}

fun <A, B, Data> RemoteData<A, Data>.mapFailure(f: (A) -> B): RemoteData<B, Data> = when (this) {
    RemoteData.NotAsked -> RemoteData.NotAsked
    RemoteData.Loading -> RemoteData.Loading
    is RemoteData.Failure -> RemoteData.Failure(f(error))
    is RemoteData.Success -> this
}

fun <Error, Data> RemoteData<Error, Data>.bindBy(
    notAsked: () -> Unit,
    loading: () -> Unit,
    failure: (Error) -> Unit,
    success: (Data) -> Unit,
) = when (this) {
    RemoteData.NotAsked -> notAsked()
    RemoteData.Loading -> loading()
    is RemoteData.Failure -> failure(error)
    is RemoteData.Success -> success(data)
}

fun <Error, Data> RemoteData<Error, Data>.bind(
    loadingBinder: (Boolean) -> Unit = {},
    errorBinder: (Error?) -> Unit = {},
    dataBinder: (Data?) -> Unit = {},
) = bindBy(
    notAsked = {
        loadingBinder(false)
        errorBinder(null)
        dataBinder(null)
    }, loading = {
        // retain error and data
        loadingBinder(true)
    }, failure = { error ->
        // retain data
        loadingBinder(false)
        errorBinder(error)
    }, success = { data ->
        loadingBinder(false)
        errorBinder(null)
        dataBinder(data)
    }
)