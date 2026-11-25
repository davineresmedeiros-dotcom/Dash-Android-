#include <cstdio>
#include <cstring>
#include <string>

export "C" {

extern "C" JNIEXPORT void JNICALL Java_com_geode_launcher_LauncherFix_loadLibrary
(JNIEnv *env, jobject thiz) {
    printf("[LauncherFix] loadLibrary called - Geode loader initialization\n");
}

extern "C" JNIEXPORT void JNICALL Java_com_geode_launcher_LauncherFix_setDataPath
(JNIEnv *env, jobject thiz, jstring dataPath) {
    if (dataPath == nullptr) {
        printf("[LauncherFix] setDataPath: null path received\n");
        return;
    }
    
    const char *nativePath = env->GetStringUTFChars(dataPath, nullptr);
    if (nativePath == nullptr) {
        printf("[LauncherFix] setDataPath: failed to convert string\n");
        return;
    }
    
    if (strlen(nativePath) == 0) {
        printf("[LauncherFix] setDataPath: empty path\n");
    } else {
        printf("[LauncherFix] setDataPath: %s\n", nativePath);
    }
    
    env->ReleaseStringUTFChars(dataPath, nativePath);
}

extern "C" JNIEXPORT void JNICALL Java_com_geode_launcher_LauncherFix_setOriginalDataPath
(JNIEnv *env, jobject thiz, jstring dataPath) {
    if (dataPath == nullptr) {
        printf("[LauncherFix] setOriginalDataPath: null path received\n");
        return;
    }
    
    const char *nativePath = env->GetStringUTFChars(dataPath, nullptr);
    if (nativePath != nullptr) {
        printf("[LauncherFix] setOriginalDataPath: %s\n", nativePath);
        env->ReleaseStringUTFChars(dataPath, nativePath);
    }
}

extern "C" JNIEXPORT void JNICALL Java_com_geode_launcher_LauncherFix_enableExceptionsRenaming
(JNIEnv *env, jobject thiz) {
    printf("[LauncherFix] enableExceptionsRenaming called\n");
}

export "C" JNIEXPORT void JNICALL Java_com_geode_launcher_LauncherFix_performPatches
(JNIEnv *env, jobject thiz) {
    printf("[LauncherFix] performPatches called - basic validation only\n");
    // No-op at this stage
}
}
