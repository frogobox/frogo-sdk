# Frogo SDK Consumer ProGuard Rules

# --------------------------------------------------
# 1. Preserve Public API Surface
# --------------------------------------------------
-keep public class com.frogobox.** {
    public protected *;
}
-keep interface com.frogobox.** { *; }
-dontwarn com.frogobox.**

# --------------------------------------------------
# 2. Preserve DTO Models & Data Classes
# (Includes private fields required for Gson/Moshi reflection)
# --------------------------------------------------
-keep class com.frogobox.**.model.** { *; }
-keep class com.frogobox.**.dto.** { *; }
-keep class com.frogobox.**.response.** { *; }
-keep class com.frogobox.**.request.** { *; }

# Preserve fields annotated with @SerializedName, @Expose, @Keep
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
    @com.google.gson.annotations.Expose <fields>;
}

# Support @Keep annotation
-keep @androidx.annotation.Keep class * { *; }
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

# --------------------------------------------------
# 3. Preserve Parcelable & Serializable
# --------------------------------------------------
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# --------------------------------------------------
# 4. Preserve Enums
# --------------------------------------------------
-keepclassmembers enum com.frogobox.** {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# --------------------------------------------------
# 5. Preserve Custom UI Views (XML Inflation)
# --------------------------------------------------
-keep public class com.frogobox.** extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

# --------------------------------------------------
# 6. Attributes for Reflection & Generic Signatures
# --------------------------------------------------
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod

