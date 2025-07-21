package com.frogobox.apprecycler.sample.kotlin.usingadapter.nested

import com.frogobox.apprecycler.core.BaseActivity
import com.frogobox.databinding.ActivityFrogoRvGridBinding

class KotlinNestedActivity : BaseActivity<ActivityFrogoRvGridBinding>() {

    override fun setupViewBinding(): ActivityFrogoRvGridBinding {
        return ActivityFrogoRvGridBinding.inflate(layoutInflater)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setupDetailActivity("Nested RecyclerView")
//        setupNewsApi()
//    }
//
//    private fun setupNewsApi() {
//        ConsumeNewsApi(NewsUrl.API_KEY).usingChuckInterceptor(BuildConfig.DEBUG, this).apply {
//            getTopHeadline( // Adding Base Parameter on main function
//                null,
//                null,
//                null,
//                NewsConstant.COUNTRY_ID,
//                null,
//                null,
//                object : ConsumeApiResponse<ArticleResponse> {
//                    override fun onSuccess(data: ArticleResponse) {
//                        // Your Ui or data
//                        val listData = mutableListOf<Article>()
//                        data.articles?.let { listData.addAll(it) }
//                        setupRecyclerView(setupDataNested(listData))
//                    }
//
//                    override fun onFailed(statusCode: Int, errorMessage: String) {
//                        // Your failed to do
//                        showToast(errorMessage)
//                    }
//
//                    override fun onFinish() {
//
//                    }
//
//                    override fun onShowProgress() {
//                        // Your Progress Show
//                        Log.d("RxJavaShow", "Show Progress")
//                        runOnUiThread {
//                            // Stuff that updates the UI
//                        }
//                    }
//
//                    override fun onHideProgress() {
//                        // Your Progress Hide
//                        Log.d("RxJavaHide", "Hide Progress")
//                        runOnUiThread {
//                            // Stuff that updates the UI
//                        }
//
//                    }
//
//                })
//        }
//    }
//
//    private fun setupDataNested(data: MutableList<Article>): MutableList<MutableList<Article>> {
//        val list = mutableListOf<MutableList<Article>>()
//        for (i in 0..10) {
//            list.add(data)
//        }
//        return list
//    }
//
//    private fun setupRecyclerView(data: MutableList<MutableList<Article>>) {
//
//        val mLinearLayoutManager = LinearLayoutManager(this)
//        val mAdapter = FrogoNestedAdapter<Article>()
//        mAdapter.setCallback(object : IFrogoNestedHolder<Article> {
//            override fun nestedCustomView(): Int {
//                return R.layout.frogo_rv_grid_type_3
//            }
//
//            override fun nestedListener(): FrogoRecyclerViewListener<Article> {
//                return object : FrogoRecyclerViewListener<Article> {
//                    override fun onItemClicked(
//                        view: View,
//                        data: Article,
//                        position: Int,
//                        notifyListener: FrogoRecyclerNotifyListener<Article>
//                    ) {
//                        showToast("Click : $data")
//                    }
//
//                    override fun onItemLongClicked(
//                        view: View,
//                        data: Article,
//                        position: Int,
//                        notifyListener: FrogoRecyclerNotifyListener<Article>
//                    ) {
//                        showToast("Long Click : $data")
//                    }
//
//                    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
//                        return oldItem.title == newItem.title
//                    }
//
//                    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
//                        return oldItem == newItem
//                    }
//                }
//            }
//
//            override fun nestedCallback(): IFrogoViewHolder<Article> {
//                return object : IFrogoViewHolder<Article> {
//                    override fun setupInitComponent(
//                        view: View,
//                        data: Article,
//                        position: Int,
//                        notifyListener: FrogoRecyclerNotifyListener<Article>
//                    ) {
//                        val iv = view.findViewById<ImageView>(R.id.frogo_rv_grid_type_3_iv_poster)
//                        val tv_title =
//                            view.findViewById<TextView>(R.id.frogo_rv_grid_type_3_tv_title)
//                        val tv_sub =
//                            view.findViewById<TextView>(R.id.frogo_rv_grid_type_3_tv_subtitle)
//                        val tv_desc = view.findViewById<TextView>(R.id.frogo_rv_grid_type_3_tv_desc)
//
//                        iv.setImageExt(data.urlToImage)
//                        tv_title.text = data.title
//                        tv_sub.text = data.author
//                        tv_desc.text = data.description
//                    }
//                }
//            }
//        })
//        mAdapter.setupNestedView()
//        mAdapter.setupDataNested(data)
//        binding.frogoRecyclerView.apply {
//            layoutManager = mLinearLayoutManager
//            setHasFixedSize(true)
//            adapter = mAdapter
//        }
//    }


}