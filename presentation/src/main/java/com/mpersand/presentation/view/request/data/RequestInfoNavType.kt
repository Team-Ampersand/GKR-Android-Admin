package com.mpersand.presentation.view.request.data

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.mpersand.domain.model.order.response.WaitListResponseModel

class RequestInfoNavType: NavType<WaitListResponseModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): WaitListResponseModel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): WaitListResponseModel {
        return Gson().fromJson(value, WaitListResponseModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: WaitListResponseModel) {
        bundle.putParcelable(key, value)
    }
}