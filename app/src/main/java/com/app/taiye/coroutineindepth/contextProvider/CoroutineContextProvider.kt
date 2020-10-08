package com.app.taiye.coroutineindepth.contextProvider

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {

    fun context(): CoroutineContext
}