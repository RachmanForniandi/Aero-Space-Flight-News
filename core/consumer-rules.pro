# ============================================
# CORE MODULE - CONSUMER RULES
# Aturan ini diwariskan ke module :app dan :favorite
# ============================================

# === PASTIKAN INI ADA DI PALING ATAS ===
-keep class rachman.forniandi.core.** { *; }

# === ATAU LEBIH SPESIFIK DENGAN PACKAGE LENGKAP ===
-keep class rachman.forniandi.core.data.** { *; }
-keep class rachman.forniandi.core.domain.** { *; }
-keep class rachman.forniandi.core.di.** { *; }
-keep class rachman.forniandi.core.utilRemote.** { *; }

# === DAGGER/HILT GENERATED CLASSES ===
-keep class **._Factory { *; }
-keep class **._MembersInjector { *; }
-keep class **.HiltModules { *; }
-keep class **.Hilt_* { *; }

# === SQLCIPHER ===
-keep class net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

# === ROOM DATABASE ===
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class *
-keep class * extends androidx.room.RoomDatabase

# === RETROFIT & OKHTTP ===
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
-dontwarn retrofit2.**
-dontwarn okhttp3.**
-dontwarn okio.**

# === KOTLIN ===
-keep class kotlin.Metadata { *; }
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class ** {
    public static ** INSTANCE;
    **$Companion **;
}