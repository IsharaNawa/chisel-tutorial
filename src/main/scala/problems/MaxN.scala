// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement test for this module. Please edit:
// .../chisel-tutorial/src/test/scala/problems/MaxNTests.scala
//
class MaxN(val n: Int, val w: Int) extends Module {

  private def Max2(x: UInt, y: UInt) = Mux(x > y, x, y)

  val io = IO(new Bundle {
    val ins = Input(Vec(n, UInt(w.W)))
    val out = Output(UInt(w.W))
  })

  // reduce the vector using max2, then the highest will be left
  io.out := io.ins.reduceLeft(Max2)
}
