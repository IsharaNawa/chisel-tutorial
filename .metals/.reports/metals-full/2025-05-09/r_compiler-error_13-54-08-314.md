file:///D:/Academics/PhD/4.Chisel/chisel-tutorial/src/main/scala/problems/DynamicMemorySearch.scala
### java.lang.IndexOutOfBoundsException: -1

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 2077
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

    // this is to check if write enable is asserted
    val isWr   = Input(Bool())

    // write to this address(this is an index to the memory element)
    val wrAddr = Input(UInt(log2Ceil(n).W))

    // data to be written or data to be searched
    val data   = Input(UInt(w.W))

    // for enabling sequential searching
    val en     = Input(Bool())

    // output , to store the index/address of the matched data
    val target = Output(UInt(log2Ceil(n).W))

    // output , done is true if the search is 
    val done   = Output(Bool())
  })

  // Creates a register with an initial value 0
  // setting the bit width : log2Ceil(n) returns the smallest number of bits needed to count up to n-1
  val index  = RegInit(0.U(log2Ceil(n).W))

  // create a synchronous memory with:
  // n entries (i.e., it can store n elements)
  // each entry being a UInt (unsigned integer)
  // with a bit-width of w
  val list   = Mem(n, UInt(w.W))

  // this is the current memory address
  val valueInAddress = list(index)

  // checking for done condition
  // if io.en = true means still searching for the element, hence done is not true
  // when memval is equal to data or index is at the last element of the memory
  // if both conditions satisfied, done is correct
  val done   = !io.en && ((memVal === io.data) || (index === (n-1).asUInt))

  when(io.isWr){
    list()@@
  }

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