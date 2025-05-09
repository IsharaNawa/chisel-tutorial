error id: `<none>`.
file:///D:/Academics/PhD/4.Chisel/chisel-tutorial/src/main/scala/problems/Counter.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -chisel3/UInt#
	 -UInt#
	 -scala/Predef.UInt#
offset: 354
uri: file:///D:/Academics/PhD/4.Chisel/chisel-tutorial/src/main/scala/problems/Counter.scala
text:
```scala
// See LICENSE.txt for license details.
package problems

import chisel3._
// import Counter._

// Problem:
//
// Counter should be incremented by the 'amt'
// every clock if 'en' is asserted
//
object Counter {

  def wrapAround(n: UInt, max: UInt) = 
    Mux(n > max, 0.U, n)

  // Modify below ----------

  // 
  def counter(max: U@@Int, en: Bool, amt: UInt): UInt = {
    val x = RegInit(0.U(max.getWidth.W))
    x := wrapAround(x + 1.U, max)
    x
  }
  // Modify above ----------

}

class Counter extends Module {
  val io = IO(new Bundle {
    val inc = Input(Bool())
    val amt = Input(UInt(4.W))
    val tot = Output(UInt(8.W))
  })

  // connect the output to the counter method in the Counter in the object
  io.tot := Counter.counter(255.U, io.inc, io.amt)

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.