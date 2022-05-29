package com.jp.gojekassignment.base

interface ItemClickCallbk<T> {
    fun onItemClick(position: Int, t: T)
}