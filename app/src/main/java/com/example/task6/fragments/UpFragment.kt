package com.thechance.rxjavaapp.ui.fragment



import android.view.LayoutInflater
import androidx.core.widget.doOnTextChanged
import com.example.task6.databinding.UpFragmentBinding
import com.example.task6.fragments.DownFragment
import com.example.task6.interfaces.Communicator
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.observables.ConnectableObservable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class UpFragment : BaseFragment<UpFragmentBinding>() {
    lateinit var communicator: Communicator
    override val LOG_TAG: String
        get() = javaClass.simpleName
    override val bindingInflater: (LayoutInflater) -> UpFragmentBinding = UpFragmentBinding::inflate

    override fun setup() {
        communicator = activity as Communicator
        setData()
    }

    override fun addCallBack() {

    }
    private fun setData() {
        val observable = PublishSubject.create<String> { emmiter ->
            binding?.textSend?.doOnTextChanged { text, start, before, count ->
                emmiter.onNext(text.toString())
            }.toString()
        }.debounce(1500, TimeUnit.MILLISECONDS).publish()
        connectObservable(observable)

    }
    private fun connectObservable(observable: @NonNull ConnectableObservable<String>){
        observable.connect()
        observable.subscribe(::nextValue, ::onError)
    }
    private fun nextValue(next: String) {
        communicator.sendData(next)
    }
    private fun onError(e:Throwable){
        println("ERROR $e")
    }
}