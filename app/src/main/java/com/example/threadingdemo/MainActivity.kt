package com.example.threadingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btnStop:Button
    private val mHandler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.button)
        btnStop  = findViewById<Button>(R.id.button2)

        // Demonstrating we are running something heavy on main thread
//        btnStart.setOnClickListener{
//            for(i in 1..10){
//                Log.d("HelloWorld","$i")
//                Thread.sleep(1000)
//            }
//        }

        //    // 1) Making an inner class to make a background thread

//        btnStart.setOnClickListener {
//            val myThread = ExampleThread()
//            myThread.start()
//        }
//    }
//
//    class ExampleThread : Thread() {
//
//        override fun run() {
//            for (i in 1..10) {
//                Log.d("HelloWorld", "$i")
//                Thread.sleep(1000)
//            }
//        }

        // 2) Implementing a runnable interface for running a new thread

//        btnStart.setOnClickListener {
//            val runnable = ExampleRunnable()
//
//        // Put runnable reference in thread obj and then call start to run in a new thread
//            Thread(runnable).start()
//
//            // If want to run runnable in main thread
////            runnable.run()
//        }

//    }

//    class ExampleRunnable : Runnable{
//        override fun run() {
//            for (i in 1..10) {
//                Log.d("HelloWorld", "$i")
//                Thread.sleep(1000)
//            }
//        }
//
//    }

        // NOTE -> Now if we want to update something on main thread using new thread , we cant do that
        // bcoz views can only be altered by the thread who created them so we have to pass info from thread
        // main thread by posting it via handler of main thread. Since we have declared handler in mainActivity
        // it will comminucate with msg queue of main thread only.

        // Post handler with runnable or message

        class ExampleRunnable : Runnable {


            override fun run() {
                for (i in 1..10) {

                   // 1. Using handler to post in msgQueue of main Thread
                    if (i == 5) {
//                        mHandler.post(Runnable {
//                            btnStop.text = "50"
//                        })
//                    }
                        // 2. Using runOnUiThread  to post in msgQueue of main thread
                        runOnUiThread(Runnable {
                            btnStop.text = "50"
                        })
                    }


                    Log.d("HelloWorld", "$i")
                    Thread.sleep(1000)
                }
            }
        }

        btnStart.setOnClickListener {
            val runnable = ExampleRunnable()
            Thread(runnable).start()
        }
    }

}


