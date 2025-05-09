file:///D:/Academics/PhD/4.Chisel/chisel-tutorial/src/main/scala/problems/DynamicMemorySearch.scala
### java.lang.IndexOutOfBoundsException: -1

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 1284
uri: file:///D:/Academics/PhD/4.Chisel/chisel-tutorial/src/main/scala/problems/DynamicMemorySearch.scala
text:
```scala
// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.log2Ceil

// Problem:
//
// This module should be able to write 'data' to
// internal memory at 'wrAddr' if 'isWr' is asserted.
//
// This module should perform sequential search of 'data'
// in internal memory if 'en' was asserted at least 1 clock cycle
//
// While searching 'done' should remain 0,
// 'done' should be asserted when search is complete
//
// If 'data' has been found 'target' should be updated to the
// address of the first occurrence
//

class DynamicMemorySearch(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val isWr   = Input(Bool())
    val wrAddr = Input(UInt(log2Ceil(n).W))
    val data   = Input(UInt(w.W))
    val en     = Input(Bool())
    val target = Output(UInt(log2Ceil(n).W))
    val done   = Output(Bool())
  })

  // Creates a register with an initial value 0
  // setting the bit width : log2Ceil(n) returns the smallest number of bits needed to count up to n-1
  val index  = RegInit(0.U(log2Ceil(n).W))

  // Implement below ----------

  // this is the internal memory
  // it has n number of elements, and is of w bit width
  val list   = Mem(n, UInt(w.W))


  val memVal = list(@@)

  // Implement above ----------
  val done   = !io.en && ((memVal === io.data) || (index === (n-1).asUInt))

  // Implement below ----------


  // Implement above ----------

  when (io.en) {
    index := 0.U
  } .elsewhen (done === false.B) {
    index := index + 1.U
  }
  io.done   := done
  io.target := index
}

```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:129)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:244)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:101)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:88)
	dotty.tools.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:46)
	dotty.tools.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:435)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: -1