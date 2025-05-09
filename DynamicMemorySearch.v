module DynamicMemorySearch(
  input         clock,
  input         reset,
  input         io_isWr,
  input  [2:0]  io_wrAddr,
  input  [15:0] io_data,
  input         io_en,
  output [2:0]  io_target,
  output        io_done
);
`ifdef RANDOMIZE_MEM_INIT
  reg [31:0] _RAND_0;
`endif // RANDOMIZE_MEM_INIT
`ifdef RANDOMIZE_REG_INIT
  reg [31:0] _RAND_1;
`endif // RANDOMIZE_REG_INIT
  reg [15:0] list [0:7]; // @[DynamicMemorySearch.scala 52:19]
  wire [15:0] list_valueInAddress_data; // @[DynamicMemorySearch.scala 52:19]
  wire [2:0] list_valueInAddress_addr; // @[DynamicMemorySearch.scala 52:19]
  wire [15:0] list__T_4_data; // @[DynamicMemorySearch.scala 52:19]
  wire [2:0] list__T_4_addr; // @[DynamicMemorySearch.scala 52:19]
  wire  list__T_4_mask; // @[DynamicMemorySearch.scala 52:19]
  wire  list__T_4_en; // @[DynamicMemorySearch.scala 52:19]
  reg [2:0] index; // @[DynamicMemorySearch.scala 46:23]
  wire  _T = ~io_en; // @[DynamicMemorySearch.scala 61:16]
  wire  _T_1 = list_valueInAddress_data == io_data; // @[DynamicMemorySearch.scala 61:43]
  wire  _T_2 = index == 3'h7; // @[DynamicMemorySearch.scala 61:66]
  wire  _T_3 = _T_1 | _T_2; // @[DynamicMemorySearch.scala 61:56]
  wire  done = _T & _T_3; // @[DynamicMemorySearch.scala 61:23]
  wire  _T_5 = ~done; // @[DynamicMemorySearch.scala 72:18]
  wire [2:0] _T_7 = index + 3'h1; // @[DynamicMemorySearch.scala 75:20]
  assign list_valueInAddress_addr = index;
  assign list_valueInAddress_data = list[list_valueInAddress_addr]; // @[DynamicMemorySearch.scala 52:19]
  assign list__T_4_data = io_data;
  assign list__T_4_addr = io_wrAddr;
  assign list__T_4_mask = 1'h1;
  assign list__T_4_en = io_isWr;
  assign io_target = index; // @[DynamicMemorySearch.scala 79:13]
  assign io_done = _T & _T_3; // @[DynamicMemorySearch.scala 78:13]
`ifdef RANDOMIZE_GARBAGE_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_INVALID_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_REG_INIT
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_MEM_INIT
`define RANDOMIZE
`endif
`ifndef RANDOM
`define RANDOM $random
`endif
`ifdef RANDOMIZE_MEM_INIT
  integer initvar;
`endif
`ifndef SYNTHESIS
`ifdef FIRRTL_BEFORE_INITIAL
`FIRRTL_BEFORE_INITIAL
`endif
initial begin
  `ifdef RANDOMIZE
    `ifdef INIT_RANDOM
      `INIT_RANDOM
    `endif
    `ifndef VERILATOR
      `ifdef RANDOMIZE_DELAY
        #`RANDOMIZE_DELAY begin end
      `else
        #0.002 begin end
      `endif
    `endif
`ifdef RANDOMIZE_MEM_INIT
  _RAND_0 = {1{`RANDOM}};
  for (initvar = 0; initvar < 8; initvar = initvar+1)
    list[initvar] = _RAND_0[15:0];
`endif // RANDOMIZE_MEM_INIT
`ifdef RANDOMIZE_REG_INIT
  _RAND_1 = {1{`RANDOM}};
  index = _RAND_1[2:0];
`endif // RANDOMIZE_REG_INIT
  `endif // RANDOMIZE
end // initial
`ifdef FIRRTL_AFTER_INITIAL
`FIRRTL_AFTER_INITIAL
`endif
`endif // SYNTHESIS
  always @(posedge clock) begin
    if(list__T_4_en & list__T_4_mask) begin
      list[list__T_4_addr] <= list__T_4_data; // @[DynamicMemorySearch.scala 52:19]
    end
    if (reset) begin
      index <= 3'h0;
    end else if (!(io_isWr)) begin
      if (io_en) begin
        index <= 3'h0;
      end else if (_T_5) begin
        index <= _T_7;
      end
    end
  end
endmodule
