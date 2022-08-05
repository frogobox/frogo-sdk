package com.frogobox.sdk.source

import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.coresdk.source.CoreDataSource


/*
 * Created by faisalamir on 08/04/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

open class FrogoRepository(
    private val remoteDataSource: CoreDataSource,
    private val localDataSource: CoreDataSource
) : CoreDataSource() {

    override fun savePrefString(key: String, value: String) {
        localDataSource.savePrefString(key, value)
    }

    override fun savePrefString(key: String, value: String, callback: FrogoStateResponse) {
        localDataSource.savePrefString(key, value, callback)
    }

    override fun savePrefLong(key: String, value: Long) {
        localDataSource.savePrefLong(key, value)
    }

    override fun savePrefLong(key: String, value: Long, callback: FrogoStateResponse) {
        localDataSource.savePrefLong(key, value, callback)
    }

    override fun savePrefFloat(key: String, value: Float) {
        localDataSource.savePrefFloat(key, value)
    }

    override fun savePrefFloat(key: String, value: Float, callback: FrogoStateResponse) {
        localDataSource.savePrefFloat(key, value, callback)
    }

    override fun savePrefInt(key: String, value: Int) {
        localDataSource.savePrefInt(key, value)
    }

    override fun savePrefInt(key: String, value: Int, callback: FrogoStateResponse) {
        localDataSource.savePrefInt(key, value, callback)
    }

    override fun savePrefBoolean(key: String, value: Boolean) {
        localDataSource.savePrefBoolean(key, value)
    }

    override fun savePrefBoolean(key: String, value: Boolean, callback: FrogoStateResponse) {
        localDataSource.savePrefBoolean(key, value, callback)
    }

    override fun deletePref(key: String) {
        localDataSource.deletePref(key)
    }

    override fun deletePref(key: String, callback: FrogoStateResponse) {
        localDataSource.deletePref(key, callback)
    }

    override fun nukePref() {
        localDataSource.nukePref()
    }

    override fun nukePref(callback: FrogoStateResponse) {
        localDataSource.nukePref(callback)
    }

    override fun getPrefString(key: String): String {
        return localDataSource.getPrefString(key)
    }

    override fun getPrefString(key: String, callback: FrogoDataResponse<String>) {
        localDataSource.getPrefString(key, callback)
    }

    override fun getPrefLong(key: String): Long {
        return localDataSource.getPrefLong(key)
    }

    override fun getPrefLong(key: String, callback: FrogoDataResponse<Long>) {
        localDataSource.getPrefLong(key, callback)
    }

    override fun getPrefFloat(key: String): Float {
        return localDataSource.getPrefFloat(key)
    }

    override fun getPrefFloat(key: String, callback: FrogoDataResponse<Float>) {
        localDataSource.getPrefFloat(key, callback)
    }

    override fun getPrefInt(key: String): Int {
        return localDataSource.getPrefInt(key)
    }

    override fun getPrefInt(key: String, callback: FrogoDataResponse<Int>) {
        localDataSource.getPrefInt(key, callback)
    }

    override fun getPrefBoolean(key: String): Boolean {
        return localDataSource.getPrefBoolean(key)
    }

    override fun getPrefBoolean(key: String, callback: FrogoDataResponse<Boolean>) {
        localDataSource.getPrefBoolean(key, callback)
    }

    override fun getPrefString(key: String, defaultValue: String): String {
        return localDataSource.getPrefString(key, defaultValue)
    }

    override fun getPrefLong(key: String, defaultValue: Long): Long {
        return localDataSource.getPrefLong(key, defaultValue)
    }

    override fun getPrefFloat(key: String, defaultValue: Float): Float {
        return localDataSource.getPrefFloat(key, defaultValue)
    }

    override fun getPrefInt(key: String, defaultValue: Int): Int {
        return localDataSource.getPrefInt(key, defaultValue)
    }

    override fun getPrefBoolean(key: String, defaultValue: Boolean): Boolean {
        return localDataSource.getPrefBoolean(key, defaultValue)
    }

    override fun getPrefString(
        key: String,
        defaultValue: String,
        callback: FrogoDataResponse<String>
    ) {
        localDataSource.getPrefString(key, defaultValue, callback)
    }

    override fun getPrefLong(key: String, defaultValue: Long, callback: FrogoDataResponse<Long>) {
        localDataSource.getPrefLong(key, defaultValue, callback)
    }

    override fun getPrefFloat(
        key: String,
        defaultValue: Float,
        callback: FrogoDataResponse<Float>
    ) {
        localDataSource.getPrefFloat(key, defaultValue, callback)
    }

    override fun getPrefInt(key: String, defaultValue: Int, callback: FrogoDataResponse<Int>) {
        localDataSource.getPrefInt(key, defaultValue, callback)
    }

    override fun getPrefBoolean(
        key: String,
        defaultValue: Boolean,
        callback: FrogoDataResponse<Boolean>
    ) {
        localDataSource.getPrefBoolean(key, defaultValue, callback)
    }
}