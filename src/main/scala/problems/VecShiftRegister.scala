// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a loadable shift register with four 4-bit stages using Vec
// Shift occurs if 'shift' is asserted
// Load  occurs if 'load'  is asserted
// Whole state should be replaced with 'ins' when loaded
//
class VecShiftRegister extends Module {

  // create io
  val io = IO(new Bundle {

    // inputs
    val ins   = Input(Vec(4, UInt(4.W)))

    // enable when you need to get data parallelly
    val load  = Input(Bool())

    // enable when you need to get data serially
    val shift = Input(Bool())

    // output of the last register
    val out   = Output(UInt(4.W))
  })

  // Implement below ----------

  // create 4 registers
  // each register can hold an unsigned integer

  // create a vector of registers
  val delay = Reg(Vec(4, UInt()))

  // check if load is enabled
  when(io.load){

    // if so , parallel load the data by connecting the wires of inputs directly to each register
    delay(0) := io.ins(0)
    delay(1) := io.ins(1)
    delay(2) := io.ins(2)
    delay(3) := io.ins(3)

  }.elsewhen(io.shift){

    // if the shifting is enabled

    // do the shifting by connecting registers appropriately
    delay(0) := io.ins(0)
    delay(1) := delay(0)
    delay(2) := delay(1)
    delay(3) := delay(2)
  }

  // output is the output of the last register
  io.out := delay(3)

  //IMPORTANT
  // It's important to place the load block first. If we place shift block first and load second, 
  // chisel will issue an error , because there can be a circular loop from the second block.
  // If we assign first block as load, chisel already knows that there are no circular paths in the 
  // first block, so it allows for the second block without any issues.
  // Implement above ----------
}
