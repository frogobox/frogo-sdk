package com.frogobox.ads.delegate

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback

/**
 * Created by faisalamir on 22/03/22
 * FrogoAdmob
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */


class FrogoAdDelegatesImpl : FrogoAdDelegates,
    AdmobDelegates by AdmobDelegatesImpl(),
    UnityAdDelegates by UnityAdDelegatesImpl() {

    override fun setupFrogoAdDelegates(activity: AppCompatActivity) {
        setupAdmobDelegates(activity)
        setupUnityAdDelegates(activity)
    }

    override fun showAdmobXUnityAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        callback: FrogoAdInterstitialCallback
    ) {

        if (admobInterstitialId == "") {
            showUnityAdInterstitial(unityInterstitialId, object : FrogoUnityAdInterstitialCallback {
                override fun onClicked(tag: String, message: String) {
                    callback.onClicked(tag, message)
                }

                override fun onShowAdRequestProgress(tag: String, message: String) {
                    callback.onShowAdRequestProgress(tag, message)
                }

                override fun onHideAdRequestProgress(tag: String, message: String) {
                    callback.onHideAdRequestProgress(tag, message)
                }

                override fun onAdDismissed(tag: String, message: String) {
                    callback.onAdDismissed(tag, message)
                }

                override fun onAdFailed(tag: String, errorMessage: String) {
                    callback.onAdFailed(tag, errorMessage)
                }

                override fun onAdLoaded(tag: String, message: String) {
                    callback.onAdLoaded(tag, message)
                }

                override fun onAdShowed(tag: String, message: String) {
                    callback.onAdShowed(tag, message)
                }
            })
        } else {
            showAdInterstitial(admobInterstitialId, object : FrogoAdmobInterstitialCallback {
                override fun onShowAdRequestProgress(tag: String, message: String) {
                    callback.onShowAdRequestProgress(tag, message)
                }

                override fun onHideAdRequestProgress(tag: String, message: String) {
                    callback.onHideAdRequestProgress(tag, message)
                }

                override fun onAdDismissed(tag: String, message: String) {
                    callback.onAdDismissed(tag, message)
                }

                override fun onAdFailed(tag: String, errorMessage: String) {
                    if (unityInterstitialId == "") {
                        callback.onAdFailed(tag, errorMessage)
                    } else {
                        showUnityAdInterstitial(unityInterstitialId,
                            object : FrogoUnityAdInterstitialCallback {
                                override fun onClicked(tag: String, message: String) {
                                    callback.onClicked(tag, message)
                                }

                                override fun onShowAdRequestProgress(tag: String, message: String) {
                                    callback.onShowAdRequestProgress(tag, message)
                                }

                                override fun onHideAdRequestProgress(tag: String, message: String) {
                                    callback.onHideAdRequestProgress(tag, message)
                                }

                                override fun onAdDismissed(tag: String, message: String) {
                                    callback.onAdDismissed(tag, message)
                                }

                                override fun onAdFailed(tag: String, errorMessage: String) {
                                    callback.onAdFailed(tag, errorMessage)
                                }

                                override fun onAdLoaded(tag: String, message: String) {
                                    callback.onAdLoaded(tag, message)
                                }

                                override fun onAdShowed(tag: String, message: String) {
                                    callback.onAdShowed(tag, message)
                                }
                            })
                    }
                }

                override fun onAdLoaded(tag: String, message: String) {
                    callback.onAdLoaded(tag, message)
                }

                override fun onAdShowed(tag: String, message: String) {
                    callback.onAdShowed(tag, message)
                }
            })
        }


    }

    override fun showAdmobXUnityAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        timeout: Int,
        callback: FrogoAdInterstitialCallback
    ) {

        if (admobInterstitialId == "") {
            showUnityAdInterstitial(unityInterstitialId, object : FrogoUnityAdInterstitialCallback {
                override fun onClicked(tag: String, message: String) {
                    callback.onClicked(tag, message)
                }

                override fun onShowAdRequestProgress(tag: String, message: String) {
                    callback.onShowAdRequestProgress(tag, message)
                }

                override fun onHideAdRequestProgress(tag: String, message: String) {
                    callback.onHideAdRequestProgress(tag, message)
                }

                override fun onAdDismissed(tag: String, message: String) {
                    callback.onAdDismissed(tag, message)
                }

                override fun onAdFailed(tag: String, errorMessage: String) {
                    callback.onAdFailed(tag, errorMessage)
                }

                override fun onAdLoaded(tag: String, message: String) {
                    callback.onAdLoaded(tag, message)
                }

                override fun onAdShowed(tag: String, message: String) {
                    callback.onAdShowed(tag, message)
                }
            })
        } else {
            showAdInterstitial(admobInterstitialId, timeout, object :
                FrogoAdmobInterstitialCallback {
                override fun onShowAdRequestProgress(tag: String, message: String) {
                    callback.onShowAdRequestProgress(tag, message)
                }

                override fun onHideAdRequestProgress(tag: String, message: String) {
                    callback.onHideAdRequestProgress(tag, message)
                }

                override fun onAdDismissed(tag: String, message: String) {
                    callback.onAdDismissed(tag, message)
                }

                override fun onAdFailed(tag: String, errorMessage: String) {
                    if (unityInterstitialId == "") {
                        callback.onAdFailed(tag, errorMessage)
                    } else {
                        showUnityAdInterstitial(unityInterstitialId,
                            object : FrogoUnityAdInterstitialCallback {
                                override fun onClicked(tag: String, message: String) {
                                    callback.onClicked(tag, message)
                                }

                                override fun onShowAdRequestProgress(tag: String, message: String) {
                                    callback.onShowAdRequestProgress(tag, message)
                                }

                                override fun onHideAdRequestProgress(tag: String, message: String) {
                                    callback.onHideAdRequestProgress(tag, message)
                                }

                                override fun onAdDismissed(tag: String, message: String) {
                                    callback.onAdDismissed(tag, message)
                                }

                                override fun onAdFailed(tag: String, errorMessage: String) {
                                    callback.onAdFailed(tag, errorMessage)
                                }

                                override fun onAdLoaded(tag: String, message: String) {
                                    callback.onAdLoaded(tag, message)
                                }

                                override fun onAdShowed(tag: String, message: String) {
                                    callback.onAdShowed(tag, message)
                                }
                            })
                    }
                }

                override fun onAdLoaded(tag: String, message: String) {
                    callback.onAdLoaded(tag, message)
                }

                override fun onAdShowed(tag: String, message: String) {
                    callback.onAdShowed(tag, message)
                }
            })
        }


    }

    // ---------------------------------------------------------------------------------------------

    // Mixed Ads Unity >> Admob
    override fun showUnityXAdmobAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        callback: FrogoAdInterstitialCallback
    ) {

        if (unityInterstitialId == "") {
            showAdInterstitial(admobInterstitialId,
                object : FrogoAdmobInterstitialCallback {
                    override fun onShowAdRequestProgress(tag: String, message: String) {
                        callback.onShowAdRequestProgress(tag, message)
                    }

                    override fun onHideAdRequestProgress(tag: String, message: String) {
                        callback.onHideAdRequestProgress(tag, message)
                    }

                    override fun onAdDismissed(tag: String, message: String) {
                        callback.onAdDismissed(tag, message)
                    }

                    override fun onAdFailed(tag: String, errorMessage: String) {
                        callback.onAdFailed(tag, errorMessage)
                    }

                    override fun onAdLoaded(tag: String, message: String) {
                        callback.onAdLoaded(tag, message)
                    }

                    override fun onAdShowed(tag: String, message: String) {
                        callback.onAdShowed(tag, message)
                    }
                })
        }

        showUnityAdInterstitial(unityInterstitialId, object : FrogoUnityAdInterstitialCallback {
            override fun onAdDismissed(tag: String, message: String) {
                callback.onAdDismissed(tag, message)
            }

            override fun onAdFailed(tag: String, errorMessage: String) {
                if (admobInterstitialId == "") {
                    callback.onAdFailed(tag, errorMessage)
                } else {
                    showAdInterstitial(admobInterstitialId,
                        object : FrogoAdmobInterstitialCallback {
                            override fun onShowAdRequestProgress(tag: String, message: String) {
                                callback.onShowAdRequestProgress(tag, message)
                            }

                            override fun onHideAdRequestProgress(tag: String, message: String) {
                                callback.onHideAdRequestProgress(tag, message)
                            }

                            override fun onAdDismissed(tag: String, message: String) {
                                callback.onAdDismissed(tag, message)
                            }

                            override fun onAdFailed(tag: String, errorMessage: String) {
                                callback.onAdFailed(tag, errorMessage)
                            }

                            override fun onAdLoaded(tag: String, message: String) {
                                callback.onAdLoaded(tag, message)
                            }

                            override fun onAdShowed(tag: String, message: String) {
                                callback.onAdShowed(tag, message)
                            }
                        })
                }
            }

            override fun onAdLoaded(tag: String, message: String) {
                callback.onAdLoaded(tag, message)
            }

            override fun onAdShowed(tag: String, message: String) {
                callback.onAdShowed(tag, message)
            }

            override fun onClicked(tag: String, message: String) {
                callback.onClicked(tag, message)
            }

            override fun onShowAdRequestProgress(tag: String, message: String) {
                callback.onShowAdRequestProgress(tag, message)
            }

            override fun onHideAdRequestProgress(tag: String, message: String) {
                callback.onHideAdRequestProgress(tag, message)
            }
        })
    }

    override fun showUnityXAdmobAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        timeout: Int,
        callback: FrogoAdInterstitialCallback
    ) {

        if (unityInterstitialId == "") {
            showAdInterstitial(admobInterstitialId,
                timeout,
                object : FrogoAdmobInterstitialCallback {
                    override fun onShowAdRequestProgress(tag: String, message: String) {
                        callback.onShowAdRequestProgress(tag, message)
                    }

                    override fun onHideAdRequestProgress(tag: String, message: String) {
                        callback.onHideAdRequestProgress(tag, message)
                    }

                    override fun onAdDismissed(tag: String, message: String) {
                        callback.onAdDismissed(tag, message)
                    }

                    override fun onAdFailed(tag: String, errorMessage: String) {
                        callback.onAdFailed(tag, errorMessage)
                    }

                    override fun onAdLoaded(tag: String, message: String) {
                        callback.onAdLoaded(tag, message)
                    }

                    override fun onAdShowed(tag: String, message: String) {
                        callback.onAdShowed(tag, message)
                    }
                })
        }

        showUnityAdInterstitial(unityInterstitialId, object : FrogoUnityAdInterstitialCallback {
            override fun onAdDismissed(tag: String, message: String) {
                callback.onAdDismissed(tag, message)
            }

            override fun onAdFailed(tag: String, errorMessage: String) {
                if (admobInterstitialId == "") {
                    callback.onAdFailed(tag, errorMessage)
                } else {
                    showAdInterstitial(admobInterstitialId,
                        timeout,
                        object : FrogoAdmobInterstitialCallback {
                            override fun onShowAdRequestProgress(tag: String, message: String) {
                                callback.onShowAdRequestProgress(tag, message)
                            }

                            override fun onHideAdRequestProgress(tag: String, message: String) {
                                callback.onHideAdRequestProgress(tag, message)
                            }

                            override fun onAdDismissed(tag: String, message: String) {
                                callback.onAdDismissed(tag, message)
                            }

                            override fun onAdFailed(tag: String, errorMessage: String) {
                                callback.onAdFailed(tag, errorMessage)
                            }

                            override fun onAdLoaded(tag: String, message: String) {
                                callback.onAdLoaded(tag, message)
                            }

                            override fun onAdShowed(tag: String, message: String) {
                                callback.onAdShowed(tag, message)
                            }
                        })
                }
            }

            override fun onAdLoaded(tag: String, message: String) {
                callback.onAdLoaded(tag, message)
            }

            override fun onAdShowed(tag: String, message: String) {
                callback.onAdShowed(tag, message)
            }

            override fun onClicked(tag: String, message: String) {
                callback.onClicked(tag, message)
            }

            override fun onShowAdRequestProgress(tag: String, message: String) {
                callback.onShowAdRequestProgress(tag, message)
            }

            override fun onHideAdRequestProgress(tag: String, message: String) {
                callback.onHideAdRequestProgress(tag, message)
            }
        })
    }

}