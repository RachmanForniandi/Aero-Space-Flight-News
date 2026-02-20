# ============================================
# CORE MODULE - CONSUMER RULES
# Aturan ini diwariskan ke module :app dan :favorite
# ============================================

# === PASTIKAN INI ADA DI PALING ATAS ===
-keep class rachman.forniandi.core.** { *; }

# === ATAU LEBIH SPESIFIK (REKOMENDASI) ===
# DATA LAYER - LOCAL
-keep class rachman.forniandi.core.data.local.** { *; }
-keep class rachman.forniandi.core.data.local.datasource.** { *; }
-keep class rachman.forniandi.core.data.local.entity.** { *; }
-keep class rachman.forniandi.core.data.local.room.** { *; }
-keep class rachman.forniandi.core.data.local.ContentsLocalDataSource { *; }

# DATA LAYER - NETWORK & REMOTE
-keep class rachman.forniandi.core.data.network.** { *; }
-keep interface rachman.forniandi.core.data.network.** { *; }
-keep class rachman.forniandi.core.data.remote.response.** { *; }

# DI MODULES
-keep class rachman.forniandi.core.di.** { *; }

# DOMAIN LAYER
-keep class rachman.forniandi.core.domain.** { *; }
-keep class rachman.forniandi.core.domain.entity.** { *; }
-keep class rachman.forniandi.core.domain.interactor.** { *; }
-keep class rachman.forniandi.core.domain.usecase.** { *; }
-keep class rachman.forniandi.core.domain.paging.** { *; }
-keep class rachman.forniandi.core.domain.repositories.** { *; }

# UTILITIES
-keep class rachman.forniandi.core.utilRemote.** { *; }

# === DAGGER/HILT GENERATED CLASSES ===
-keep class **._Factory { *; }
-keep class **._MembersInjector { *; }
-keep class **.HiltModules { *; }
-keep class **.Hilt_* { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }

# === SQLCIPHER (ENKRIPSI DATABASE) ===
-keep class net.sqlcipher.** { *; }
-keep interface net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

# === ROOM DATABASE ===
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class *
-keep class * extends androidx.room.RoomDatabase
-keep class * extends androidx.room.RoomDatabase$* { *; }
-dontwarn androidx.room.paging.**

# === RETROFIT, OKHTTP, OKIO ===
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
-dontwarn retrofit2.**
-dontwarn okhttp3.**
-dontwarn okio.**

# === KOTLIN & COROUTINES ===
-keep class kotlin.Metadata { *; }
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class ** {
    public static ** INSTANCE;
    **$Companion **;
}

# === ENUM & PARCELABLE ===
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# === ANNOTATIONS ===
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions
-keepattributes RuntimeVisibleAnnotations