package com.example.move

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform