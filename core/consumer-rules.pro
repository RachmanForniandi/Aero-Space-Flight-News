# ============================================
# CORE MODULE - CONSUMER RULES
# Aturan ini diwariskan ke module :app dan :favorite
# ============================================

# === DOMAIN MODELS (ENTITY) ===
# Model classes dari package domain.entity
-keep class rachman.forniandi.core.domain.entity.** {
    <fields>;
    <init>(...);
}

# Model classes spesifik
-keep class rachman.forniandi.core.domain.entity.AuthorContents { *; }
-keep class rachman.forniandi.core.domain.entity.Contents { *; }
-keep class rachman.forniandi.core.domain.entity.ContentType { *; }

# === DATA LAYER - LOCAL ENTITY ===
# Entity untuk Room database
-keep class rachman.forniandi.core.data.local.entity.** {
    <fields>;
    <init>(...);
}

# Entity spesifik
-keep class rachman.forniandi.core.data.local.entity.FavoriteContentsEntity { *; }
-keep class rachman.forniandi.core.data.local.entity.RemoteKeys { *; }

# === ROOM DATABASE & CONVERTERS ===
# Database class
-keep class rachman.forniandi.core.data.local.room.ContentsDatabase { *; }
-keep class rachman.forniandi.core.data.local.room.ContentsDao { *; }
-keep class rachman.forniandi.core.data.local.room.FavoriteContentDao { *; }
-keep class rachman.forniandi.core.data.local.room.RemoteKeysDao { *; }

# Type converters (penting untuk Room)
-keep class rachman.forniandi.core.data.local.room.AuthorConverter { *; }
-keep class rachman.forniandi.core.data.local.room.ContentTypeConverter { *; }

# Local data source
-keep class rachman.forniandi.core.data.local.datasource.** { *; }
-keep class rachman.forniandi.core.data.local.ContentsLocalDataSource.** { *; }
-keep class rachman.forniandi.core.data.local.datasource.SettingPreferenceImpl { *; }
-keep class rachman.forniandi.core.data.local.datasource.DataSourceReference { *; }
-keep class rachman.forniandi.core.data.local.entity.FavoriteContentsEntity{ *; }
-keep class rachman.forniandi.core.data.local.entity.RemoteKeys{ *; }
-keep class rachman.forniandi.core.data.local.room.AuthorConverter{ *; }
-keep class rachman.forniandi.core.data.local.room.ContentsDao{ *; }
-keep class rachman.forniandi.core.data.local.room.ContentsDatabase{ *; }
-keep class rachman.forniandi.core.data.local.room.ContentTypeConverter{ *; }
-keep class rachman.forniandi.core.data.local.room.FavoriteContentDao{ *; }
-keep class rachman.forniandi.core.data.local.room.RemoteKeysDao{ *; }

# === SQLCIPHER (LIBRARY ENKRIPSI DATABASE) ===
# Net.zetetic SQLCipher rules
-keep class net.sqlcipher.** { *; }
-keep interface net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

# SQLCipher database classes
-keep class net.sqlcipher.database.** { *; }
-keep interface net.sqlcipher.database.** { *; }

# SQLCipher OpenHelper
-keep class net.sqlcipher.database.SQLiteOpenHelper { *; }
-keep class net.sqlcipher.database.SupportHelper { *; }
-keep class net.sqlcipher.database.SupportFactory { *; }

# SQLCipher cursor classes
-keep class net.sqlcipher.Cursor { *; }
-keep class net.sqlcipher.AbstractCursor { *; }

# CrossProcessCursor dan CrossProcessCursorWrapper Android framework
-keep class android.database.CrossProcessCursor { *; }
-keep class android.database.CrossProcessCursorWrapper { *; }


# Native libraries
-keep class net.sqlcipher.database.SQLiteDatabase {
    native <methods>;
}

# === ROOM DATABASE (SUDAH ADA) ===
# ... aturan Room yang sudah ada sebelumnya ...

