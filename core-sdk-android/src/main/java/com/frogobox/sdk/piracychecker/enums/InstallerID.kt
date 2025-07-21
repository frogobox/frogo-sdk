package com.frogobox.sdk.piracychecker.enums

enum class InstallerID(private val text: String) {
    GOOGLE_PLAY("com.android.vending|com.google.android.feedback"),
    AMAZON_APP_STORE("com.amazon.venezia"),
    GALAXY_APPS("com.sec.android.app.samsungapps"),
    HUAWEI_APP_GALLERY("com.huawei.appmarket"),
    XIAOMI_GET_APPS("com.xiaomi.mipicks");
    
    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    override fun toString(): String {
        return text
    }
    
    fun toIDs(): List<String> = if (text.contains("|")) {
        val split = text.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        ArrayList(listOf(*split))
    } else {
        ArrayList(listOf(text))
    }
}
