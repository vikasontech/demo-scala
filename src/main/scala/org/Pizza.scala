package org

import scala.collection.mutable.ArrayBuffer

class Pizza {
  private val toppings = new ArrayBuffer[Topping]

  def addToppings(topping: Topping){this.toppings += topping}

  def getToppings() =  toppings.toList

}

case class Topping(val name: String)
