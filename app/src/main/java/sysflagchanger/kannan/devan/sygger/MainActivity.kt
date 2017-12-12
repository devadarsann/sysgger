package sysflagchanger.kannan.devan.sygger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity(),IViewUpdater {
    override fun OnRooted(isRooted: Boolean) {
        if (!isRooted){
            ShowNoRoot()
        }
        else{
            SetProgressBar(View.VISIBLE);
        }
    }

    private fun SetProgressBar(visibility: Int) {
        var handler:Handler = Handler(Looper.getMainLooper())
        handler.post(object:Runnable{
            override fun run() {
                progressBar!!.visibility = visibility
            }
        })
    }


    private fun ShowNoRoot() {
        var handler:Handler = Handler(Looper.getMainLooper())
        handler.post(object :Runnable{
            override fun run() {
                progressBar!!.visibility = View.GONE
                UpdateProgressText("No root access!")
            }
        })
    }

    var backprocessor:IProcessor?=null
    var progressBar:ProgressBar?=null
    var progressText:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InitializeViews()
        backprocessor = ProcessHandler(this)
        UpdateProgressText("Checking root access.....")
        backprocessor?.OnCheckRoot()
    }

    private fun UpdateProgressText(text: String) {
        var handler:Handler = Handler(Looper.getMainLooper())
        handler.post(object :Runnable{
            override fun run() {
                if (progressText==null){
                    InitializeViews()
                }
                progressText!!.text = text
            }
        })
    }

    private fun InitializeViews() {
        progressBar = findViewById<ProgressBar>(R.id.progress)
        progressText = findViewById<TextView>(R.id.progressText)
    }
}
