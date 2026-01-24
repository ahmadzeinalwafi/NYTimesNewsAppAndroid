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

# Keep Dependency Injection Modules
-keep class com.example.newsapp.core.di.** { *; }

# Keep Domain Layer (Models, Repositories, UseCases)
-keep class com.example.newsapp.core.domain.** { *; }

# Keep Data Layer (Repository Impl, Data Sources, Remote)
-keep class com.example.newsapp.core.data.** { *; }

# Keep Utils (Resource, Mapper, etc.)
-keep class com.example.newsapp.core.utils.** { *; }

# Keep UI Components (Adapters)
-keep class com.example.newsapp.core.ui.** { *; }

# Keep Room Entities and DAOs
-keep class com.example.newsapp.core.data.source.local.entity.** { *; }
-keep class com.example.newsapp.core.data.source.local.room.** { *; }

# Keep Retrofit Services
-keep class com.example.newsapp.core.data.source.remote.network.** { *; }
-keep class com.example.newsapp.core.data.source.remote.response.** { *; }

#SQL Cipher
-keep class net.sqlcipher.** { *; }
-keep class net.sqlcipher.database.** { *; }