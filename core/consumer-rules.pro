# ============================================
# CORE MODULE - CONSUMER RULES
# Aturan ini diwariskan ke module :app dan :favorite
# ============================================

# === PASTIKAN INI ADA DI PALING ATAS ===
-keep class rachman.forniandi.core.** { *; }

# Keep Data Source classes
-keep class rachman.forniandi.core.data.local.** { *; }
-keep class rachman.forniandi.core.data.remote.** { *; }
-keep class rachman.forniandi.core.data.network.** { *; }

# Keep Room database entities and DAOs
-keep class rachman.forniandi.core.data.local.entity.** { *; }
-keep class rachman.forniandi.core.data.local.room.** { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao class *
-keep @androidx.room.Database class *

# Keep Dagger/Hilt generated classes
-keep class rachman.forniandi.core.di.** { *; }
-keep class * extends dagger.internal.Factory { *; }
-keep class * extends dagger.internal.Provider { *; }
-keep class * extends dagger.internal.DoubleCheck { *; }
-keep class * extends javax.inject.Provider { *; }

# Keep domain models and use cases
-keep class rachman.forniandi.core.domain.entity.** { *; }
-keep class rachman.forniandi.core.domain.usecase.** { *; }
-keep class rachman.forniandi.core.domain.interactor.** { *; }

# Keep repositories
-keep class rachman.forniandi.core.repositories.** { *; }

# Keep utility classes
-keep class rachman.forniandi.core.utilRemote.** { *; }

# Retrofit and network rules
-keep class rachman.forniandi.core.data.remote.response.** { *; }
-keep class rachman.forniandi.core.data.network.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeVisibleTypeAnnotations
-keepattributes EnclosingMethod

# Retrofit specific rules
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-dontwarn retrofit2.**
-dontwarn okhttp3.**
-dontwarn okio.**

# Keep Parcelable classes
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep Serializable classes
-keep class * implements java.io.Serializable {
    *;
}

# Keep enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep classes with @Keep annotation
-keep @androidx.annotation.Keep class *
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

# R8 full mode compatibility (AGP 8+)
-keep,allowoptimization,allowobfuscation,allowshrinking class * extends androidx.lifecycle.ViewModel {
    <init>();
}
-keep,allowoptimization,allowobfuscation,allowshrinking class * extends android.app.Application {
    <init>();
}

# Keep constructors for Dagger/Hilt
-keepclassmembers class * {
    @dagger.* <init>(...);
    @javax.inject.* <init>(...);
}

# Ignore warnings for missing classes (if they're not critical)
-dontwarn rachman.forniandi.core.data.local.ContentsLocalDataSource
-dontwarn rachman.forniandi.core.data.local.datasource.DataSourceReference
-dontwarn rachman.forniandi.core.data.local.datasource.SettingPreferenceImpl
-dontwarn rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
-dontwarn rachman.forniandi.core.data.local.room.ContentsDao
-dontwarn rachman.forniandi.core.data.local.room.FavoriteContentDao
-dontwarn rachman.forniandi.core.data.network.NetworkService
-dontwarn rachman.forniandi.core.data.network.RemoteResponse$Error
-dontwarn rachman.forniandi.core.data.network.RemoteResponse$Loading
-dontwarn rachman.forniandi.core.data.network.RemoteResponse$Success
-dontwarn rachman.forniandi.core.data.network.RemoteResponse
-dontwarn rachman.forniandi.core.data.remote.response.RemoteSourceData
-dontwarn rachman.forniandi.core.di.DatabaseModule_ProvideContentsDaoFactory
-dontwarn rachman.forniandi.core.di.DatabaseModule_ProvideContentsDatabaseFactory
-dontwarn rachman.forniandi.core.di.DatabaseModule_ProvideDatabasePassphraseFactory
-dontwarn rachman.forniandi.core.di.DatabaseModule_ProvideFavoriteContentDaoFactory
-dontwarn rachman.forniandi.core.di.DatabaseModule_ProvideSupportFactoryFactory
-dontwarn rachman.forniandi.core.di.LocalDataSourceModule_ProvideContentsLocalDataSourceFactory
-dontwarn rachman.forniandi.core.di.NetworkModule_ProvideApiServiceFactory
-dontwarn rachman.forniandi.core.di.NetworkModule_ProvideCheckerCollectorFactory
-dontwarn rachman.forniandi.core.di.NetworkModule_ProvideCheckerInterceptorFactory
-dontwarn rachman.forniandi.core.di.NetworkModule_ProvideConverterFactoryFactory
-dontwarn rachman.forniandi.core.di.NetworkModule_ProvideHttpClientFactory
-dontwarn rachman.forniandi.core.di.NetworkModule_ProvideRetrofitInstanceNetFactory
-dontwarn rachman.forniandi.core.domain.entity.AuthorContents
-dontwarn rachman.forniandi.core.domain.entity.ContentType
-dontwarn rachman.forniandi.core.domain.entity.Contents
-dontwarn rachman.forniandi.core.domain.interactor.ArticlesInteractor
-dontwarn rachman.forniandi.core.domain.interactor.BlogsInteractor
-dontwarn rachman.forniandi.core.domain.interactor.FavoriteContentsInteractor
-dontwarn rachman.forniandi.core.domain.usecase.ArticlesUseCase
-dontwarn rachman.forniandi.core.domain.usecase.BlogsUseCase
-dontwarn rachman.forniandi.core.domain.usecase.FavoriteContentUseCase
-dontwarn rachman.forniandi.core.repositories.ContentsRepository
-dontwarn rachman.forniandi.core.repositories.ContentsRepositoryImpl
-dontwarn rachman.forniandi.core.utilRemote.AttributeMapperKt
-dontwarn rachman.forniandi.core.utilRemote.NavigationProvider
-dontwarn rachman.forniandi.core.utilRemote.ViewSupportKt