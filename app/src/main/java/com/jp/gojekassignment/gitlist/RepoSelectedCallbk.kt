package com.jp.gojekassignment.gitlist

import com.jp.gojekassignment.base.ItemClickCallbk
import com.jp.gojekassignment.data.model.git.GitRepo

interface RepoSelectedCallbk : ItemClickCallbk<GitRepo> {
    fun isPositionExpanded(position: Int): Boolean
}