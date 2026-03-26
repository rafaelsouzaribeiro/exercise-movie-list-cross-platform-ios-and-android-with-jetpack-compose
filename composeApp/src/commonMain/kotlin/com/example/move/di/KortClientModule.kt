package com.example.move.di

import com.example.move.data.network.KortClient
import org.koin.dsl.module

val kortClientModule= module {
    single{
        KortClient()
    }
}