package com.frogobox.recycler.core

/**
 * Created by faisalamircs on 25/03/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 */


interface FrogoDiffUtilCallback<T> {

    // DiffUtils --------------------------------

    fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    fun areContentsTheSame(oldItem: T, newItem: T): Boolean

}