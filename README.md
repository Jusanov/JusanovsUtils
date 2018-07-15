# JusanovsUtils
[![](https://jitpack.io/v/net.jusanov/jusanovsutils.svg?style=flat-square)](https://jitpack.io/#net.jusanov/jusanovsutils)

A collection of utils written by Jusanov.

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
        <groupId>net.jusanov</groupId>
        <artifactId>jusanovsutils</artifactId>
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
    implementation 'net.jusanov:jusanovsutils:-SNAPSHOT'
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