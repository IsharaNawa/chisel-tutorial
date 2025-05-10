// See LICENSE.txt for license details.
package problems

import chisel3._
import scala.collection.mutable.ArrayBuffer

// Problem:
//
// Implement a four-by-four multiplier using a look-up table.
//
class Mul extends Module {
  val io = IO(new Bundle {
    val x   = Input(UInt(4.W))
    val y   = Input(UInt(4.W))
    val z   = Output(UInt(8.W))
  })
  val mulsValues = new ArrayBuffer[UInt]()

  // Calculate io.z = io.x * io.y by generating a table of values for mulsValues
  for(i <- 0 until 16){
    for(j <- 0 until 16){
      mulsValues += (i * j).asUInt(8.W)
    }
  }

  // convert that to a LUT
  val table = VecInit(mulsValues)

  // get the index related to the current values
  val index = (io.x << 4.U) | io.y

  // connect the outut as the table indexed with the correct index value
  io.z := table(index)
}
