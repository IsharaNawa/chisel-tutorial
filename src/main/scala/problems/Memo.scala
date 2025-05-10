// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a dual port memory of 256 8-bit words.
// When 'wen' is asserted, write 'wrData' to memory at 'wrAddr'
// When 'ren' is asserted, 'rdData' holds the output
// of reading the memory at 'rdAddr'
//
class Memo extends Module {
  val io = IO(new Bundle {
    val wen     = Input(Bool())
    val wrAddr  = Input(UInt(8.W))
    val wrData  = Input(UInt(8.W))
    val ren     = Input(Bool())
    val rdAddr  = Input(UInt(8.W))
    val rdData  = Output(UInt(8.W))
  })

  // memory to be written or read from
  val mem = Mem(256, UInt(8.W))

  // Implement below ----------

  // check if wen is enabled
  when(io.wen){

    // if so, connect the data to the address given by the wrAddress of the memory
    mem(io.wrAddr) := io.wrData
  }

  // check is reading is enabled
  when(io.ren){

    // if so, connect the reading data to the output
    io.rdData := mem(io.rdAddr)
  }.otherwise{

    // otherwise connect 0
    io.rdData := 0.U
  }

  // Implement above ----------

}
