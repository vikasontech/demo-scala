package org

import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite

class SampleTest extends AnyFunSuite with BeforeAndAfter {

  var pizza: Pizza = _
  before{
    pizza = new Pizza
  }

  test("new pizza has zero toppings") {
    assert(pizza.getToppings.size == 0)
  }

  test("add one toppings") {
    pizza.addToppings(Topping("cheez"))
    pizza.getToppings.foreach(println(_))
    assert(pizza.getToppings.size == 1)
  }
}
