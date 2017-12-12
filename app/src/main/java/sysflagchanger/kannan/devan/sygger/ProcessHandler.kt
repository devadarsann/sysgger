package sysflagchanger.kannan.devan.sygger

import android.os.Build
import java.io.File

/**
 * Created by devan on 9/12/17.
 */
class ProcessHandler:IProcessor {

    constructor(mainActivity: MainActivity) {
        viewUpdater = mainActivity
    }
    var viewUpdater:IViewUpdater?=null

    override fun OnCheckRoot() {
        var thread:Thread= Thread(object :Runnable{
            override fun run() {
                viewUpdater!!.OnRooted(findBinary("su"))
            }
        })
        thread.start()
    }

    private fun findBinary(binary: String): Boolean {
        return if (Build.VERSION.SDK_INT<Build.VERSION_CODES.O) {
            var places = arrayOf("/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/",
                    "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/")
            places.any { File(it + binary).exists() }
        }
        else{
            var places = arrayOf("/su/bin/","/su/xbin/")
            places.any { File(it+binary).exists() }
        }
    }
}