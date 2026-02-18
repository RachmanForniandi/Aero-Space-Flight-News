# ============================================
# FAVORITE MODULE - PROGUARD RULES
# Dynamic Feature Module
# ============================================

# === SELURUH PACKAGE FAVORITE MODULE ===
# Keep semua class di module favorite
-keep class rachman.forniandi.favorite.** { *; }

# === ADAPTER ===
# Adapter untuk menampilkan daftar favorite
-keep class rachman.forniandi.favorite.adapter.** { *; }
-keep class rachman.forniandi.favorite.adapter.FavoriteContentAdapter { *; }

# === DI (Dependency Injection) MODULE ===
# Dagger/Hilt module untuk favorite
-keep class rachman.forniandi.favorite.di.** { *; }
-keep class rachman.forniandi.favorite.di.FavoriteContentComponent { *; }

# === UI FRAGMENT ===
# Fragment untuk menampilkan konten favorite
-keep class rachman.forniandi.favorite.ui.** { *; }
-keep class rachman.forniandi.favorite.ui.FavoriteContentFragment { *; }

# === VIEWMODEL ===
# ViewModel dan Factory-nya
-keep class rachman.forniandi.favorite.viewmodel.** { *; }
-keep class rachman.forniandi.favorite.viewmodel.FavoriteContentViewModel { *; }
-keep class rachman.forniandi.favorite.viewmodel.FavoriteContentViewModelFactory { *; }

# === UNTUK DYNAMIC FEATURE MODULE ===
# Penting: Menjaga class yang diakses dari module app
#-keep class rachman.forniandi.favorite.FavoriteContentFragment { *; }

# === NAVIGATION COMPONENT UNTUK DYNAMIC FEATURE ===
# Navigation graph dan destinations
-keep class androidx.navigation.dynamicfeatures.** { *; }
-keep class * implements androidx.navigation.NavArgs { *; }

# Jika menggunakan Safe Args di dynamic feature
-keepclassmembers class ** implements androidx.navigation.NavArgs {
    public *;
}

# === UNTUK VIEWMODEL YANG MEMILIKI CONSTRUCTOR DENGAN PARAMETER ===
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}
-keepclassmembers class * extends androidx.lifecycle.AndroidViewModel {
    <init>(android.app.Application);
}

# === FRAGMENT LIFECYCLE METHODS ===
-keepclassmembers class * extends androidx.fragment.app.Fragment {
    public void on*(...);
    public android.view.View onCreateView(...);
    public void onViewCreated(...);
}

# SQLCipher rules (jika ada akses database di favorite)
-keep class net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

# Aturan yang sudah ada sebelumnya
-keep class rachman.forniandi.favorite.** { *; }

# === VIEWBINDING DI FAVORITE MODULE ===
-keepclassmembers class * implements androidx.viewbinding.ViewBinding {
    public static * inflate(android.view.LayoutInflater);
    public static * bind(android.view.View);
}

# === UNTUK MENJAGA METHOD/ATTRIBUTE ANNOTATION ===
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions

# === UNTUK KOTLIN COMPANION OBJECT & OBJECT SINGLETON ===
-keepclassmembers class ** {
    public static ** INSTANCE;
    **$Companion **;
}

# === UNTUK ENUM CLASSES ===
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# === UNTUK PARCELABLE ===
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# === UNTUK CUSTOM VIEWS (jika ada) ===
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# === UNTUK RESOURCES DI DYNAMIC FEATURE ===
# Menjaga referensi ID resource di module favorite
-keepclassmembers class rachman.forniandi.favorite.R$* {
    public static <fields>;
}

# === UNTUK KOTLIN DATA CLASS ===
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keep class kotlin.Metadata { *; }
-keepclassmembers class ** {
    @kotlin.Metadata <fields>;
}

# === ATURAN UNTUK DEBUG (AKAN DIHAPUS DI RELEASE) ===
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}