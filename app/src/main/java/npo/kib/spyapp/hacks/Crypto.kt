package npo.kib.spyapp.hacks

import android.content.Context
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import com.google.crypto.tink.signature.EcdsaSignKeyManager
import com.google.crypto.tink.signature.SignatureConfig

class Crypto(context: Context) {

    private val keysetName = "master"
    private val prefFilename = "master_pref"
    private val masterUri = "android-keystore://master"

    init {
        SignatureConfig.register()
    }

    val privateKeySetHandle: KeysetHandle = AndroidKeysetManager.Builder()
        .withSharedPref(context, keysetName, prefFilename)
        .withKeyTemplate(EcdsaSignKeyManager.ecdsaP256Template())
        .withMasterKeyUri(masterUri)
        .build()
        .keysetHandle
}