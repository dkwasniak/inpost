package pl.inpost.recruitmenttask.remotedata

import androidx.compose.runtime.Composable
import pl.inpost.domain.remotedata.RemoteData


@Composable
fun <Error, Data> RemoteData<Error, Data>.bindComposable(
    notAsked: @Composable () -> Unit,
    loading: @Composable () -> Unit,
    failure: @Composable (Error) -> Unit,
    success: @Composable (Data) -> Unit,
) = when (this) {
    RemoteData.NotAsked -> notAsked()
    RemoteData.Loading -> loading()
    is RemoteData.Failure -> failure(error)
    is RemoteData.Success -> success(data)
}

@Composable
fun <Error, Data> RemoteData<Error, Data>.bindComposable(
    loadingBinder: @Composable (Boolean) -> Unit = {},
    errorBinder: @Composable (Error?) -> Unit = {},
    dataBinder: @Composable (Data?) -> Unit,
) = bindComposable(
    notAsked = {
        dataBinder(null)
    }, loading = {
        loadingBinder(true)
    }, failure = { error ->
        errorBinder(error)
    }, success = { data ->
        dataBinder(data)
    }
)