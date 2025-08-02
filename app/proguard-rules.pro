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


# --- REGRAS PARA GSON E SEUS MODELOS ---
# Manter atributos necessários para Gson
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes Annotation

# Manter classes do Gson
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class com.google.gson.** { *; }


# Manter suas classes de modelo que são serializadas/desserializadas pelo Gson
-keep class com.branchh.afimdefeirax.Model.HistoricoModel { *; }
-keep class com.branchh.afimdefeirax.Model.Historico { *; }
-keep class com.branchh.afimdefeirax.Model.FeirasModel { *; }
-keep class com.branchh.afimdefeirax.Model.Produtos { *; }
-keep class com.google.gson.reflect.TypeToken { *; }

-keep class * implements com.google.gson.JsonSerializer { *; }
-keep class * implements com.google.gson.JsonDeserializer { *; }
-keep class * implements com.google.gson.TypeAdapterFactory { *; }

-keepclassmembers class *{public<init>();}

# A regra abaixo é frequentemente recomendada para Gson, mas pode não ser
# estritamente necessária para o problema específico do TypeToken se as acima estiverem corretas.
# Considere adicionar se ainda enfrentar problemas de serialização/desserialização mais obscuros.
 # -keep class sun.misc.Unsafe { *; }
 -keep class com.google.gson.internal.UnsafeAllocator
# --- FIM DAS REGRAS PARA GSON ---
