## Transactional key value store.

### Overview

Sample application that implements transactional key value store.
It is implemented in Kotlin and uses no third party libraries, except ones mentions in
[libs.version.toml](gradle/libs.versions.toml) file. Implementation aims to be as simple as possible
and target as many platforms as possible.

#### List supported of platforms

##### JVM

[JVM](readme/jvm_sample.png)

##### JS

[WEB](readme/web_sample.png)

##### iOS

[iOS](readme/ios_sample.png)

##### Android

[Android](readme/android_sample.png)

### How to build

Note: WASM compiles seems to be broken on MacOS and continue running even after close.
Experimental (not even alpha) versions. Kill precesses manually.

##### JVM

````bash
./gradlew :composeApp:run
````

##### JS

````bash
./gradlew :composeApp:wasmJsBrowserRun
````

##### iOS

Sorry, for now no simple script.
If you will, open `iosApp` in Xcode and run it.

##### Android

Sorry, for now no simple script.
If you will just compile and run it in Android Studio/Gradle or whatever you use.

### System design

Controversial to clean architecture I picked up onion design. This example is way too simple to
show benefits of clean architecture also overheads of clean architecture are not needed here.
Thought, this onion design is meant to be scalable and can, with some refactoring, be used in
clean architecture.
Proes: simplicity, easy to understand, easy to implement even with MVI
Cons: lack of scalability, lack of separation of concerns (in UI part, navigation will be nightmare)
Overall: good for small projects, not recommended for big ones.

[LIL](readme/system_design.png)

### Tech Stack

As case stated: "The solution shouldn't depend on any third party libraries." I've minimized usage
of any third party libraries. Whole list can be found in
[libs.version.toml](gradle/libs.versions.toml).
Only ones used are:

- AGP (Android Gradle Plugin) - for Android build
- Kotlin - for language
- Icepick - simplify and unify multiplatform build
- AndroidX - for Android build
- Kotlin test - for testing
- Compose - for UI
  I was cutoff from JVM libraries, so I've wrote my own implementations. Did it for
  [CuncurrectHashMap](src/commonMain/kotlin/com/transactionalkeyvaluestore/ConcurrentHashMap.kt) but
  It was too much for me, too much time consumed.
  [TransactionalKeyValueStore.transactions](src/commonMain/kotlin/com/transactionalkeyvaluestore/TransactionalKeyValueStore.kt)
  should be an Queue. Concurrent queue. But I've implemented it as a list. This lead for sync on
  storage. Its defiantly not a good solution. But it's a solution.

### project structure

| Path                                   | Description                                                                                                                                                             |
|----------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [build-conventions](build-conventions) | Utilisation of composite build vs buildScr makes it faster.                                                                                                             |
| [design-system](design-system)         | Implementation of Design system. Not that much, though I reckon with good underdanting of desing and close work with desiners we can achive decign system, step by step |
| [domain](domain)                       | App is biuld in concepts of onion ach, Holder of business logic                                                                                                         |                                                                                                                                   |

### commit history

If you read this line, you are my hero. I thought this task will take me at max 5 hours.
Do a git log with timestamps. It was a journey.
