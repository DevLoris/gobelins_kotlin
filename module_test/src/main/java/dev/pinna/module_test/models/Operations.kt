package dev.pinna.module_test.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Operation : Parcelable {
    SUM,
    SUBSTRACT,
    MULTIPLY,
    DIVISION
}