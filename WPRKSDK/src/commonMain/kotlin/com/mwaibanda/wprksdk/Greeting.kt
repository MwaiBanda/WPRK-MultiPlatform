package com.mwaibanda.wprksdk

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}