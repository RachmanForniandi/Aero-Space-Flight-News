# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# ============================================
# APP MODULE - PROGUARD RULES
# Aturan khusus untuk module aplikasi utama
# ============================================

# === APLIKASI & BASE PACKAGE ===
# Application class
-keep class rachman.forniandi.aerospaceflightnews.apps.MyApplication { *; }

# Seluruh package app
-keep class rachman.forniandi.aerospaceflightnews.** { *; }

# === MAIN ACTIVITY & SPLASH ===
# Activities utama
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.main.MainActivity{ *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.splash.SplashScreenActivity { *; }

# === UI PRESENTATION - FRAGMENTS & VIEWMODELS ===
# Articles feature
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.articles.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.articles.ArticlesFragment { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.articles.ArticlesViewModel { *; }

# Blogs feature
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.blogs.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.blogs.BlogsFragment { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.blogs.BlogsViewModel { *; }

# Detail Contents feature
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents.DetailArticlesFragment { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents.DetailArticlesViewModel { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents.DetailBlogsFragment { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents.DetailBlogsViewModel { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents.DetailContentsWebviewActivity { *; }

# Home feature
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.home.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.home.HomeFragment { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.home.HomeViewModel { *; }

# Settings feature
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.settings.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.settings.SettingsActivity { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.settings.SettingsViewModel { *; }

# === DI MODULES ===
# Dependency injection modules
-keep class rachman.forniandi.aerospaceflightnews.di.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.di.FavoriteContentModuleDependencies { *; }
-keep class rachman.forniandi.aerospaceflightnews.di.NavigationModule { *; }

# === UTILITIES ===
# Utility classes spesifik app
-keep class rachman.forniandi.aerospaceflightnews.util.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.util.ContentDiffUtil { *; }
-keep class rachman.forniandi.aerospaceflightnews.util.NavigationProviderImpl { *; }

# === NAVIGATION COMPONENT ===
# Untuk Navigation Args (generated classes)
-keepclassmembers class ** implements androidx.navigation.NavArgs {
    public *;
}
-keep class androidx.navigation.** { *; }

# Untuk Safe Args
-keepclassmembers class * extends androidx.navigation.NavArgs {
    public *** get*();
}
-keep class * implements androidx.navigation.NavArgs { *; }

# === VIEWBINDING ===
# ViewBinding classes (semua yang di-generate)
-keepclassmembers class * implements androidx.viewbinding.ViewBinding {
    public static * inflate(android.view.LayoutInflater);
    public static * bind(android.view.View);
}



# === ACTIVITY & FRAGMENT LIFECYCLE ===
# Memastikan method lifecycle tetap ada
-keepclassmembers class * extends androidx.fragment.app.Fragment {
    public void on*(...);
}
-keepclassmembers class * extends androidx.activity.ComponentActivity {
    public void on*(...);
}

# SQLCipher rules (untuk jaga-jaga)
-keep class net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

# Android Keystore
-keep class android.security.keystore.** { *; }

# === UNTUK VIEWMODEL YANG MEMILIKI CONSTRUCTOR DENGAN PARAMETER ===
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}
-keepclassmembers class * extends androidx.lifecycle.AndroidViewModel {
    <init>(android.app.Application);
}

# === ADAPTERS DI APP (jika ada adapter spesifik app) ===
-keep class rachman.forniandi.aerospaceflightnews.adapters.** { *; }

# === INTERFACES DENGAN ANNOTATION RETROFIT (jika ada di app) ===
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# === KOTLIN COMPANION OBJECT & OBJECT SINGLETON ===
-keepclassmembers class ** {
    public static ** INSTANCE;
    **$Companion **;
}

# === ENUM CLASSES ===
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# === UNTUK CLASS YANG DIINVOKE VIA REFLEKSI ===
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# === UNTUK PARCELABLE ===
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# === UNTUK CUSTOM VIEWS ===
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# === UNTUK RESOURCES ===
# Menjaga referensi ID resource
-keepclassmembers class **.R$* {
    public static <fields>;
}

# === UNTUK JAVASCRIPT INTERFACE (jika ada WebView) ===
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# AndroidX dan Support Library
-keep class androidx.** { *; }
-keep interface androidx.** { *; }
-dontwarn androidx.**

# Material Components
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

# Kotlin Reflect (jika digunakan)
-keep class kotlin.reflect.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.reflect.**

# Kotlinx Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.coroutines.** {
    volatile <fields>;
}
-dontwarn kotlinx.coroutines.**
-keep class kotlinx.coroutines.** { *; }

# Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

# Retrofit
-keep class retrofit2.** { *; }
-keepattributes Exceptions
-dontwarn retrofit2.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-keep class okio.** { *; }
-dontwarn okio.**

# Hilt & Dagger
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.internal.Binding
-keep class * extends dagger.internal.ModuleAdapter
-keep class * extends dagger.internal.StaticInjection
-dontwarn dagger.**
-dontwarn javax.inject.**

# SQLCipher
-keep class net.sqlcipher.** { *; }
-keep interface net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class com.bumptech.glide.** { *; }
-dontwarn com.bumptech.glide.**

# Coil
-keep class coil3.** { *; }
-dontwarn coil3.**

# Navigation Component
-keep class androidx.navigation.** { *; }
-keepclassmembers class ** implements androidx.navigation.NavArgs {
    public *;
}

# Room Database
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class *
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**

# Chucker
-keep class com.github.chuckerteam.chucker.** { *; }
-dontwarn com.github.chuckerteam.chucker.**

# LeakCanary
-keep class com.squareup.leakcanary.** { *; }
-dontwarn com.squareup.leakcanary.**

# DataStore
-keep class androidx.datastore.** { *; }

# ViewBinding & DataBinding
-keepclassmembers class * implements androidx.viewbinding.ViewBinding {
    public static * inflate(android.view.LayoutInflater);
}

# Parcelable
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Custom Views
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Model Classes dari Aplikasi
-keep class rachman.forniandi.core.domain.entity.** { *; }
-keep class rachman.forniandi.core.data.local.entity.** { *; }
-keep class rachman.forniandi.core.data.remote.response.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.** { *; }
-keep class rachman.forniandi.favorite.** { *; }

# Untuk mengatasi warning "Missing classes"
-dontwarn **

# === ATURAN UNTUK DEBUG (AKAN DIHAPUS DI RELEASE) ===
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}