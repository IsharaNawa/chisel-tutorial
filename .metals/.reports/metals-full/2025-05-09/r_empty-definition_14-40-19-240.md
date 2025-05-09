error id: scala/Unit#
file:///D:/Academics/PhD/4.Chisel/chisel-tutorial/src/main/scala/problems/GenerateVerilog.scala
empty definition using pc, found symbol in pc: scala/Unit#
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -Unit#
	 -scala/Predef.Unit#
offset: 114
uri: file:///D:/Academics/PhD/4.Chisel/chisel-tutorial/src/main/scala/problems/GenerateVerilog.scala
text:
```scala
package problems

import chisel3.stage.ChiselStage

object GenerateVerilog {
  def main(args: Array[String]): Unit@@ = {
    // Use ChiselStage to generate Verilog
    (new ChiselStage).emitVerilog(new DynamicMemorySearch(n = 8, w = 16))
    println(getVerilog(new DynamicMemorySearch(n = 8, w = 16)))
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: scala/Unit#