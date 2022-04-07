package id.my.arieftb.meowvie.favorite.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.my.arieftb.meowvie.favorite.FavoriteFragment
import id.my.arieftb.meowvie.presentation.di.FavoriteModuleDependencies

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {
    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}