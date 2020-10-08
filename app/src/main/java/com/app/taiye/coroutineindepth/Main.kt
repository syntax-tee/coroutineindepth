package com.app.taiye.coroutineindepth

import kotlinx.coroutines.*
import java.lang.IllegalStateException


fun main() {
    val userId = 992

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
    }

    val parentJob = Job()


    val job = GlobalScope.launch(
        start = CoroutineStart.LAZY,
        context = Dispatchers.Default + exceptionHandler + parentJob) {
        getUserByIdFromNetwork(userId) { user ->
            println(user)
            println(Thread.currentThread().name)
        }

    }

    job.start()

    GlobalScope.launch {
        print("Launching another job")

        job.join()

        println("Child Job finished")
    }

    while (job.isActive) {

    }
    Thread.sleep(50)

}






fun getUserByIdFromNetwork(userId: Int, onUserReady: (User) -> Unit) {
    Thread.sleep(3000)
    onUserReady(User(userId, "Taiye", "Babic"))
}


data class User(val id: Int, val name: String, val lastName: String)
