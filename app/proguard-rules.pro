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

# Keep Core Module Classes
-keep class com.example.newsapp.core.di.** { *; }
-keep class com.example.newsapp.core.domain.** { *; }
-keep class com.example.newsapp.core.data.** { *; }
-keep class com.example.newsapp.core.utils.** { *; }
-keep class com.example.newsapp.core.ui.** { *; }
-keep class com.example.newsapp.core.data.source.local.entity.** { *; }
-keep class com.example.newsapp.core.data.source.local.room.** { *; }
-keep class com.example.newsapp.core.data.source.remote.network.** { *; }
-keep class com.example.newsapp.core.data.source.remote.response.** { *; }

# Hilt & Dagger
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class net.sqlcipher.** { *; }
-keep class net.sqlcipher.database.** { *; }