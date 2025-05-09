// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.PeekPokeTester

// Problem:
//
// Implement test with PeekPokeTester
//
class Max2Tests(c: Max2) extends PeekPokeTester(c) {
  for (i <- 0 until 10) {

    // Implement below ----------
     val val1 = rnd.nextInt(256)
     val val2 = rnd.nextInt(256)


    poke(c.io.in0, val1)
    poke(c.io.in1, val2)
    step(1)
    
    if(val1>val2){
      expect(c.io.out, val1)
    }else{
      expect(c.io.out, val2)
    }

    // shorthand version
    // expect(c.io.out, if (val1 > val2) val1 else val2)

    // Implement above ----------
  }
}
