package com.yatatsu.expiresmemo.data

import java.util.Date



class JavaUtilDateProvider : DateProvider {
  override fun now(): Date {
    return Date()
  }
}
