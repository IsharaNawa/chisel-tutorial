;buildInfoPackage: chisel3, version: 3.3.1, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit DynamicMemorySearch : 
  module DynamicMemorySearch : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip isWr : UInt<1>, flip wrAddr : UInt<3>, flip data : UInt<16>, flip en : UInt<1>, target : UInt<3>, done : UInt<1>}
    
    reg index : UInt<3>, clock with : (reset => (reset, UInt<3>("h00"))) @[DynamicMemorySearch.scala 46:23]
    cmem list : UInt<16>[8] @[DynamicMemorySearch.scala 52:19]
    infer mport valueInAddress = list[index], clock @[DynamicMemorySearch.scala 55:28]
    node _T = eq(io.en, UInt<1>("h00")) @[DynamicMemorySearch.scala 61:16]
    node _T_1 = eq(valueInAddress, io.data) @[DynamicMemorySearch.scala 61:43]
    node _T_2 = eq(index, UInt<3>("h07")) @[DynamicMemorySearch.scala 61:66]
    node _T_3 = or(_T_1, _T_2) @[DynamicMemorySearch.scala 61:56]
    node done = and(_T, _T_3) @[DynamicMemorySearch.scala 61:23]
    when io.isWr : @[DynamicMemorySearch.scala 64:16]
      infer mport _T_4 = list[io.wrAddr], clock @[DynamicMemorySearch.scala 67:9]
      _T_4 <= io.data @[DynamicMemorySearch.scala 67:21]
      skip @[DynamicMemorySearch.scala 64:16]
    else : @[DynamicMemorySearch.scala 68:20]
      when io.en : @[DynamicMemorySearch.scala 68:20]
        index <= UInt<1>("h00") @[DynamicMemorySearch.scala 71:11]
        skip @[DynamicMemorySearch.scala 68:20]
      else : @[DynamicMemorySearch.scala 72:29]
        node _T_5 = eq(done, UInt<1>("h00")) @[DynamicMemorySearch.scala 72:18]
        when _T_5 : @[DynamicMemorySearch.scala 72:29]
          node _T_6 = add(index, UInt<1>("h01")) @[DynamicMemorySearch.scala 75:20]
          node _T_7 = tail(_T_6, 1) @[DynamicMemorySearch.scala 75:20]
          index <= _T_7 @[DynamicMemorySearch.scala 75:11]
          skip @[DynamicMemorySearch.scala 72:29]
    io.done <= done @[DynamicMemorySearch.scala 78:13]
    io.target <= index @[DynamicMemorySearch.scala 79:13]
    
