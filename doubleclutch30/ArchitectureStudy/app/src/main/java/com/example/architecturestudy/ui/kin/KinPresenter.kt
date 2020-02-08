package com.example.architecturestudy.ui.kin

import com.example.architecturestudy.data.repository.NaverSearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class KinPresenter(
    val view : KinContract.View,
    private val repository: NaverSearchRepository?
    ) : KinContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun taskSearch(isNetwork: Boolean, keyword: String) {
        if(repository != null) {
            val searchSingle = repository?.getKin(
                keyword = keyword
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view.showResult(it.items)
                    },
                    {
                        view.showErrorMessage(it.toString())
                    }
                )
            disposable.add(searchSingle)
        }
    }

    override fun getLastData() {
        repository?.getLastKin(
            success = { view.showResult(it) },
            fail = { e -> view.showEmpty(e.toString()) }
        )
    }

    override fun onStop() {
        disposable.clear()
    }
}