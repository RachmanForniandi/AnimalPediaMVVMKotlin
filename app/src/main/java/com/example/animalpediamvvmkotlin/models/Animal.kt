package com.example.animalpediamvvmkotlin.models

import com.google.gson.annotations.SerializedName


data class ApiKey(
    val message:String?,
    val key:String?
)
data class Animal (
    val name:String?,
    val taxonomy: Taxonomy?,
    val location:String?,
    val speed:String?,
    val diet: String?,

    @SerializedName("lifespan")
    val lifeSpan: String?,

    @SerializedName("Image")
    val imageURL: String?
)

data class Taxonomy (
    var kingdom: String?,
    var order: String?,
    var family: String?
)

data class Speed (
    var metric: String? ,
    var imperial: String?
)

