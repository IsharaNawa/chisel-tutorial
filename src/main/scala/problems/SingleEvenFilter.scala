// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util._

// Problem:
//
// Create a composition (chain) of two filters:
//
// SingleFilter - indicates that input is single decimal digit
// (i.e. is less or equal to 9)
//
// EvenFilter   - indicates that input is even number
//

// this is an abstract filter class, which needs to be implemented using extending
// it tells that T can be an any chisel data type
// it accepts dtype parameter which is of any T chisel data type
// this parameter can set the datatype of the inputs and outputs
abstract class Filter[T <: Data](dtype: T) extends Module {

  // io pins have valid signals in addition to actual data signals(given by bits signal)
  val io = IO(new Bundle {
    val in = Input(Valid(dtype))
    val out = Output(Valid(dtype))
  })
}

// create a class extending the filter abstract class
// this is of type of any chisel data type
// has 2 params, dtype is any chisel data type, f is a function which converts T to Bool
// f seems like a function to test 
  // 1. If the filter is a single filter(gets T and returns a boolean to indicate if its a single value)
  // 2. If the filter is a even filter(gets T and returns a boolean to indicate if its a even value)
class PredicateFilter[T <: Data](dtype: T, f: T => Bool) extends Filter(dtype) {

  // set the valid bit of the output signal if input is valid and result returned by f is true
  io.out.valid := io.in.valid && f(io.in.bits)

  // connect the output , directly to the input
  io.out.bits  := io.in.bits
}

// object for single filter
object SingleFilter {

  // this is the apply function
  // it takes UInt type and parameter dtype
  def apply[T <: UInt](dtype: T) = 
    // Change function argument of Predicate filter below ----------

    // create a new PredicateFilter instance
    // first parameter is dtype which can be directly passed
    // second parameter is a function to tell if the current bits(x) is a single value
    Module(new PredicateFilter(dtype, (x: T) => x <= 9.U))
    // Change function argument of Predicate filter above ----------
}

// object for even filter
object EvenFilter {

  // this is the apply function
  // it takes UInt type and parameter dtype
  def apply[T <: UInt](dtype: T) = 
    // Change function argument of Predicate filter below ----------

    // create a new PredicateFilter instance
    // first parameter is dtype which can be directly passed
    // second parameter is a function to tell if the current bits(x) is an even value
    Module(new PredicateFilter(dtype, (x: T) => x(0)===0.U.asBool))
    // Change function argument of Predicate filter above ----------
}

// this class again extends the abstract filter class
// i.e. we already have required io ports here
class SingleEvenFilter[T <: UInt](dtype: T) extends Filter(dtype) {
  // Implement composition below ----------

  // create a single filter
  val single = SingleFilter(dtype)

  // create an even filter
  val even = EvenFilter(dtype)

  // set the valid bit of the output signal if input is valid and result returned by f is true
  //single.io.in.valid := io.in.valid
  //single.io.in.bits := io.in.bits
  // shorthand version of above two lines
  single.io.in := io.in

  // using the similar shorthand version
  even.io.in := single.io.out
  io.out := even.io.out

  // Implement composition above ----------
}
