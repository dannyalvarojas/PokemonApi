package com.example.pokeapi.util.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.pokeapi.R
import com.example.pokeapi.databinding.TextSearchViewBinding

class TextSearchView(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

        private var biding = TextSearchViewBinding.inflate(LayoutInflater
            .from(context), this, true)

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TextSearchView)
        biding.titleTextView.text = attributes.getString(R.styleable.TextSearchView_title)
    }


}