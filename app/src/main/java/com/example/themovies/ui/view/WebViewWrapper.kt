package com.example.themovies.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.example.themovies.App
import com.example.themovies.R
import com.example.themovies.databinding.WebViewWrapperBinding

class WebViewWrapper @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    companion object {
        const val TAG = "WebViewWrapper"
    }

    private var mBinding: WebViewWrapperBinding? = null
    private var supportBackStack = false
    private var clearHistory = false

    init {
        initAttrs(attrs)
        mBinding = WebViewWrapperBinding.inflate(LayoutInflater.from(context), this, true)
        initView()
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.WebViewWrapper, 0, 0)

        supportBackStack =
            typedArray.getBoolean(R.styleable.WebViewWrapper_supportBackStack, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        mBinding?.progressLoading?.root?.setBackgroundColor(App.getResColor(R.color.white))
        mBinding?.webView?.apply {
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    if (clearHistory){
                        clearHistory()
                        clearHistory = false
                    }
                    mBinding?.progressLoading?.root?.isVisible = false
                    mBinding?.swipeContainer?.isRefreshing = false
                }
            }
            webChromeClient = WebChromeClient()
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
        }

        if (supportBackStack){
            setupBackstackSupport()
        }

        mBinding?.swipeContainer?.setOnRefreshListener {
            mBinding?.webView?.reload()
        }
    }

    private fun setupBackstackSupport(){
        mBinding?.webView?.setOnKeyListener(object : OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event?.action == KeyEvent.ACTION_DOWN
                    && v is WebView
                    && keyCode == KeyEvent.KEYCODE_BACK
                    && v.canGoBack()
                ) {
                    v.goBack()
                    return true
                }
                return false
            }
        })
    }

    private fun loadUrl(url: String) {
        mBinding?.progressLoading?.root?.isVisible = true
        mBinding?.webView?.loadUrl(url)
    }

    fun loadUrlAndClearBackStack(url: String){
        clearHistory = true
        loadUrl(url)
    }

    fun onPause() {
        mBinding?.webView?.onPause()
    }

    fun onResume() {
        mBinding?.webView?.onResume()
    }

}