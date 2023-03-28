import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {
    private var counterJob: Job? = null
    //LiveData
    private var _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter

    init {
        _counter.value = 0
    }
    //Suspend func
    private suspend fun startCounterWithDelay() {
        while (true) {
            delay(1000)
            _counter.postValue(_counter.value?.plus(1))
        }
    }
    //co
    fun startCounter() {
        counterJob?.cancel()
        //Dispatcher - launch
        counterJob = viewModelScope.launch(Dispatchers.Default) {
            startCounterWithDelay()
        }
    }

    fun stopCounter() {
        counterJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        counterJob?.cancel()
    }
}
