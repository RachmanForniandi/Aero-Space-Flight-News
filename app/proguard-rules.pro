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

# Seluruh package app (kecuali yang sudah di-keep secara spesifik)
-keep class rachman.forniandi.aerospaceflightnews.** { *; }

#-keep class rachman.forniandi.core.** { *; }
#-dontwarn rachman.forniandi.core.**

# === MAIN ACTIVITY & SPLASH ===
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.main.MainActivity { *; }
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

# Settings feature (YANG SEBELUMNYA TERLEWATKAN)
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.settings.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.settings.SettingsActivity { *; }
-keep class rachman.forniandi.aerospaceflightnews.uiPresentation.settings.SettingsViewModel { *; }

# === DI MODULES (HANYA UNTUK APP) ===
-keep class rachman.forniandi.aerospaceflightnews.di.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.di.FavoriteContentModuleDependencies { *; }
-keep class rachman.forniandi.aerospaceflightnews.di.NavigationModule { *; }

# === UTILITIES ===
-keep class rachman.forniandi.aerospaceflightnews.util.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.util.ContentDiffUtil { *; }
-keep class rachman.forniandi.aerospaceflightnews.util.NavigationProviderImpl { *; }

# === ADAPTERS ===
-keep class rachman.forniandi.aerospaceflightnews.adapters.** { *; }
-keep class rachman.forniandi.aerospaceflightnews.adapters.CarrouselAdapter { *; }
-keep class rachman.forniandi.aerospaceflightnews.adapters.ContentAdapter { *; }
-keep class rachman.forniandi.aerospaceflightnews.adapters.LoadingStatePageAdapter { *; }

# === NAVIGATION COMPONENT ===
-keepclassmembers class ** implements androidx.navigation.NavArgs {
    public *;
}
-keep class androidx.navigation.** { *; }

# === VIEWBINDING ===
-keepclassmembers class * implements androidx.viewbinding.ViewBinding {
    public static * inflate(android.view.LayoutInflater);
    public static * bind(android.view.View);
}

# === ACTIVITY & FRAGMENT LIFECYCLE ===
-keepclassmembers class * extends androidx.fragment.app.Fragment {
    public void on*(...);
}
-keepclassmembers class * extends androidx.activity.ComponentActivity {
    public void on*(...);
}

# === VIEWMODEL CONSTRUCTORS ===
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}
-keepclassmembers class * extends androidx.lifecycle.AndroidViewModel {
    <init>(android.app.Application);
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

# === PARCELABLE ===
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# === CUSTOM VIEWS ===
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# === RESOURCES ===
-keepclassmembers class **.R$* {
    public static <fields>;
}

# === UNTUK DEBUG (AKAN DIHAPUS DI RELEASE) ===
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}