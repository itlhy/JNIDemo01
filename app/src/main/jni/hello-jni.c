//
// Created by lhy on 2017/5/16.
//
#include <jni.h>

jstring Java_com_lhy_jnidemo01_MainActivity_helloFromC(JNIEnv* env,jobject thiz){
    return (*env)->NewStringUTF(env, "你好,我来自C代码 !!!");
}

