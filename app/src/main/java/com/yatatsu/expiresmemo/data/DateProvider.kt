package com.yatatsu.expiresmemo.data

import java.util.Date


interface DateProvider {
  fun now(): Date
}
