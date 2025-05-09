package problems

import chisel3.stage.ChiselStage

object GenerateVerilog {
  def main(args: Array[String]): Unit = {
    // Use ChiselStage to generate Verilog
    (new ChiselStage).emitVerilog(new DynamicMemorySearch(n = 8, w = 16))
    println(getVerilog(new DynamicMemorySearch(n = 8, w = 16)))
  }
}
