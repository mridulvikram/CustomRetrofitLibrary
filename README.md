# CustomRetrofitLibrary

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Step 2. Add the dependency
  dependencies {
	        implementation 'com.github.mridulvikram:CustomRetrofitLibrary:v1.0.2'
          implementation 'com.squareup.retrofit2:converter-scalars:2.5.0'
	}
  
  
