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

  // A Fibonacci LFSR feeds the XOR of specific tapped bits into the MSB.
  // For this polynomial, the feedback taps are on bits 15, 13, 12, and 10 (zero-indexed from LSB).
  // You shift the register right, and the feedback enters the MSB.
  // Do not update the register unless inc is high.

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