1. 安装NDK

    在工具栏点击File->Settings->Appearance & Behavior->System Settings->Android SDK->SDK Tools选中LLDB和NDK，点击确认，软件会自动安装NDK。
    默认安装路径: 你的sdk路径\ndk-bundle文件夹
    
2.  新建一个工程
    在main文件夹下新建一个目录jni
    
3. 在jni目录下新建一个文件hello-jni.c

    编写c代码，按照规范编写

        #include <jni.h>
        //jstring: 返回值类型 String
        //规则: Java_包名_类名_方法名（jvm虚拟机的指针，调用者对象）
        jstring Java_com_lhy_jnidemo01_MainActivity_helloFromC(JNIEnv* env,jobject thiz){
            return (*env)->NewStringUTF(env, "你好,我来自C代码 !!!");
        }
4. 在jni目录下新建Android.mk文件

		#指定编译的文件夹  指定当前文件目录
		LOCAL_PATH := $(call my-dir)
		#编译器会定义很多的临时变量，中间变量，最好清空所有的中间变量。
		include $(CLEAR_VARS)
		#编译出来模块的名称
		LOCAL_MODULE    := hello-jni
		# 编译的源代码的名称
		LOCAL_SRC_FILES := hello-jni.c
		#编译一个动态库，静态库
		#静态库 文件名.a   包含所有的函数并且函数运行的依赖，体积大，包含所有的API
		#动态库 文件名.so  包含函数，不包含函数运行的依赖，体积小，运行的时候，去操作系统寻找需要的API
		include $(BUILD_SHARED_LIBRARY)
    
5. 在build.gradle中配置

        externalNativeBuild {
            ndkBuild {
                path file("src\\main\\jni\\Android.mk")
            }
        }
        
    点击 Make Projec 即可生成.so文件,路径: app\build\intermediates\ndk-build
    
##在MainActivity里面: 
1. 需要把动态链接库加载到jvm虚拟机,添加以下代码: 
        static{
          //System.loadLibrary("库文件名称不带前缀，后缀名");
          System.loadLibrary("hello-jni");
        }
2. 声明native的方法: 
        public native String helloFromC();
3. 像调用一般java代码一样调用native的方法.

