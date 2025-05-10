// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.{Valid, DeqIO}

// Problem:
// Implement a GCD circuit (the greatest common divisor of two numbers).
// Input numbers are bundled as 'RealGCDInput' and communicated using the Decoupled handshake protocol
//
class RealGCDInput extends Bundle {
  val a = UInt(16.W)
  val b = UInt(16.W)
}

class RealGCD extends Module {
  val io  = IO(new Bundle {

    // inputs are taken from a decoupled input
    // i.e. there are valid and ready signal in addition to bits signal in which a and b is at
    val in  = DeqIO(new RealGCDInput())

    // output is a valid output
    // i.e. in addition to gdc(a,b), it also provides valid signal
    val out = Output(Valid(UInt(16.W)))
  })

  // a register , to store some value, executed only the first clock cycle
  val x = Reg(UInt())

  // another register , to store another value, executed only the first clock cycle
  val y = Reg(UInt())

  // another register to store busy signal, i.e. actively computing, executed only the first clock cycle
  val busy = RegInit(false.B)

  // ready is true if busy is false, i.e. if the computing is done, ready will be set to true
  // in the first cycle, ready signal will be set to high, accepting incoming data
  // thereafter, ready signal stays low, until busy signal becomes low
  io.in.ready := !busy

  // read the x and y values as a and b when input data is valid and 
  // module is not in a busy state
  // this gets executed every clock cycle, but it does not goes inside when
  // unless busy is true
  when (io.in.valid && !busy) {

    // store a and b
    x := io.in.bits.a
    y := io.in.bits.b

    // set the busy value to true
    // because module has new values and a computation needs to happen
    busy := true.B
  } 

  // when module is busy
  // i.e. there is an ongoing computation
  // this gets executed every clock cycle
  when (busy) {

    // this is eucledian algorithm for finding gcd(a,b)

    // check if x > y, if so swap values
    // we can directly re-wire values because it gets executed only on the next clock
    when (x > y)  { x := y; y := x } 

    // otherwise get the y by substracting x from it
    .otherwise    { y := y - x }
  }

  // now set the x as the output
  io.out.bits  := x

  // but dont set the output to be valid
  // check if y is 0 and p is busy
  // y == 0 means computation has been completed
  // therefore set the valid bit
  io.out.valid := y === 0.U && busy

  // when the valid bit is 1
  // computation has completed
  // therefore set the busy signal to false, indicating, it is ready for new computation
  when (io.out.valid) {
    busy := false.B
  }
}
