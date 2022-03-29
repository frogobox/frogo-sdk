![ScreenShoot Apps](docs/image/ss_banner.png?raw=true)

## About This Project (release-and-work-in-progress 👷🔧️👷‍♀️⛏)
[![](https://jitpack.io/v/frogobox/frogo-sdk.svg?style=flat-square)](https://jitpack.io/#frogobox/frogo-sdk)
[![Android CI](https://github.com/frogobox/frogo-sdk/actions/workflows/android-ci.yml/badge.svg)](https://github.com/frogobox/frogo-sdk/actions/workflows/android-ci.yml)
[![Scan with Detekt](https://github.com/frogobox/frogo-sdk/actions/workflows/detekt-analysis.yml/badge.svg)](https://github.com/frogobox/frogo-sdk/actions/workflows/detekt-analysis.yml)
[![pages-build-deployment](https://github.com/frogobox/frogo-sdk/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/frogobox/frogo-sdk/actions/workflows/pages/pages-build-deployment)
- SDK for anything your problem to make easier developing android apps
- Available for android and desktop
- Privacy Policy [Click Here](https://github.com/frogobox/frogo-sdk/blob/master/PRIVACY-POLICY.md)
- License [Click Here](https://github.com/frogobox/frogo-sdk/blob/master/LICENSE)


## Version Release
This Is Latest Release

    ~ Beta Release
    $version_release = 0.0.2-beta02

What's New??

    * SDK Android and Desktop *
    * Beta Release *

## Download this project

### Step 1. Add the JitPack repository to your build file (build.gradle : Project)
    
#### <Option 1> Groovy Gradle

    // Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

#### <Option 2> Kotlin DSL Gradle

```kotlin
// Add it in your root build.gradle.kts at the end of repositories:

allprojects {
    repositories {
        ...
        maven("https://jitpack.io")
    }
}
```
      
### Step 2. Add the dependency (build.gradle : Module)

    #### <Option 1> Groovy Gradle

        dependencies {
            // library frogo-sdk
            implementation 'com.github.frogobox:frogo-sdk:0.0.2-beta02'
        }

    #### <Option 2> Kotlin DSL Gradle

        dependencies {
            // library frogo-sdk
            implementation("com.github.frogobox:frogo-sdk:0.0.2-beta02")
        }

### Step 3. Function from this SDK

#### All Class SDK (android)
```kotlin
FrogoActivity
FrogoApiClient
FrogoApplication
FrogoComposeActivity
FrogoDate
FrogoFragment
FrogoFunc
FrogoMusic
FrogoMutableLiveData
FrogoNavigation
FrogoPagerHelper
FrogoPreference
FrogoViewModel
```

### All Class SDK (desktop & android)
```kotlin
FrogoApiModel
FrogoApiObserver
FrogoConstant
FrogoCoreApiClient
FrogoDataResponse
FrogoLocalObserver
FrogoStateResponse
```

#### FrogoActivity
```kotlin
fun setupDetailActivity(title: String)

fun setupChildFragment(frameId: Int, fragment: Fragment)

fun showToast(message: String)

fun setupEventEmptyView(view: View, isEmpty: Boolean)

fun setupEventProgressView(view: View, progress: Boolean)

fun checkExtra(extraKey: String): Boolean

fun <Model> baseFragmentNewInstance(
    fragment: FrogoFragment<*>,
    argumentKey: String,
    extraDataResult: Model
)

fun verifySignature()

fun readSignature()

fun verifyInstallerId()

fun verifyUnauthorizedApps()

fun verifyStores()

fun verifyDebug()

fun verifyEmulator()

fun showApkSignatures()
```

#### FrogoFragment
```kotlin
fun setupChildFragment(frameId: Int, fragment: Fragment)

fun checkArgument(argsKey: String): Boolean

fun setupEventEmptyView(view: View, isEmpty: Boolean)

fun setupEventProgressView(view: View, progress: Boolean)

fun showToast(message: String)

fun <Model> baseNewInstance(argsKey: String, data: Model)
```
#### FrogoFunc

```kotlin
fun createFolderPictureVideo()

fun getVideoFilePath(): String

fun createDialogDefault(
    context: Context,
    title: String,
    message: String,
    listenerYes: () -> Unit,
    listenerNo: () -> Unit
)

fun noAction(): Boolean

fun randomNumber(start: Int, end: Int): Int

fun isNetworkAvailable(context: Context): Boolean?

fun <T> fetchRawData(mContext: Context, sourceRaw: Int): ArrayList<T>

fun <T> fetchRawData(mContext: Context, sourceRaw: Int, shuffle: Boolean): ArrayList<T>

fun getJsonFromAsset(context: Context, filename: String): String?

fun <T> getArrayFromJsonAsset(context: Context, filename: String): MutableList<T>

fun getDrawableString(context: Context, nameResource: String): Int

fun getRawString(context: Context, nameResource: String): Int
```

#### FrogoMusic

```kotlin
fun playMusic(context: Context, musicFile: Int)

fun stopMusic()

fun pauseMusic()
```

#### FrogoDate

```kotlin
fun getTimeStamp(): String

fun getTimeNow(): String

fun getCurrentDate(format: String): String

fun dateTimeToTimeStamp(date: String?): Long

fun getCurrentUTC(): String

fun timetoHour(date: String?): String

fun dateTimeTZtoHour(date: String?): String

fun DateTimeMonth(date: String?): String

fun dateTimeSet(date: String?): String

fun dateTimeProblem(date: String?): String

fun getTimeAgo(time: Long): String?

fun compareDate(newDate: String): String?

fun messageDate(newDate: String): String?

fun getDataChat(time: Long): String?

fun convertClassificationDate(string: String?): String

fun convertDateNewFormat(string: String?): String

fun convertLongDateNewFormat(string: String?): String

fun revertFromLongDateNewFormat(string: String?): String

fun convertTargetDate(string: String?): String

fun diffTime(timeStart: String, timeEnd: String): Long
```

### Added Function

<details>
<summary>Click for detail !!!</summary>

## FrogoLog
- SDK for your Log problem to make easier developing android apps
- frogo-log is Long Term Service
- Line number show
- Toast for easy develop and debug

### Screenshoot Result
<table>
    <tr>
        <th>SS 1</th>
        <th>SS 2</th>
        <th>SS 3</th>
        <th>SS 4</th>
    </tr>
    <tr>
        <td><img width="200px" height="360px" src="docs/image/log/ss-1.png"></td>
        <td><img width="200px" height="360px" src="docs/image/log/ss-2.png"></td>
        <td><img width="200px" height="360px" src="docs/image/log/ss-3.png"></td>
        <td><img width="200px" height="360px" src="docs/image/log/ss-4.png"></td>
    </tr>
<table>

### Screenshoot Library Sample
![ScreenShoot Apps](docs/image/log/ss_result_1.png?raw=true)

### FrogoLog (with line code)
```kotlin
// Function Log Debug
FrogoLog.d("Debug")

// Function Log Info
FrogoLog.i("Info")

// Function Log Verbose
FrogoLog.v("Verbose")

// Function Log Warn
FrogoLog.w("Warn")

// Function Log Error
FrogoLog.e("Error")

// Function Log Simple Debug without message params
FrogoLog.d()

```
### FrogoLog (with line code and Toast)
```kotlin
// Function Log Debug (adding context params)
FrogoLog.d("Debug", this@MainActivity)

// Function Log Info (adding context params)
FrogoLog.i("Info", this@MainActivity)

// Function Log Verbose
FrogoLog.v("Verbose", this@MainActivity)

// Function Log Warn (adding context params)
FrogoLog.w("Warn", this@MainActivity)

// Function Log Error (adding context params)
FrogoLog.e("Error", this@MainActivity)

// Function Log Simple Debug without message params
FrogoLog.d(this@MainActivity)
```

### FLog (without line code)

```kotlin
// Function Log Debug
FLog.d("Debug")

// Function Log Info
FLog.i("Info")

// Function Log Verbose
FLog.v("Verbose")

// Function Log Warn
FLog.w("Warn")

// Function Log Error
FLog.e("Error")

// Function Log Simple Debug without message params
FLog.d()
```

### Flog (without line code with toast)
```kotlin
// Function Log Debug (adding context params)
FLog.d("Debug", this@MainActivity)

// Function Log Info (adding context params)
FLog.i("Info", this@MainActivity)

// Function Log Verbose
FLog.v("Verbose", this@MainActivity)

// Function Log Warn (adding context params)
FLog.w("Warn", this@MainActivity)

// Function Log Error (adding context params)
FLog.e("Error", this@MainActivity)

// Function Log Simple Debug without message params
FLog.d(this@MainActivity)
```

### Result FrogoLog
![ScreenShoot Apps](docs/image/log/ss_result_2.png?raw=true)

## FrogoNotification
- SDK for your notification problem to make easier developing android apps
- frogo-notification is under huge large development
- Notification with singleton method
- Simple and eazy to use
- With many feature
- Full documentation
- Custom Layout Notification

### Screenshoot Sample

<table>
    <tr>
        <th>Simple Notification</th>
        <th>Stack Notification</th>
        <th>Custom Notification (1)</th>
        <th>Custom Notification (2)</th>
    </tr>
    <tr>
        <td><img width="200px" height="360px" src="docs/image/notification/ss_simple_notif.gif"></td>
        <td><img width="200px" height="360px" src="docs/image/notification/ss_stack_notif.gif"></td>
        <td><img width="200px" height="360px" src="docs/image/notification/ss_custom_layout.gif"></td>
        <td><img width="200px" height="360px" src="docs/image/notification/ss_reply_notif.gif"></td>
    </tr>
</table>

```kotlin
FrogoNotification.Inject(this) // Intialize for Context
    .setChannelId(CHANNEL_ID) // Intialize for Channel ID
    .setChannelName(CHANNEL_NAME) // Initialize for Channel Name
    .setContentIntent(pendingIntent) // Initialize for Content Intent
    .setSmallIcon(R.drawable.ic_frogo_notif) // Initialize for Small Icon
    .setLargeIcon(R.drawable.ic_frogo_notif) // Initialize for Large Icon
    .setContentTitle(resources.getString(R.string.content_title)) // Initialize for Content Title
    .setContentText(resources.getString(R.string.content_text)) // Initialize for Content Text
    .setSubText(resources.getString(R.string.subtext)) // Initialize for Sub Text
    .setupAutoCancel() // Initialize for Auto Cancel
    .build() // Build the Frogo Notification
    .launch(NOTIFICATION_ID) // Notify the Frogo Notification
```

### Simple Notification
```kotlin
FrogoNotification.Inject(this) // Intialize for Context
    .setChannelId(CHANNEL_ID) // Intialize for Channel ID
    .setChannelName(CHANNEL_NAME) // Initialize for Channel Name
    .setContentIntent(pendingIntent) // Initialize for Content Intent
    .setSmallIcon(R.drawable.ic_frogo_notif) // Initialize for Small Icon
    .setLargeIcon(R.drawable.ic_frogo_notif) // Initialize for Large Icon
    .setContentTitle(resources.getString(R.string.content_title)) // Initialize for Content Title
    .setContentText(resources.getString(R.string.content_text)) // Initialize for Content Text
    .setSubText(resources.getString(R.string.subtext)) // Initialize for Sub Text
    .setupAutoCancel() // Initialize for Auto Cancel
    .build() // Build the Frogo Notification
    .launch(NOTIFICATION_ID) // Notify the Frogo Notification
```

### Custom Layout (NEW FEATURE)
```kotlin
val collapsed = object : FrogoNotifCustomContentViewListener {
    override fun setupCustomView(): Int {
        return R.layout.notification_collapsed
    }

    override fun setupComponent(context: Context, customView: RemoteViews) {
        customView.apply{
            setTextViewText(R.id.text_view_collapsed_1, "Hello World!")
        }
    }
}

val expanded = object : FrogoNotifCustomContentViewListener {
    override fun setupCustomView(): Int {
        return R.layout.notification_expanded
    }

    override fun setupComponent(context: Context, customView: RemoteViews) {
        customView.apply {
            setImageViewResource(R.id.image_view_expanded, R.drawable.ic_android)
            setOnClickPendingIntent(R.id.image_view_expanded, clickPendingIntent)
        }
    }
}

FrogoNotification.Inject(this) // Intialize for Context
    .setChannelId(FrogoApp.CHANNEL_ID) // Intialize for Channel ID
    .setChannelName(FrogoApp.CHANNEL_NAME) // Initialize for Channel Name
    .setSmallIcon(R.drawable.ic_android) // Initialize for Small Icon
    .setCustomContentView(collapsed)
    .setCustomBigContentView(expanded)
    .build() // Build the Frogo Notification
    .launch(FrogoApp.NOTIFICATION_ID) // Notify the Frogo Notification
```

### With Action Replay
```kotlin
FrogoNotification.Inject(this)
    .setChannelId(CHANNEL_ID)
    .setChannelName(CHANNEL_NAME as String)
    .setSmallIcon(R.drawable.ic_frogo_notif)
    .setContentTitle(getString(R.string.notif_title))
    .setContentText(getString(R.string.notif_content))
    .setupShowWhen()
    .setupActionRemoteInput(object : FrogoNotifActionRemoteInputListener {
        override fun setRemoteInputResultKey(): String {
            return KEY_REPLY
        }

        override fun setRemoteInputLabel(): String {
            return getString(R.string.notif_action_reply)
        }

        override fun setActionIcon(): Int {
            return R.drawable.ic_frogo_send
        }

        override fun setActionTitle(): String {
            return getString(R.string.notif_action_reply)
        }

        override fun setActionIntent(): PendingIntent? {
            return getReplyPendingIntent()
        }

        override fun setAllowGeneratedReplies(): Boolean {
            return true
        }
    })
    .build()
    .launch(mNotificationId)
```

### With Inbox Style (Stack)
```kotlin
val frogoNotification = FrogoNotification.Inject(this)
    .setChannelId(CHANNEL_ID)
    .setChannelName(CHANNEL_NAME)
    .setSmallIcon(R.drawable.ic_frogo_email)
    .setGroup(GROUP_KEY_EMAILS)
    .setContentIntent(pendingIntent)
    .setupAutoCancel()

// Check if NotificationID is smaller than Max Notif
if (idNotification < MAX_NOTIFICATION) {

    stackNotif[idNotification].message?.let {
        frogoNotification
            .setContentTitle("New Email from " + stackNotif[idNotification].sender)
            .setContentText(it)
            .setLargeIcon(R.drawable.ic_frogo_notif)
    }

} else {

    frogoNotification
        .setContentTitle("$idNotification new emails")
        .setContentText("mail@frogobox.com")
        .setGroupSummary()
        .setupInboxStyle(object : FrogoNotifInboxStyleListener {
            override fun addLine1(): String {
                return "New Email from " + stackNotif[idNotification].sender
            }

            override fun addLine2(): String {
                return "New Email from " + stackNotif[idNotification - 1].sender
            }

            override fun setBigContentTitle(): String {
                return "$idNotification new emails"
            }

            override fun setSummaryText(): String {
                return "mail@frogobox"
            }
        })

}

frogoNotification
    .build()
    .launch(idNotification)
```

### With Frogo Style
```kotlin
FrogoNotification.Inject(this) // Intialize for Context
    .setSmallIcon(R.drawable.ic_frogo_notif) // Initialize for Small Icon
    .setupWithFrogoStyle()
    .build() // Build the Frogo Notification
    .launch(NOTIFICATION_ID) // Notify the Frogo Notification
```

### For Documentation

- Method with description [Click Here](https://github.com/amirisback/frogo-notification/blob/master/frogonotification/src/main/java/com/frogobox/frogonotification/IFrogoNotification.kt)
- Simple Notification [Click Here](https://github.com/amirisback/frogo-notification/blob/master/app/src/main/java/com/frogobox/notification/simple/MainActivity.kt)
- With Action Replay [Click Here](https://github.com/amirisback/frogo-notification/blob/master/app/src/main/java/com/frogobox/notification/custom/NotificationService.kt)
- With Inbox Style (Stack) [Click Here](https://github.com/amirisback/frogo-notification/blob/master/app/src/main/java/com/frogobox/notification/stack/StackNotifActivity.kt)

</details>


## Colaborator
Very open to anyone, I'll write your name under this, please contribute by sending an email to me

- Mail To faisalamircs@gmail.com
- Subject : Github _ [Github-Username-Account] _ [Language] _ [Repository-Name]
- Example : Github_amirisback_kotlin_admob-helper-implementation

Name Of Contribute
- Muhammad Faisal Amir
- Waiting List
- Waiting List

Waiting for your contribute

## Attention !!!
- Please enjoy and don't forget fork and give a star
- Don't Forget Follow My Github Account


![ScreenShoot Apps](docs/image/mad_score.png?raw=true)
