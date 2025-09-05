package cm.daccvo.di

import cm.daccvo.repository.ImcRepository
import cm.daccvo.repository.ImcRepositoryImpl
import org.koin.dsl.module

val KoinModule = module {
    single<ImcRepository> {
        ImcRepositoryImpl()
    }
}