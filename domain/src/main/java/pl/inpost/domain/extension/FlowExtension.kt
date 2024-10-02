package pl.inpost.domain.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import pl.inpost.domain.remotedata.RemoteData

fun <T> Flow<T>.remotelify(): Flow<RemoteData<Throwable, T>> = this
    .map<T, RemoteData<Throwable, T>> { RemoteData.Success(it) }
    .onStart { emit(RemoteData.Loading) }
    .catch { error ->
        emit(RemoteData.Failure(error))
    }


