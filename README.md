# JustinsJavaUtils
[![](https://jitpack.io/v/com.justinschaaf/justinsjavautils.svg?style=flat-square)](https://jitpack.io/#com.justinschaaf/justinsjavautils)

A collection of utils written by Justin.

### Usage

If you're using Maven, add the following to your pom.xml

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.justinschaaf</groupId>
        <artifactId>justinsjavautils</artifactId>
        <version>-SNAPSHOT</version>
    </dependency>
</dependencies>
```

Or, if you're using Gradle, add the following to your build.gradle

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
    
dependencies {
    implementation 'com.justinschaaf:justinsjavautils:-SNAPSHOT'
}
```

For the latest version, use -SNAPSHOT as the version.
```xml
<version>-SNAPSHOT</version>
```

For a specific comment, use the short comment id.
```xml
<version>6ab13d8</version>
```

For a specific release version, use the release tag.
```xml
<version>v1.0.0</version>
```
Note: This currently won't work as there are no release versions.