# === DOMAIN MODELS (SUDAH ADA) ===
# ... aturan model yang sudah ada sebelumnya ...

# === ANDROID KEYSTORE (UNTUK ENKRIPSI) ===
-keep class android.security.keystore.** { *; }
-keep class javax.crypto.** { *; }
-keep class java.security.** { *; }
-dontwarn android.security.keystore.**

# === KOTLINX COROUTINES ===
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}



# === REMOTE RESPONSE MODELS ===
# Response models untuk Retrofit
-keep class rachman.forniandi.core.data.remote.response.** {
    <fields>;
    <init>(...);
}

# Response spesifik
-keep class rachman.forniandi.core.data.remote.response.RemoteSourceData { *; }
-keep class rachman.forniandi.core.data.remote.response.ResponseGeneral { *; }

# === NETWORK SERVICE ===
# API Interface untuk Retrofit
-keep class rachman.forniandi.core.data.remote.network.** { *; }
-keep interface rachman.forniandi.core.data.network.NetworkService{ *; }
-keep interface rachman.forniandi.core.data.network.RemoteResponse{ *; }

# === REPOSITORIES ===
# Repository interfaces dan implementasinya
-keep class rachman.forniandi.core.repositories.** { *; }
-keep class rachman.forniandi.core.repositories.ContentsRepository { *; }
-keep class rachman.forniandi.core.repositories.ContentsRepositoryImpl { *; }



# === USE CASES ===
# Interactors dan UseCases
-keep class rachman.forniandi.core.domain.interactor.** { *; }
-keep class rachman.forniandi.core.domain.usecase.** { *; }

# UseCase & Interactor spesifik
-keep class rachman.forniandi.core.domain.interactor.ArticlesInteractor { *; }
-keep class rachman.forniandi.core.domain.interactor.BlogsInteractor { *; }
-keep class rachman.forniandi.core.domain.interactor.FavoriteContentsInteractor { *; }

-keep class rachman.forniandi.core.domain.usecase.ArticlesUseCase { *; }
-keep class rachman.forniandi.core.domain.usecase.BlogsUseCase { *; }
-keep class rachman.forniandi.core.domain.usecase.FavoriteContentUseCase { *; }

# === PAGING ===
# RemoteMediator untuk Paging
-keep class rachman.forniandi.core.paging.ContentsRemoteMediator { *; }

# === ADAPTERS ===
# Adapter classes yang digunakan di UI
-keep class rachman.forniandi.core.adapters.** { *; }
-keep class rachman.forniandi.core.adapters.CarrouselAdapter { *; }
-keep class rachman.forniandi.core.adapters.ContentAdapter { *; }
-keep class rachman.forniandi.core.adapters.LoadingStatePageAdapter { *; }

# === UTILITIES ===
# Utility classes
-keep class rachman.forniandi.core.utilRemote.** { *; }
-keep class rachman.forniandi.core.utilRemote.AttributeMapperKt { *; }
-keep class rachman.forniandi.core.utilRemote.NavigationProvider { *; }
-keep class rachman.forniandi.core.utilRemote.ViewSupportKt{ *; }

# === DI MODULES ===
# Dagger/Hilt modules (penting untuk injection)
-keep class rachman.forniandi.core.di.** { *; }
-keep class rachman.forniandi.core.di.DatabaseModule { *; }
-keep class rachman.forniandi.core.di.DataStoreModule { *; }
-keep class rachman.forniandi.core.di.LocalDataSourceModule { *; }
-keep class rachman.forniandi.core.di.NetworkModule { *; }
-keep class rachman.forniandi.core.di.RepositoryModule { *; }
-keep class rachman.forniandi.core.di.UseCaseModule { *; }

# === UNTUK MENJAGA METHOD/ATTRIBUTE ANNOTATION ===
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions

# === UNTUK KOTLIN DATA CLASS ===
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keep class kotlin.Metadata { *; }
-keepclassmembers class ** {
    @kotlin.Metadata <fields>;
}