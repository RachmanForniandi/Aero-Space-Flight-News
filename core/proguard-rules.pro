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
# Aturan internal untuk core module
# Biasanya hanya untuk testing atau debugging

# SQLCipher native libraries
-keep class net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

# Native methods untuk SQLCipher
-keepclassmembers class net.sqlcipher.database.SQLiteDatabase {
    native <methods>;
}

# Testing libraries (hanya untuk internal)
-dontwarn org.junit.**
-dontwarn org.mockito.**
-dontwarn org.assertj.**

# Debug logging - akan dihapus di release
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}


