// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.Cat

// Problem:
//
// Implement a 16-bit Fibonacci Linear-feedback shift register
// with polynomial x^16 + x^14 + x^13 + x^11 + 1
// State change is allowed only when 'inc' is asserted
//
class LFSR16 extends Module {
  val io = IO(new Bundle {
    val inc = Input(Bool())
    val out = Output(UInt(16.W))
  })


  // create a register
  val reg = RegInit(1.U(16.W))

  // if the inc is enabled, then get the next value
  when(io.inc){
    val nxt_res = Cat(reg(0)^reg(2)^reg(3)^reg(5), reg(15,1)) 
    reg := nxt_res
  }

  // then connect the new register to the output
  io.out := reg
}