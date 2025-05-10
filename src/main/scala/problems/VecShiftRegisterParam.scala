// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a parametrized simple shift register.
// 'n' is the number of elements in the shift register.
// 'w' is the width of one element.

class VecShiftRegisterParam(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(w.W))
    val out = Output(UInt(w.W))
  })

  // Implement below ----------

  // create a register vector to hold shifting values
  // this vector has n registers
  // since there are no load, lets set 0 as the initial value
  val initValues = Seq.fill(n) { 0.U(w.W) }
  val delays = RegInit(VecInit(initValues))

  // get the input for the first register
  delays(0) := io.in

  // connect the other registers
  for(i <- 1 until n){
    delays(i) := delays(i-1)
  }

  // output of the module is the output of the last register
  io.out := delays(n-1)
}
// Implement above ----------
