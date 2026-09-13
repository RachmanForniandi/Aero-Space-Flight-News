# ============================================
# FAVORITE MODULE - PROGUARD RULES
# Dynamic Feature Module
# ============================================

# === SELURUH PACKAGE FAVORITE MODULE ===
# Keep semua class di module favorite
-keep class rachman.forniandi.favorite.** { *; }

# === NAVIGATION COMPONENT UNTUK DYNAMIC FEATURE ===
-keep class androidx.navigation.dynamicfeatures.** { *; }
-keep class * implements androidx.navigation.NavArgs { *; }
-keepclassmembers class ** implements androidx.navigation.NavArgs {
    public *;
}

# === VIEWMODEL CONSTRUCTORS ===
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}
-keepclassmembers class * extends androidx.lifecycle.AndroidViewModel {
    <init>(android.app.Application);
}

# === FRAGMENT LIFECYCLE ===
-keepclassmembers class * extends androidx.fragment.app.Fragment {
    public void on*(...);
    public android.view.View onCreateView(...);
    public void onViewCreated(...);
}

# === VIEWBINDING ===
-keepclassmembers class * implements androidx.viewbinding.ViewBinding {
    public static * inflate(android.view.LayoutInflater);
    public static * bind(android.view.View);
}

# === SQLCIPHER (jika module favorite mengakses database) ===
# Core module sudah mewariskan aturan ini via consumer-rules.pro
# Tapi untuk jaga-jaga, tetap sertakan
-keep class net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

# === KOTLIN ===
-keep class kotlin.Metadata { *; }
-keepclassmembers class ** {
    @kotlin.Metadata <fields>;
}
-keepclassmembers class **$WhenMappings {
    <fields>;
}
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

# === RESOURCES ===
-keepclassmembers class rachman.forniandi.favorite.R$* {
    public static <fields>;
}

# === UNTUK DEBUG (AKAN DIHAPUS DI RELEASE) ===
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}